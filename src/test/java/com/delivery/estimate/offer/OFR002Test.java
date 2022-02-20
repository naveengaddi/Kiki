package com.delivery.estimate.offer;

import com.delivery.estimate.domain.Package;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OFR002Test {
    private Offer offer;

    @BeforeEach
    void setUp() {
        offer = new OFR002();
    }

    @Test
    void shouldReturn0AsDiscountWhenPackageDistanceIsGreaterThan150() {
        Package packageMock = mock(Package.class);
        when(packageMock.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(290));
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(100));

        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    void shouldReturn0AsDiscountWhenPackageDistanceIsLessThan50() {
        Package packageMock = mock(Package.class);
        when(packageMock.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(32));
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(100));

        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    void shouldReturn0AsDiscountWhenPackageWeightIsLessThan100() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(99));

        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    void shouldReturn0AsDiscountWhenPackageWeightIsGreaterThan250() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(251));

        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    void shouldReturn7AsDiscountWhenPackageDeliveryCostIs100AndDeliveryDistanceIsBetween50To150AndWeightIsBetween100To250() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(100));
        when(packageMock.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(50));
        when(packageMock.deliveryCost()).thenReturn(BigDecimal.valueOf(100));

        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(new BigDecimal("7.00"), discount);
    }

    @Test
    void shouldReturn9Dot10AsDiscountWhenPackageDeliveryCostIs130AndDeliveryDistanceIsBetween50To150AndWeightIsBetween100To250() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(150));
        when(packageMock.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(111));
        when(packageMock.deliveryCost()).thenReturn(BigDecimal.valueOf(130));

        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(new BigDecimal("9.10"), discount);
    }
}
