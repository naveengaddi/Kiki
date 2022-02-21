package com.delivery.estimate.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Vehicle {
    private final BigDecimal maxLoad;
    private final BigDecimal maxSpeed;
    private BigDecimal availableAt;

    public void updateAvailableTime(BigDecimal nextAvailableTime) {
        this.availableAt = this.availableAt.add(nextAvailableTime);
    }
}
