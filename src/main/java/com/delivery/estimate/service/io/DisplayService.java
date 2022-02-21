package com.delivery.estimate.service.io;

import com.delivery.estimate.Logger;
import com.delivery.estimate.domain.Package;

import java.util.List;

public class DisplayService {

    public void displayPackageEstimates(List<Package> packages) {
        Logger.log("----------------------");
        packages.forEach(packageItem -> {
            String packageDetails = String.format("%s %d %d %.2f", packageItem.getId(),
                    packageItem.discount().intValue(),
                    packageItem.totalCost().intValue(),
                    packageItem.getDeliveryTime()
            );
            Logger.log(packageDetails);
        });
    }
}
