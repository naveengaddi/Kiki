package com.delivery.estimate.offer;

import java.math.BigDecimal;

public class OFR003 extends Offer {
    private static final BigDecimal MIN_WEIGHT = BigDecimal.valueOf(10);
    private static final BigDecimal MAX_WEIGHT = BigDecimal.valueOf(150);
    private static final BigDecimal MIN_DISTANCE = BigDecimal.valueOf(50);
    private static final BigDecimal MAX_DISTANCE = BigDecimal.valueOf(250);
    private static final BigDecimal PERCENTAGE = BigDecimal.valueOf(5);

    public OFR003() {
        super(MIN_WEIGHT, MAX_WEIGHT, MIN_DISTANCE, MAX_DISTANCE, PERCENTAGE);
    }
}
