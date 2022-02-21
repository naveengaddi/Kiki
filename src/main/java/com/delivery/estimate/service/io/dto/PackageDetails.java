package com.delivery.estimate.service.io.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class PackageDetails {
    private String id;
    private BigDecimal weight;
    private BigDecimal deliveryDistance;
    private String offerCode;
}
