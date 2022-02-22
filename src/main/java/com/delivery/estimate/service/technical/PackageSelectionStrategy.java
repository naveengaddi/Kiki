package com.delivery.estimate.service.technical;

import com.delivery.estimate.domain.Package;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PackageSelectionStrategy {

    public List<Package> findPackagesWithin(BigDecimal maxCapacity, List<Package> packages) {

        if (packages.size()==1 && packages.get(0).getWeight().compareTo(maxCapacity) > 0) {
            return Collections.emptyList();
        }
        List<Package> sortedPackages = sortByWeight(packages);

        Shipment selectedShipment = new Shipment(maxCapacity);
        int size = sortedPackages.size();
        for (int i = 0; i < size; i++) {
            Shipment currentShipment = new Shipment(maxCapacity);
            Package selectedPackage = sortedPackages.get(i);
            currentShipment.add(selectedPackage);
            selectedShipment = selectBetterShipment(selectedShipment, currentShipment);
            for (int j = i + 1; j < size; j++) {
                currentShipment.addAll(sortedPackages.subList(j, size));
                selectedShipment = selectBetterShipment(selectedShipment, currentShipment);
                currentShipment.clear();
                currentShipment.add(selectedPackage);
            }
        }
        return selectedShipment;
    }

    private Shipment selectBetterShipment(Shipment selectedShipment, Shipment currentShipment) {
        if (currentShipment.isBetterThan(selectedShipment)) {
            selectedShipment = new Shipment(currentShipment);
        }
        return selectedShipment;
    }

    private List<Package> sortByWeight(List<Package> packages) {
        return packages.stream().sorted(Comparator.comparing(Package::getWeight)).collect(Collectors.toList());
    }
}
