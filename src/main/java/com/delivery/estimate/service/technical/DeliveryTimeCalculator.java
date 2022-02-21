package com.delivery.estimate.service.technical;

import com.delivery.estimate.domain.Package;
import com.delivery.estimate.domain.Vehicle;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DeliveryTimeCalculator {

    public BigDecimal calculate(Vehicle vehicle, Package packageItem) {
        return vehicle.getAvailableAt()
                .add(packageItem.getDeliveryDistance().divide(vehicle.getMaxSpeed(), 2, RoundingMode.DOWN));
    }
}
