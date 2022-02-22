package com.delivery.estimate.service.technical;

import com.delivery.estimate.domain.Package;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PackageSelectionStrategy {

    public List<Package> findPackagesWithin(BigDecimal maxCapacity, List<Package> packages) {
        if (packages.size()==1 && packages.get(0).getWeight().compareTo(maxCapacity) <= 0) {
            return packages;
        }
        if (packages.size()==1 && packages.get(0).getWeight().compareTo(maxCapacity) > 0) {
            return Collections.emptyList();
        }
        List<Package> sortedPackages = sortByWeight(packages);

        Shipment selectedShipment = new Shipment();
        int size = sortedPackages.size();
        for (int i = 0; i < size; i++) {
            Shipment currentShipment = new Shipment();
            currentShipment.add(sortedPackages.get(i));
            for (int j = i + 1; j < size; j++) {
                currentShipment.clear();
                currentShipment.add(sortedPackages.get(i));
                for (int k = j; k < size; k++) {
                    Package currentPackage = sortedPackages.get(k);
                    if (isTotalWeightWithin(maxCapacity, currentShipment, currentPackage)) {
                        currentShipment.add(currentPackage);
                    } else {
                        break;
                    }
                }
                if (currentShipment.isBetterThan(selectedShipment)) {
                    selectedShipment = new Shipment(currentShipment);
                }
            }
            if (currentShipment.isBetterThan(selectedShipment)) {
                selectedShipment = new Shipment(currentShipment);
            }
        }
        return selectedShipment;
    }

    private List<Package> sortByWeight(List<Package> packages) {
        return packages.stream().sorted(Comparator.comparing(Package::getWeight)).collect(Collectors.toList());
    }

    private boolean isTotalWeightWithin(BigDecimal maxCapacity, Shipment shipment, Package currentPackage) {
        BigDecimal currentWeight = shipment.getTotalWeight();
        return currentWeight.add(currentPackage.getWeight()).compareTo(maxCapacity) <= 0;
    }
}
