package com.delivery.estimate.offer;

import com.delivery.estimate.Package;

import java.math.BigDecimal;

public class OFR002 {
    private static final BigDecimal MIN_WEIGHT = BigDecimal.valueOf(100);
    private static final BigDecimal MAX_WEIGHT = BigDecimal.valueOf(250);
    private static final BigDecimal MIN_DISTANCE = BigDecimal.valueOf(50);
    private static final BigDecimal MAX_DISTANCE = BigDecimal.valueOf(150);
    private static final BigDecimal PERCENTAGE = BigDecimal.valueOf(7);


    public BigDecimal applyOn(Package packageItem) {
        if (hasEligibleWeight(packageItem.getWeight()) && hasEligibleDistance(packageItem.getDeliveryDistance())) {
            return packageItem
                    .deliveryCost()
                    .multiply(percentageMultiplier());
        }
        return BigDecimal.ZERO;
    }

    private boolean hasEligibleWeight(BigDecimal weight) {
        return (MAX_WEIGHT.compareTo(weight) >= 0) && (MIN_WEIGHT.compareTo(weight) <= 0);
    }

    private boolean hasEligibleDistance(BigDecimal distance) {
        return (MAX_DISTANCE.compareTo(distance) >= 0) && (MIN_DISTANCE.compareTo(distance) <= 0);
    }


    private BigDecimal percentageMultiplier() {
        return PERCENTAGE.divide(BigDecimal.valueOf(100));
    }
}
