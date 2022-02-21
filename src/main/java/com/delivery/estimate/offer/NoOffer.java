package com.delivery.estimate.offer;

import com.delivery.estimate.domain.Package;

import java.math.BigDecimal;

public class NoOffer extends Offer {
    private final static String code = "NA";
    private final static BigDecimal minWeight = BigDecimal.ZERO;
    private final static BigDecimal maxWeight = BigDecimal.ZERO;
    private final static BigDecimal minDistance = BigDecimal.ZERO;
    private final static BigDecimal maxDistance = BigDecimal.ZERO;
    private final static BigDecimal percentage = BigDecimal.ZERO;

    public NoOffer() {
        super(code, minWeight, maxWeight, minDistance, maxDistance, percentage);
    }


    @Override
    public BigDecimal applyOn(Package packageItem) {
        return BigDecimal.ZERO;
    }
}
