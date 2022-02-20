package com.delivery.estimate.service;

import com.delivery.estimate.domain.Package;
import com.google.common.collect.Sets;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PackageSelectionStrategy {

    public List<Package> findPackagesWithin(BigDecimal maxCapacity, List<Package> packages) {
        Set<Set<Package>> powerSet = getPowerSet(packages);
        Set<Package> selectedPackages = null;
        BigDecimal maxWeightSoFar = BigDecimal.ZERO;
        int maxPackagesSoFar = Integer.MIN_VALUE;
        BigDecimal minDistanceSoFar = BigDecimal.valueOf(Double.MAX_VALUE);
        for (Set<Package> packageSet : powerSet) {
            if (packageSet.isEmpty()) continue;
            BigDecimal totalWeight = getTotalWeight(packageSet);
            BigDecimal totalDistance = getTotalDistance(packageSet);
            if (isTotalWeightWithin(maxCapacity, totalWeight)) {
                if (isPackageSizeGreaterThan(maxPackagesSoFar, packageSet)) {
                    selectedPackages = packageSet;
                    maxWeightSoFar = totalWeight;
                    minDistanceSoFar = totalDistance;
                    maxPackagesSoFar = packageSet.size();
                } else if (packageSet.size()==maxPackagesSoFar) {
                    if (isTotalWeightGreaterThan(maxWeightSoFar, totalWeight)) {
                        selectedPackages = packageSet;
                        maxWeightSoFar = totalWeight;
                        minDistanceSoFar = totalDistance;
                    } else if (totalWeight.compareTo(maxWeightSoFar)==0) {
                        if (isTotalDistanceLessThan(minDistanceSoFar, totalDistance)) {
                            selectedPackages = packageSet;
                            maxWeightSoFar = totalWeight;
                            minDistanceSoFar = totalDistance;
                        }
                    }
                }
            }
        }
        return listOf(selectedPackages);
    }

    private boolean isTotalDistanceLessThan(BigDecimal minDistanceSoFar, BigDecimal totalDistance) {
        return totalDistance.compareTo(minDistanceSoFar) < 0;
    }

    private boolean isPackageSizeGreaterThan(int maxPackagesSoFar, Set<Package> packageSet) {
        return packageSet.size() > maxPackagesSoFar;
    }

    private boolean isTotalWeightGreaterThan(BigDecimal maxWeightSoFar, BigDecimal totalWeight) {
        return totalWeight.compareTo(maxWeightSoFar) > 0;
    }

    private List<Package> listOf(Set<Package> maxWeightPackages) {
        if (maxWeightPackages==null) {
            return List.of();
        }
        return new ArrayList<>(maxWeightPackages);
    }

    private boolean isTotalWeightWithin(BigDecimal maxCapacity, BigDecimal totalWeight) {
        return totalWeight.compareTo(maxCapacity) <= 0;
    }

    private BigDecimal getTotalDistance(Set<Package> packageSet) {
        return packageSet.stream().map(Package::getDeliveryDistance).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getTotalWeight(Set<Package> packageSet) {
        return packageSet.stream().map(Package::getWeight).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Set<Set<Package>> getPowerSet(List<Package> packages) {
        Set<Package> setOfPackages = new TreeSet<>(Comparator.comparing(Package::getId));
        setOfPackages.addAll(packages);
        return Sets.powerSet(setOfPackages);
    }
}
