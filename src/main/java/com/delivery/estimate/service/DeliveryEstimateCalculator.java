package com.delivery.estimate.service;

import com.delivery.estimate.Logger;
import com.delivery.estimate.domain.Package;
import com.delivery.estimate.domain.PackageFactory;
import com.delivery.estimate.domain.Vehicle;
import com.delivery.estimate.domain.VehicleFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class DeliveryEstimateCalculator {
    private Scanner scanner = new Scanner(System.in);
    private final InputParser inputParser;
    private final DisplayService displayService;
    private final VehicleSelectionStrategy vehicleSelectionStrategy;
    private final PackageSelectionStrategy packageSelectionStrategy;
    private List<Package> packages;
    private List<Vehicle> vehicles;

    public DeliveryEstimateCalculator(InputParser parser,
                                      DisplayService displayService,
                                      VehicleSelectionStrategy vehicleSelectionStrategy,
                                      PackageSelectionStrategy packageSelectionStrategy) {
        this.inputParser = parser;
        this.displayService = displayService;
        this.vehicleSelectionStrategy = vehicleSelectionStrategy;
        this.packageSelectionStrategy = packageSelectionStrategy;
        packages = new ArrayList<>();
        vehicles = new ArrayList<>();
    }

    public void start() {
        Logger.log("Please enter input");
        String packageInfo = scanner.nextLine();
        BigDecimal basePrice = inputParser.extractBasePrice(packageInfo);
        Integer numberOfPackages = inputParser.extractNumberOfPackages(packageInfo);
        for (int index = 0; index < numberOfPackages; index++) {
            String packageDetails = scanner.nextLine();
            String packageId = inputParser.extractPackageId(packageDetails);
            BigDecimal packageWeight = inputParser.extractPackageWeight(packageDetails);
            BigDecimal packageDistance = inputParser.extractPackageDistance(packageDetails);
            String offerCode = inputParser.extractOfferCode(packageDetails);
            packages.add(PackageFactory.createPackage(packageId, packageWeight, packageDistance, offerCode, basePrice));
        }
        String vehicleDetails = scanner.nextLine();
        Integer numberOfVehicles = inputParser.extractNumberOfVehicles(vehicleDetails);
        BigDecimal maxLoad = inputParser.extractMaxLoad(vehicleDetails);
        BigDecimal maxSpeed = inputParser.extractMaxSpeed(vehicleDetails);
        vehicles = VehicleFactory.vehicles(numberOfVehicles, maxLoad, maxSpeed);
        calculateDeliveryTime();
        displayService.displayPackageEstimates(packages);
    }

    private void calculateDeliveryTime() {
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
        return vehicle.getAvailableAt().add(packageItem.getDeliveryDistance().divide(vehicle.getMaxSpeed(), 2, RoundingMode.DOWN));
    }

    private boolean allPackagesDelivered(List<Package> packages) {
        return packages.stream().anyMatch(packageItem -> packageItem.getDeliveryTime()==null);
    }
}
