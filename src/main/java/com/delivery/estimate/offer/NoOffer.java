package com.delivery.estimate.offer;

import com.delivery.estimate.Package;

import java.math.BigDecimal;

public class NoOffer extends Offer {

    @Override
    public BigDecimal applyOn(Package packageItem) {
        return BigDecimal.ZERO;
    }
}
