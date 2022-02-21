package com.delivery.estimate.service.io.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class BasePriceAndPackageCount {
    private BigDecimal basePrice;
    private Integer numberOfPackages;
}
