package com.delivery.estimate.service;

import com.delivery.estimate.Logger;
import com.delivery.estimate.domain.Package;
import com.delivery.estimate.domain.PackageFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DeliveryEstimateCalculator {
    private Scanner scanner = new Scanner(System.in);
    private final InputParser inputParser;
    private final DisplayService displayService;

    public DeliveryEstimateCalculator(InputParser parser, DisplayService displayService) {
        this.inputParser = parser;
        this.displayService = displayService;
    }

    public void start() {
        List<Package> packages = new ArrayList<>();
        Logger.log("Please enter input");
        String line1 = scanner.nextLine();
        BigDecimal basePrice = inputParser.extractBasePrice(line1);
        Integer numberOfPackages = inputParser.extractNumberOfPackages(line1);
        for (int index = 0; index < numberOfPackages; index++) {
            String input = scanner.nextLine();
            String packageId = inputParser.extractPackageId(input);
            BigDecimal packageWeight = inputParser.extractPackageWeight(input);
            BigDecimal packageDistance = inputParser.extractPackageDistance(input);
            String offerCode = inputParser.extractOfferCode(input);
            packages.add(PackageFactory.createPackage(packageId, packageWeight, packageDistance, offerCode, basePrice));
        }
        displayService.displayPackageEstimates(packages);
    }
}
