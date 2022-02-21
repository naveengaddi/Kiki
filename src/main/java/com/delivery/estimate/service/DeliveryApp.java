package com.delivery.estimate.service;

import com.delivery.estimate.Logger;
import com.delivery.estimate.domain.Package;
import com.delivery.estimate.domain.PackageFactory;
import com.delivery.estimate.domain.Vehicle;
import com.delivery.estimate.domain.VehicleFactory;
import com.delivery.estimate.service.dto.BasePriceAndPackageCount;
import com.delivery.estimate.service.dto.PackageDetails;
import com.delivery.estimate.service.dto.VehicleDetails;
import com.delivery.estimate.service.io.DisplayService;
import com.delivery.estimate.service.io.InputParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DeliveryApp {
    private final InputParser inputParser;
    private final DisplayService displayService;
    private final DeliveryEstimateService deliveryEstimateService;
    private Scanner scanner = new Scanner(System.in);

    public DeliveryApp(InputParser inputParser, DisplayService displayService) {
        this.inputParser = inputParser;
        this.displayService = displayService;
        this.deliveryEstimateService = DeliveryEstimateService.create();
    }


    public void start() {
        Logger.log("Please enter input");
        List<Package> packages = readPackagesInput();
        List<Vehicle> vehicles = readVehicleInput();
        List<Package> packageList = deliveryEstimateService.estimateDelivery(packages, vehicles);
        displayService.displayPackageEstimates(packageList);
    }

    private List<Vehicle> readVehicleInput() {
        List<Vehicle> vehicles;
        String vehicleDetails = scanner.nextLine();
        VehicleDetails vehicleDetails1 = inputParser.parseVehicleDetails(vehicleDetails);
        vehicles = VehicleFactory.vehicles(vehicleDetails1.getNumberOfVehicles(),
                vehicleDetails1.getMaxLoad(),
                vehicleDetails1.getMaxSpeed());
        return vehicles;
    }

    private List<Package> readPackagesInput() {
        String packageInfo = scanner.nextLine();
        BasePriceAndPackageCount priceAndPackageCount = inputParser.parseBasePriceAndPackageCount(packageInfo);
        List<Package> packages = new ArrayList<>();
        for (int index = 0; index < priceAndPackageCount.getNumberOfPackages(); index++) {
            String packageDetails = scanner.nextLine();
            PackageDetails packageDetails1 = inputParser.parsePackageDetails(packageDetails);
            packages.add(PackageFactory.createPackage(packageDetails1.getId(),
                    packageDetails1.getWeight(),
                    packageDetails1.getDeliveryDistance(),
                    packageDetails1.getOfferCode(),
                    priceAndPackageCount.getBasePrice()));
        }
        return packages;
    }
}
