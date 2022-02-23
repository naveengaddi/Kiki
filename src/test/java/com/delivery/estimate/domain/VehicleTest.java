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
    void shouldUpdateVehicleAvailableAtTimes2() {
        BigDecimal maxLoad = BigDecimal.valueOf(200);
        BigDecimal maxSpeed = BigDecimal.valueOf(70);
        BigDecimal availableAt = BigDecimal.valueOf(0.0);

        Vehicle vehicle = new Vehicle(maxLoad, maxSpeed, availableAt);
        vehicle.updateAvailableTimeFrom(BigDecimal.ONE);
        assertEquals(new BigDecimal("2.0"), vehicle.getAvailableAt());
    }

    @Test
    void shouldUpdateVehicleAvailableAtTimes2PlusLastAvailableTime() {
        BigDecimal maxLoad = BigDecimal.valueOf(200);
        BigDecimal maxSpeed = BigDecimal.valueOf(70);
        BigDecimal availableAt = BigDecimal.valueOf(2.3);

        Vehicle vehicle = new Vehicle(maxLoad, maxSpeed, availableAt);
        vehicle.updateAvailableTimeFrom(BigDecimal.valueOf(2.5));
        assertEquals(new BigDecimal("7.3"), vehicle.getAvailableAt());
    }
}
