package com.delivery.estimate.offer;

import com.delivery.estimate.Package;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
abstract public class Offer {
    private BigDecimal minWeight;
    private BigDecimal maxWeight;
    private BigDecimal minDistance;
    private BigDecimal maxDistance;
    private BigDecimal percentage;

    public BigDecimal applyOn(Package packageItem) {
        if (hasEligibleWeight(packageItem.getWeight()) && hasEligibleDistance(packageItem.getDeliveryDistance())) {
            return packageItem
                    .deliveryCost()
                    .multiply(percentageMultiplier());
        }
        return BigDecimal.ZERO;
    }

    private boolean hasEligibleWeight(BigDecimal weight) {
        return (maxWeight.compareTo(weight) >= 0) && (minWeight.compareTo(weight) <= 0);
    }

    private boolean hasEligibleDistance(BigDecimal distance) {
        return (maxDistance.compareTo(distance) >= 0) && (minDistance.compareTo(distance) <= 0);
    }


    private BigDecimal percentageMultiplier() {
        return percentage.divide(BigDecimal.valueOf(100));
    }
}
