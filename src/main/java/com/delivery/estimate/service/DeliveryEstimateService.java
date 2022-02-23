package com.delivery.estimate.service;

import com.delivery.estimate.domain.Package;
import com.delivery.estimate.domain.Vehicle;
import com.delivery.estimate.service.technical.DeliveryTimeCalculator;
import com.delivery.estimate.service.technical.PackageSelectionStrategy;
import com.delivery.estimate.service.technical.Shipment;
import com.delivery.estimate.service.technical.VehicleSelectionStrategy;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


public class DeliveryEstimateService {

    private final VehicleSelectionStrategy vehicleSelectionStrategy;
    private final PackageSelectionStrategy packageSelectionStrategy;
    private final DeliveryTimeCalculator deliveryTimeCalculator;

    public DeliveryEstimateService(
            VehicleSelectionStrategy vehicleSelectionStrategy,
            PackageSelectionStrategy packageSelectionStrategy,
            DeliveryTimeCalculator deliveryTimeCalculator) {
        this.vehicleSelectionStrategy = vehicleSelectionStrategy;
        this.packageSelectionStrategy = packageSelectionStrategy;
        this.deliveryTimeCalculator = deliveryTimeCalculator;
    }

    List<Package> estimateDelivery(List<Package> packages, List<Vehicle> vehicles) {
        do {
            Vehicle vehicle = vehicleSelectionStrategy.findVehicleWithMinimumWaitTime(vehicles);
            Shipment shipment = packageSelectionStrategy.findPackagesWithin(vehicle.getMaxLoad(), packagesToDeliverNext(packages));
            updateDeliveryTimeForPackages(vehicle, shipment);
            BigDecimal deliveryTime = shipment.lastPackageDeliveryTime();
            vehicle.updateAvailableTimeFrom(deliveryTime);
        } while (allPackagesEstimated(packages));
        return packages;
    }

    private void updateDeliveryTimeForPackages(Vehicle vehicle, List<Package> packagesToBeDelivered) {
        packagesToBeDelivered.forEach(packageItem -> {
            BigDecimal deliveryTime = deliveryTimeCalculator.calculate(vehicle, packageItem);
            packageItem.updateDeliveryTime(deliveryTime);
        });
    }

    private List<Package> packagesToDeliverNext(List<Package> packages) {
        return packages.stream()
                .filter(packageItem -> packageItem.getDeliveryTime()==null).
                collect(Collectors.toList());
    }

    private boolean allPackagesEstimated(List<Package> packages) {
        return packages.stream().anyMatch(packageItem -> packageItem.getDeliveryTime()==null);
    }

    public static DeliveryEstimateService create() {
        return new DeliveryEstimateService(
                new VehicleSelectionStrategy(),
                new PackageSelectionStrategy(),
                new DeliveryTimeCalculator()
        );
    }
}
