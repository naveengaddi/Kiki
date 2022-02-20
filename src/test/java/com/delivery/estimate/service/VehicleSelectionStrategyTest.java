package com.delivery.estimate.service;

import com.delivery.estimate.domain.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VehicleSelectionStrategyTest {
    private VehicleSelectionStrategy vehicleSelectionStrategy;

    @BeforeEach
    void setUp() {
        vehicleSelectionStrategy = new VehicleSelectionStrategy();
    }

    @Test
    void shouldReturnSameVehicleIfOnlyOneVehicleExists() {
        Vehicle vehicle = mock(Vehicle.class);
        List<Vehicle> vehicles = List.of(vehicle);

        Vehicle sameVehicle = vehicleSelectionStrategy.findVehicleWithMinimumWaitTime(vehicles);
        assertEquals(vehicle, sameVehicle);
    }

    @Test
    void shouldReturnVehicleWithAvailableAt10WhenTwoVehiclesWithAvailableAt20And10Exists() {
        Vehicle vehicle1 = mock(Vehicle.class);
        Vehicle vehicle2 = mock(Vehicle.class);
        when(vehicle1.getAvailableAt()).thenReturn(BigDecimal.valueOf(20));
        when(vehicle2.getAvailableAt()).thenReturn(BigDecimal.TEN);
        List<Vehicle> vehicles = List.of(vehicle1, vehicle2);

        Vehicle actualVehicle = vehicleSelectionStrategy.findVehicleWithMinimumWaitTime(vehicles);
        assertEquals(vehicle2, actualVehicle);
    }

    @Test
    void shouldReturnVehicleWith2AvailableAtWhen5VehiclesWithAvailableAt3And1And2Exists() {
        Vehicle vehicle1 = mock(Vehicle.class);
        Vehicle vehicle2 = mock(Vehicle.class);
        Vehicle vehicle3 = mock(Vehicle.class);
        when(vehicle1.getAvailableAt()).thenReturn(BigDecimal.valueOf(3));
        when(vehicle2.getAvailableAt()).thenReturn(BigDecimal.ONE);
        when(vehicle3.getAvailableAt()).thenReturn(BigDecimal.valueOf(2));
        List<Vehicle> vehicles = List.of(vehicle1, vehicle2);

        Vehicle actualVehicle = vehicleSelectionStrategy.findVehicleWithMinimumWaitTime(vehicles);
        assertEquals(vehicle2, actualVehicle);
    }
}
