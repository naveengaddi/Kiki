package com.delivery.estimate.service;

import com.delivery.estimate.domain.Package;
import com.delivery.estimate.domain.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeliveryTimeCalculatorTest {
    private DeliveryTimeCalculator deliveryTimeCalculator;

    @BeforeEach
    void setUp() {
        deliveryTimeCalculator = new DeliveryTimeCalculator();
    }

    @Test
    void shouldReturnDeliveryTimeAs1Dot78() {
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getAvailableAt()).thenReturn(BigDecimal.ZERO);
        when(vehicle.getMaxLoad()).thenReturn(BigDecimal.valueOf(200));
        when(vehicle.getMaxSpeed()).thenReturn(BigDecimal.valueOf(70));

        Package packageItem = mock(Package.class);
        when(packageItem.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(125));

        BigDecimal deliveryTime = deliveryTimeCalculator.calculate(vehicle, packageItem);

        assertEquals(new BigDecimal("1.78"), deliveryTime);
    }

    @Test
    void shouldReturnDeliveryTimeAs0Dot85() {
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getAvailableAt()).thenReturn(BigDecimal.ZERO);
        when(vehicle.getMaxLoad()).thenReturn(BigDecimal.valueOf(200));
        when(vehicle.getMaxSpeed()).thenReturn(BigDecimal.valueOf(70));

        Package packageItem = mock(Package.class);
        when(packageItem.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(60));

        BigDecimal deliveryTime = deliveryTimeCalculator.calculate(vehicle, packageItem);

        assertEquals(new BigDecimal("0.85"), deliveryTime);
    }

    @Test
    void shouldReturnDeliveryTimeAs3Dot98() {
        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getAvailableAt()).thenReturn(new BigDecimal("3.56"));
        when(vehicle.getMaxLoad()).thenReturn(BigDecimal.valueOf(200));
        when(vehicle.getMaxSpeed()).thenReturn(BigDecimal.valueOf(70));

        Package packageItem = mock(Package.class);
        when(packageItem.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(30));

        BigDecimal deliveryTime = deliveryTimeCalculator.calculate(vehicle, packageItem);

        assertEquals(new BigDecimal("3.98"), deliveryTime);
    }
}
