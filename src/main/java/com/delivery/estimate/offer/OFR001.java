package com.delivery.estimate.offer;

import java.math.BigDecimal;

public class OFR001 extends Offer {
    private static final BigDecimal MIN_WEIGHT = BigDecimal.valueOf(70);
    private static final BigDecimal MAX_WEIGHT = BigDecimal.valueOf(200);
    private static final BigDecimal MIN_DISTANCE = BigDecimal.valueOf(0);
    private static final BigDecimal MAX_DISTANCE = BigDecimal.valueOf(199);
    private static final BigDecimal PERCENTAGE = BigDecimal.valueOf(10);

    public OFR001() {
        super(MIN_WEIGHT, MAX_WEIGHT, MIN_DISTANCE, MAX_DISTANCE, PERCENTAGE);
    }
}
