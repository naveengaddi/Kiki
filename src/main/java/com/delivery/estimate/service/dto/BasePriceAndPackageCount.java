package com.delivery.estimate.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class BasePriceAndPackageCount {
    private BigDecimal basePrice;
    private Integer numberOfPackages;
}
