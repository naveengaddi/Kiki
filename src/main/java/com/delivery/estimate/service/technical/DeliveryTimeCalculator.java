package com.delivery.estimate.service.technical;

import com.delivery.estimate.domain.Package;
import com.delivery.estimate.domain.Vehicle;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DeliveryTimeCalculator {

    public BigDecimal calculate(Vehicle vehicle, Package packageItem) {
        BigDecimal deliveryDistance = packageItem.getDeliveryDistance();
        BigDecimal maxSpeed = vehicle.getMaxSpeed();
        BigDecimal availableAt = vehicle.getAvailableAt();
        return availableAt.plus().add(deliveryDistance.divide(maxSpeed, 2, RoundingMode.DOWN));

    }
}
