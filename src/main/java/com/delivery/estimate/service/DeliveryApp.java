package com.delivery.estimate.service;

import com.delivery.estimate.Logger;
import com.delivery.estimate.domain.Package;
import com.delivery.estimate.domain.PackageFactory;
import com.delivery.estimate.domain.Vehicle;
import com.delivery.estimate.domain.VehicleFactory;
import com.delivery.estimate.service.dto.BasePriceAndPackageCount;
import com.delivery.estimate.service.dto.PackageDetails;
import com.delivery.estimate.service.dto.VehicleDetails;

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
        BasePriceAndPackageCount priceAndPackageCount = inputParser.parseBasePriceAndPackageCount(packageInfo);
        for (int index = 0; index < priceAndPackageCount.getNumberOfPackages(); index++) {
            String packageDetails = scanner.nextLine();
            PackageDetails packageDetails1 = inputParser.parsePackageDetails(packageDetails);
            packages.add(PackageFactory.createPackage(packageDetails1.getId(),
                    packageDetails1.getWeight(),
                    packageDetails1.getDeliveryDistance(),
                    packageDetails1.getOfferCode(),
                    priceAndPackageCount.getBasePrice()));
        }
        String vehicleDetails = scanner.nextLine();
        VehicleDetails vehicleDetails1 = inputParser.parseVehicleDetails(vehicleDetails);
        vehicles = VehicleFactory.vehicles(vehicleDetails1.getNumberOfVehicles(),
                vehicleDetails1.getMaxLoad(),
                vehicleDetails1.getMaxSpeed());
        List<Package> packageList = deliveryEstimateCalculator.estimateDelivery(packages, vehicles);
        displayService.displayPackageEstimates(packageList);
    }
}
