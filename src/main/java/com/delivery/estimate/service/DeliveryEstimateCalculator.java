package com.delivery.estimate.service;

import com.delivery.estimate.domain.Package;
import com.delivery.estimate.domain.Vehicle;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class DeliveryEstimateCalculator {

    private final VehicleSelectionStrategy vehicleSelectionStrategy;
    private final PackageSelectionStrategy packageSelectionStrategy;
    private List<Package> packages;
    private List<Vehicle> vehicles;

    public DeliveryEstimateCalculator(
            VehicleSelectionStrategy vehicleSelectionStrategy,
            PackageSelectionStrategy packageSelectionStrategy) {
        this.vehicleSelectionStrategy = vehicleSelectionStrategy;
        this.packageSelectionStrategy = packageSelectionStrategy;
    }

    List<Package> estimateDelivery(List<Package> packages, List<Vehicle> vehicles) {
        assignToGlobal(packages, vehicles);
        do {
            Vehicle vehicle = vehicleSelectionStrategy.findVehicleWithMinimumWaitTime(vehicles);
            List<Package> packagesToBeDelivered = packageSelectionStrategy.findPackagesWithin(vehicle.getMaxLoad(), packagesToDeliver());
            packagesToBeDelivered.forEach(packageItem -> {
                BigDecimal deliveryTime = calculateDeliveryTime(vehicle, packageItem);
                packageItem.updateDeliveryTime(deliveryTime);
            });
            BigDecimal deliveryTime = lastDeliveredPackageTime(packagesToBeDelivered);
            updateVehicleNextAvailableTime(vehicle, deliveryTime);
        } while (allPackagesDelivered(packages));
        return packages;
    }

    private void assignToGlobal(List<Package> packages, List<Vehicle> vehicles) {
        this.packages = packages;
        this.vehicles = vehicles;
    }

    private void updateVehicleNextAvailableTime(Vehicle vehicle, BigDecimal deliveryTime) {
        vehicle.updateAvailableTime(deliveryTime.multiply(BigDecimal.valueOf(2)));
    }

    private BigDecimal lastDeliveredPackageTime(List<Package> packagesToBeDelivered) {
        return packagesToBeDelivered.stream().max(Comparator.comparing(Package::getDeliveryTime)).get().getDeliveryTime();
    }

    private List<Package> packagesToDeliver() {
        return packages.stream()
                .filter(packageItem -> packageItem.getDeliveryTime()==null).
                collect(Collectors.toList());
    }

    private BigDecimal calculateDeliveryTime(Vehicle vehicle, Package packageItem) {
        return vehicle.getAvailableAt()
                .add(packageItem.getDeliveryDistance().divide(vehicle.getMaxSpeed(), 2, RoundingMode.DOWN));
    }

    private boolean allPackagesDelivered(List<Package> packages) {
        return packages.stream().anyMatch(packageItem -> packageItem.getDeliveryTime()==null);
    }

    public static DeliveryEstimateCalculator create() {
        return new DeliveryEstimateCalculator(new VehicleSelectionStrategy(), new PackageSelectionStrategy());
    }
}
