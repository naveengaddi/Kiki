package com.delivery.estimate.service.io;

import com.delivery.estimate.exception.InputInvalidException;
import com.delivery.estimate.service.dto.BasePriceAndPackageCount;
import com.delivery.estimate.service.dto.PackageDetails;
import com.delivery.estimate.service.dto.VehicleDetails;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {

    public BasePriceAndPackageCount parseBasePriceAndPackageCount(String input) {
        Pattern pattern = Pattern.compile("(\\d+) (\\d+)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            BigDecimal basePrice = new BigDecimal(matcher.group(1));
            Integer numberOfPackages = Integer.parseInt(matcher.group(2));
            return BasePriceAndPackageCount.builder()
                    .basePrice(basePrice)
                    .numberOfPackages(numberOfPackages).build();
        } else {
            throw new InputInvalidException("Provided input format is incorrect");
        }
    }

    public VehicleDetails parseVehicleDetails(String input) {
        Pattern pattern = Pattern.compile("(\\d+) (\\d+) (\\d+)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            Integer numberOfVehicles = Integer.parseInt(matcher.group(1));
            BigDecimal maxSpeed = new BigDecimal(matcher.group(2));
            BigDecimal maxLoad = new BigDecimal(matcher.group(3));
            return VehicleDetails.builder()
                    .numberOfVehicles(numberOfVehicles)
                    .maxSpeed(maxSpeed)
                    .maxLoad(maxLoad)
                    .build();
        } else {
            throw new InputInvalidException("Provided input format is incorrect");
        }
    }

    public PackageDetails parsePackageDetails(String input) {
        Pattern pattern = Pattern.compile("(\\w+) (\\d+) (\\d+) (\\w+)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            String packageId = matcher.group(1);
            BigDecimal weight = new BigDecimal(matcher.group(2));
            BigDecimal deliveryDistance = new BigDecimal(matcher.group(3));
            String offerCode = matcher.group(4);
            return PackageDetails.builder()
                    .id(packageId)
                    .weight(weight)
                    .deliveryDistance(deliveryDistance)
                    .offerCode(offerCode)
                    .build();
        } else {
            throw new InputInvalidException("Provided input format is incorrect");
        }
    }
}
