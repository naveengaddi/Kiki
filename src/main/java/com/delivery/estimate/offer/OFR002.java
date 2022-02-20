package com.delivery.estimate.offer;

import java.math.BigDecimal;

public class OFR002 extends Offer {
    private static final BigDecimal MIN_WEIGHT = BigDecimal.valueOf(100);
    private static final BigDecimal MAX_WEIGHT = BigDecimal.valueOf(250);
    private static final BigDecimal MIN_DISTANCE = BigDecimal.valueOf(50);
    private static final BigDecimal MAX_DISTANCE = BigDecimal.valueOf(150);
    private static final BigDecimal PERCENTAGE = BigDecimal.valueOf(7);

    public OFR002() {
        super(MIN_WEIGHT, MAX_WEIGHT, MIN_DISTANCE, MAX_DISTANCE, PERCENTAGE);
    }
}
