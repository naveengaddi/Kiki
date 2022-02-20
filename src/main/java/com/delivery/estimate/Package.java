package com.delivery.estimate;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Package {
    private String id;
    private BigDecimal weight;
    private BigDecimal deliveryDistance;
    private BigDecimal baseDeliveryCost;
}
