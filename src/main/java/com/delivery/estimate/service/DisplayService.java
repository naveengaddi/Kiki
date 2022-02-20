package com.delivery.estimate.service;

import com.delivery.estimate.Logger;
import com.delivery.estimate.domain.Package;

import java.util.List;

public class DisplayService {

    void displayPackageEstimates(List<Package> packages) {
        Logger.log("----------------------");
        packages.forEach(packageItem -> {
            String packageDetails = String.format("%s %d %d", packageItem.getId(), packageItem.discount().intValue(), packageItem.totalCost().intValue());
            Logger.log(packageDetails);
        });
    }
}