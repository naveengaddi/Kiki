package com.delivery.estimate.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VehicleFactoryTest {
    @Test
    void shouldReturn1Vehicle() {
        List<Vehicle> vehicles = VehicleFactory.vehicles(1, BigDecimal.TEN, BigDecimal.TEN);
        assertEquals(1, vehicles.size());
        assertEquals(BigDecimal.TEN, vehicles.get(0).getMaxSpeed());
        assertEquals(BigDecimal.TEN, vehicles.get(0).getMaxLoad());
    }

    @Test
    void shouldReturn3Vehicle() {
        List<Vehicle> vehicles = VehicleFactory.vehicles(3, BigDecimal.TEN, BigDecimal.TEN);
        assertEquals(3, vehicles.size());
        assertEquals(BigDecimal.TEN, vehicles.get(0).getMaxSpeed());
        assertEquals(BigDecimal.TEN, vehicles.get(0).getMaxLoad());
    }

    @Test
    void shouldReturn0Vehicle() {
        List<Vehicle> vehicles = VehicleFactory.vehicles(0, BigDecimal.TEN, BigDecimal.TEN);
        assertEquals(0, vehicles.size());
    }
}
