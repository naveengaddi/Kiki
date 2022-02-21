package com.delivery.estimate.service;

import com.delivery.estimate.Logger;
import com.delivery.estimate.domain.Package;
import com.delivery.estimate.domain.PackageFactory;
import com.delivery.estimate.domain.Vehicle;
import com.delivery.estimate.domain.VehicleFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DeliveryApp {
    private final InputParser inputParser;
    private final DisplayService displayService;
    private final DeliveryEstimateCalculator deliveryEstimateCalculator;
    private Scanner scanner = new Scanner(System.in);

    public DeliveryApp(InputParser inputParser, DisplayService displayService) {
        this.inputParser = inputParser;
        this.displayService = displayService;
        this.deliveryEstimateCalculator = DeliveryEstimateCalculator.create();
    }


    public void start() {
        List<Package> packages = new ArrayList<>();
        List<Vehicle> vehicles;
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
        List<Package> packageList = deliveryEstimateCalculator.estimateDelivery(packages, vehicles);
        displayService.displayPackageEstimates(packageList);
    }
}
