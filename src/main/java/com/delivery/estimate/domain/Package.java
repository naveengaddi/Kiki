package com.delivery.estimate.domain;

import com.delivery.estimate.offer.Offer;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Package {
    private static final int WEIGHT_MULTIPLIER = 10;
    private static final int DISTANCE_MULTIPLIER = 5;

    private  String id;
    private  BigDecimal weight;
    private  BigDecimal deliveryDistance;
    private  BigDecimal baseDeliveryCost;
    private final Offer offer;

    public BigDecimal discount() {
        return offer.applyOn(this);
    }

    public BigDecimal totalCost() {
        return deliveryCost().subtract(discount());
    }

    public BigDecimal deliveryCost() {
        BigDecimal weightCost = calculateCostFor(weight, WEIGHT_MULTIPLIER);
        BigDecimal distanceCost = calculateCostFor(deliveryDistance, DISTANCE_MULTIPLIER);
        return baseDeliveryCost.add(weightCost).add(distanceCost);
    }

    private BigDecimal calculateCostFor(BigDecimal value, int multiplier) {
        return value.multiply(BigDecimal.valueOf(multiplier));
    }
}
