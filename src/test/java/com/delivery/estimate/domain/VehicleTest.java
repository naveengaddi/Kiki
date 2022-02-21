package com.delivery.estimate.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleTest {

    @Test
    void shouldContainMaxLoadAndMaxSpeedAndAvailableAt() {
        BigDecimal maxLoad = BigDecimal.valueOf(200);
        BigDecimal maxSpeed = BigDecimal.valueOf(70);
        BigDecimal availableAt = BigDecimal.valueOf(0.0);

        Vehicle vehicle = new Vehicle(maxLoad, maxSpeed, availableAt);
        assertEquals(maxLoad, vehicle.getMaxLoad());
        assertEquals(maxSpeed, vehicle.getMaxSpeed());
        assertEquals(availableAt, vehicle.getAvailableAt());
    }

    @Test
    void shouldUpdateVehicleAvailableAt() {
        BigDecimal maxLoad = BigDecimal.valueOf(200);
        BigDecimal maxSpeed = BigDecimal.valueOf(70);
        BigDecimal availableAt = BigDecimal.valueOf(0.0);

        Vehicle vehicle = new Vehicle(maxLoad, maxSpeed, availableAt);
        vehicle.updateAvailableTime(BigDecimal.ONE);
        assertEquals(new BigDecimal("1.0"), vehicle.getAvailableAt());
    }
}
