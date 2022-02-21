package com.delivery.estimate.offer;

import com.delivery.estimate.domain.Package;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OfferTest {


    @Test
    void shouldReturn0AsDiscountWhenPackageDistanceIsGreaterThan199() {
        Package packageMock = mock(Package.class);
        when(packageMock.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(230));
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(100));
        Offer offer = createOffer(
                "OFR001",
                BigDecimal.valueOf(70),
                BigDecimal.valueOf(200),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(199),
                BigDecimal.valueOf(10)
        );
        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    void shouldReturn0AsDiscountWhenPackageWeightIsLessThan70() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(10));
        Offer offer = createOffer(
                "OFR001",
                BigDecimal.valueOf(70),
                BigDecimal.valueOf(200),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(199),
                BigDecimal.valueOf(10)
        );
        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    void shouldReturn0AsDiscountWhenPackageWeightIsGreaterThan200() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(250));
        Offer offer = createOffer(
                "OFR002",
                BigDecimal.valueOf(70),
                BigDecimal.valueOf(200),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(199),
                BigDecimal.valueOf(10)
        );
        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    void shouldReturn10AsDiscountWhenPackageDeliveryCostIs100AndDeliveryDistanceIsLessThan200AndWeightIsBetween70To200() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(100));
        when(packageMock.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(50));
        when(packageMock.deliveryCost()).thenReturn(BigDecimal.valueOf(100));
        Offer offer = createOffer(
                "OFR002",
                BigDecimal.valueOf(70),
                BigDecimal.valueOf(200),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(199),
                BigDecimal.valueOf(10)
        );
        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.valueOf(10.0), discount);
    }

    @Test
    void shouldReturn13AsDiscountWhenPackageDeliveryCostIs130AndDeliveryDistanceIsLessThan200AndWeightIsBetween70To200() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(111));
        when(packageMock.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(22));
        when(packageMock.deliveryCost()).thenReturn(BigDecimal.valueOf(130));

        Offer offer = createOffer(
                "OFR002",
                BigDecimal.valueOf(70),
                BigDecimal.valueOf(200),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(199),
                BigDecimal.valueOf(10)
        );

        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.valueOf(13.0), discount);
    }

    public static Offer createOffer(
            String offerCode,
            BigDecimal minWeight,
            BigDecimal maxWeight,
            BigDecimal minDistance,
            BigDecimal maxDistance,
            BigDecimal percentage
    ) {
        return new Offer(offerCode, minWeight, maxWeight, minDistance, maxDistance, percentage);
    }

    @Test
    void shouldReturn0AsDiscountWhenPackageDistanceIsGreaterThan150() {
        Package packageMock = mock(Package.class);
        when(packageMock.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(290));
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(100));
        Offer offer = createOffer(
                "OFR002",
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(250),
                BigDecimal.valueOf(50),
                BigDecimal.valueOf(150),
                BigDecimal.valueOf(7)
        );
        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    void shouldReturn0AsDiscountWhenXPackageDistanceIsLessThan50() {
        Package packageMock = mock(Package.class);
        when(packageMock.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(32));
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(100));
        Offer offer = createOffer(
                "OFR002",
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(250),
                BigDecimal.valueOf(50),
                BigDecimal.valueOf(150),
                BigDecimal.valueOf(7)
        );
        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    void shouldReturn0AsDiscountWhenPackageWeightIsLessThan100() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(99));
        Offer offer = createOffer(
                "OFR002",
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(250),
                BigDecimal.valueOf(50),
                BigDecimal.valueOf(150),
                BigDecimal.valueOf(7)
        );
        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    void shouldReturn0AsDiscountWhenPackageWeightIsGreaterThan250() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(251));
        Offer offer = createOffer(
                "OFR002",
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(250),
                BigDecimal.valueOf(50),
                BigDecimal.valueOf(150),
                BigDecimal.valueOf(7)
        );
        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    void shouldReturn7AsDiscountWhenPackageDeliveryCostIs100AndDeliveryDistanceIsBetween50To150AndWeightIsBetween100To250() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(100));
        when(packageMock.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(50));
        when(packageMock.deliveryCost()).thenReturn(BigDecimal.valueOf(100));
        Offer offer = createOffer(
                "OFR002",
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(250),
                BigDecimal.valueOf(50),
                BigDecimal.valueOf(150),
                BigDecimal.valueOf(7)
        );
        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(new BigDecimal("7.00"), discount);
    }

    @Test
    void shouldReturn9Dot10AsDiscountWhenPackageDeliveryCostIs130AndDeliveryDistanceIsBetween50To150AndWeightIsBetween100To250() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(150));
        when(packageMock.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(111));
        when(packageMock.deliveryCost()).thenReturn(BigDecimal.valueOf(130));
        Offer offer = createOffer(
                "OFR002",
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(250),
                BigDecimal.valueOf(50),
                BigDecimal.valueOf(150),
                BigDecimal.valueOf(7)
        );
        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(new BigDecimal("9.10"), discount);
    }


    @Test
    void shouldReturn0AsDiscountWhenPackageDistanceIsGreaterThan250() {
        Package packageMock = mock(Package.class);
        when(packageMock.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(290));
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(100));
        Offer offer = createOffer(
                "OFR003",
                BigDecimal.valueOf(10),
                BigDecimal.valueOf(150),
                BigDecimal.valueOf(50),
                BigDecimal.valueOf(250),
                BigDecimal.valueOf(5)
        );
        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    void shouldReturn0AsDiscountWhenPackageDistanceIsLessThan50() {
        Package packageMock = mock(Package.class);
        when(packageMock.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(32));
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(100));
        Offer offer = createOffer(
                "OFR003",
                BigDecimal.valueOf(10),
                BigDecimal.valueOf(150),
                BigDecimal.valueOf(50),
                BigDecimal.valueOf(250),
                BigDecimal.valueOf(5)
        );
        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    void shouldReturn0AsDiscountWhenPackageWeightIsLessThan10() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(8));
        Offer offer = createOffer(
                "OFR003",
                BigDecimal.valueOf(10),
                BigDecimal.valueOf(150),
                BigDecimal.valueOf(50),
                BigDecimal.valueOf(250),
                BigDecimal.valueOf(5)
        );
        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    void shouldReturn0AsDiscountWhenPackageWeightIsGreaterThan150() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(151));
        Offer offer = createOffer(
                "OFR003",
                BigDecimal.valueOf(10),
                BigDecimal.valueOf(150),
                BigDecimal.valueOf(50),
                BigDecimal.valueOf(250),
                BigDecimal.valueOf(5)
        );
        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.ZERO, discount);
    }

    @Test
    void shouldReturn5AsDiscountWhenPackageDeliveryCostIs100AndDeliveryDistanceIsBetween50To250AndWeightIsBetween10To150() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(10));
        when(packageMock.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(50));
        when(packageMock.deliveryCost()).thenReturn(BigDecimal.valueOf(100));
        Offer offer = createOffer(
                "OFR003",
                BigDecimal.valueOf(10),
                BigDecimal.valueOf(150),
                BigDecimal.valueOf(50),
                BigDecimal.valueOf(250),
                BigDecimal.valueOf(5)
        );
        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(new BigDecimal("5.00"), discount);
    }

    @Test
    void shouldReturn6Dot5AsDiscountWhenPackageDeliveryCostIs100AndDeliveryDistanceIsBetween50To250AndWeightIsBetween10To150() {
        Package packageMock = mock(Package.class);
        when(packageMock.getWeight()).thenReturn(BigDecimal.valueOf(150));
        when(packageMock.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(250));
        when(packageMock.deliveryCost()).thenReturn(BigDecimal.valueOf(130));
        Offer offer = createOffer(
                "OFR003",
                BigDecimal.valueOf(10),
                BigDecimal.valueOf(150),
                BigDecimal.valueOf(50),
                BigDecimal.valueOf(250),
                BigDecimal.valueOf(5)
        );
        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(new BigDecimal("6.50"), discount);
    }
}
