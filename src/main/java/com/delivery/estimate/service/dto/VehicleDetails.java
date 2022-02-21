package com.delivery.estimate.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class VehicleDetails {
    private Integer numberOfVehicles;
    private BigDecimal maxSpeed;
    private BigDecimal maxLoad;
}
