package com.delivery.estimate.offer;

import com.delivery.estimate.Package;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OFR001Test {
    private Offer offer;

    @BeforeEach
    void setUp() {
        offer = new OFR001();
    }

    @Test
    void shouldReturn0AsDiscountWhenPackageDistanceIsGreaterThan199() {
        Package packageMock = mock(Package.class);
        when(packageMock.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(230));
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(100));

        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    void shouldReturn0AsDiscountWhenPackageWeightIsLessThan70() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(10));

        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    void shouldReturn0AsDiscountWhenPackageWeightIsGreaterThan200() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(250));

        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    void shouldReturn10AsDiscountWhenPackageDeliveryCostIs100AndDeliveryDistanceIsLessThan200AndWeightIsBetween70To200() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(100));
        when(packageMock.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(50));
        when(packageMock.deliveryCost()).thenReturn(BigDecimal.valueOf(100));

        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.valueOf(10.0), discount);
    }

    @Test
    void shouldReturn13AsDiscountWhenPackageDeliveryCostIs130AndDeliveryDistanceIsLessThan200AndWeightIsBetween70To200() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(111));
        when(packageMock.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(22));
        when(packageMock.deliveryCost()).thenReturn(BigDecimal.valueOf(130));

        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.valueOf(13.0), discount);
    }
}
