package com.delivery.estimate.service.technical;

import com.delivery.estimate.domain.Package;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.List.of;

public class PackageSelectionStrategy {

    public List<Package> findPackagesWithin(BigDecimal maxCapacity, List<Package> packages) {
        if (packages.size()==1 && packages.get(0).getWeight().compareTo(maxCapacity) <= 0) {
            return packages;
        }
        if (packages.size()==1 && packages.get(0).getWeight().compareTo(maxCapacity) > 0) {
            return Collections.emptyList();
        }
        List<Package> sortedPackages = sortByWeight(packages);

        List<Package> selectedPackages = Collections.emptyList();
        int size = sortedPackages.size();
        for (int i = 0; i < size; i++) {
            List<Package> tmpPackageList = new ArrayList<>(of(sortedPackages.get(i)));
            for (int j = i + 1; j < size; j++) {
                tmpPackageList.clear();
                tmpPackageList.add(sortedPackages.get(i));
                for (int k = j; k < size; k++) {
                    Package currentPackage = sortedPackages.get(k);
                    if (isTotalWeightWithin(maxCapacity, tmpPackageList, currentPackage)) {
                        tmpPackageList.add(currentPackage);
                    } else {
                        break;
                    }
                }
                selectedPackages = findOptimalPackages(selectedPackages, tmpPackageList);
            }
            selectedPackages = findOptimalPackages(selectedPackages, tmpPackageList);
        }
        return selectedPackages;
    }

    private List<Package> sortByWeight(List<Package> packages) {
        return packages.stream().sorted(Comparator.comparing(Package::getWeight)).collect(Collectors.toList());
    }

    private List<Package> findOptimalPackages(List<Package> previousSelectedPackages, List<Package> currentSelectedPackages) {

        if (currentSelectedPackages.size() < previousSelectedPackages.size()) {
            return previousSelectedPackages;
        }
        if (currentSelectedPackages.size() > previousSelectedPackages.size()) {
            return new ArrayList<>(currentSelectedPackages);
        }
        if (isCurrentPackageWeightLessThan(previousSelectedPackages, currentSelectedPackages)) {
            return previousSelectedPackages;
        }
        if (isCurrentPackageWeightBiggerThan(previousSelectedPackages, currentSelectedPackages)) {
            return new ArrayList<>(currentSelectedPackages);
        }
        if (isCurrentPackageDistanceLessThan(previousSelectedPackages, currentSelectedPackages)) {
            return new ArrayList<>(currentSelectedPackages);
        } else {
            return previousSelectedPackages;
        }
    }

    private boolean isCurrentPackageDistanceLessThan(List<Package> selectedPackages, List<Package> tmpPackageList) {
        BigDecimal currentDistance = tmpPackageList.stream().map(Package::getDeliveryDistance).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal previousDistance = selectedPackages.stream().map(Package::getDeliveryDistance).reduce(BigDecimal.ZERO, BigDecimal::add);
        return currentDistance.compareTo(previousDistance) < 0;
    }

    private boolean isCurrentPackageWeightLessThan(List<Package> selectedPackages, List<Package> tmpPackageList) {
        BigDecimal currentWeight = tmpPackageList.stream().map(Package::getWeight).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal previousWeight = selectedPackages.stream().map(Package::getWeight).reduce(BigDecimal.ZERO, BigDecimal::add);
        return currentWeight.compareTo(previousWeight) < 0;
    }

    private boolean isCurrentPackageWeightBiggerThan(List<Package> selectedPackages, List<Package> tmpPackageList) {
        BigDecimal currentWeight = tmpPackageList.stream().map(Package::getWeight).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal previousWeight = selectedPackages.stream().map(Package::getWeight).reduce(BigDecimal.ZERO, BigDecimal::add);
        return currentWeight.compareTo(previousWeight) > 0;
    }

    private boolean isTotalWeightWithin(BigDecimal maxCapacity, List<Package> packages, Package currentPackage) {
        BigDecimal currentWeight = packages.stream().map(Package::getWeight).reduce(BigDecimal.ZERO, BigDecimal::add);
        return currentWeight.add(currentPackage.getWeight()).compareTo(maxCapacity) <= 0;
    }
}
