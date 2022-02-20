package com.delivery.estimate.offer;

import com.delivery.estimate.Package;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OFR003Test {
    private Offer offer;

    @BeforeEach
    void setUp() {
        offer = new OFR003();
    }

    @Test
    void shouldReturn0AsDiscountWhenPackageDistanceIsGreaterThan250() {
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
    void shouldReturn0AsDiscountWhenPackageWeightIsLessThan10() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(8));

        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    void shouldReturn0AsDiscountWhenPackageWeightIsGreaterThan150() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(151));

        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    void shouldReturn5AsDiscountWhenPackageDeliveryCostIs100AndDeliveryDistanceIsBetween50To250AndWeightIsBetween10To150() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(10));
        when(packageMock.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(50));
        when(packageMock.deliveryCost()).thenReturn(BigDecimal.valueOf(100));

        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(new BigDecimal("5.00"), discount);
    }

    @Test
    void shouldReturn6Dot5AsDiscountWhenPackageDeliveryCostIs100AndDeliveryDistanceIsBetween50To250AndWeightIsBetween10To150() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(150));
        when(packageMock.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(250));
        when(packageMock.deliveryCost()).thenReturn(BigDecimal.valueOf(130));

        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(new BigDecimal("6.50"), discount);
    }
}
