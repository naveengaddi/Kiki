package com.delivery.estimate.service;

import com.delivery.estimate.offer.Offer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OfferServiceTest {
    private OfferService offerService;

    @BeforeEach
    void setUp() {
        offerService = new OfferService();
    }

    @Test
    void shouldReturnOfferWithOfferCodeOFR001() {
        BigDecimal minWeight = BigDecimal.valueOf(70);
        BigDecimal maxWeight = BigDecimal.valueOf(200);
        BigDecimal minDistance = BigDecimal.valueOf(0);
        BigDecimal maxDistance = BigDecimal.valueOf(199);
        BigDecimal percentage = BigDecimal.valueOf(10);

        Offer offer = offerService.getOffer("OFR001");
        assertNotNull(offer);
        assertEquals(minWeight, offer.getMinWeight());
        assertEquals(maxWeight, offer.getMaxWeight());
        assertEquals(minDistance, offer.getMinDistance());
        assertEquals(maxDistance, offer.getMaxDistance());
        assertEquals(percentage, offer.getPercentage());
    }

    @Test
    void shouldReturnOfferWithOfferCodeOFR002() {
        BigDecimal minWeight = BigDecimal.valueOf(100);
        BigDecimal maxWeight = BigDecimal.valueOf(250);
        BigDecimal minDistance = BigDecimal.valueOf(50);
        BigDecimal maxDistance = BigDecimal.valueOf(150);
        BigDecimal percentage = BigDecimal.valueOf(7);

        Offer offer = offerService.getOffer("OFR002");
        assertNotNull(offer);
        assertEquals(minWeight, offer.getMinWeight());
        assertEquals(maxWeight, offer.getMaxWeight());
        assertEquals(minDistance, offer.getMinDistance());
        assertEquals(maxDistance, offer.getMaxDistance());
        assertEquals(percentage, offer.getPercentage());
    }

    @Test
    void shouldReturnOfferWithOfferCodeOFR003() {
        BigDecimal minWeight = BigDecimal.valueOf(10);
        BigDecimal maxWeight = BigDecimal.valueOf(150);
        BigDecimal minDistance = BigDecimal.valueOf(50);
        BigDecimal maxDistance = BigDecimal.valueOf(250);
        BigDecimal percentage = BigDecimal.valueOf(5);

        Offer offer = offerService.getOffer("OFR003");
        assertNotNull(offer);
        assertEquals(minWeight, offer.getMinWeight());
        assertEquals(maxWeight, offer.getMaxWeight());
        assertEquals(minDistance, offer.getMinDistance());
        assertEquals(maxDistance, offer.getMaxDistance());
        assertEquals(percentage, offer.getPercentage());
    }

    @Test
    void shouldReturnNoOfferWithOfferCodeIsNotExists() {
        BigDecimal minWeight = BigDecimal.valueOf(0);
        BigDecimal maxWeight = BigDecimal.valueOf(0);
        BigDecimal minDistance = BigDecimal.valueOf(0);
        BigDecimal maxDistance = BigDecimal.valueOf(0);
        BigDecimal percentage = BigDecimal.valueOf(0);

        Offer offer = offerService.getOffer("no");
        assertNotNull(offer);
        assertEquals(minWeight, offer.getMinWeight());
        assertEquals(maxWeight, offer.getMaxWeight());
        assertEquals(minDistance, offer.getMinDistance());
        assertEquals(maxDistance, offer.getMaxDistance());
        assertEquals(percentage, offer.getPercentage());
    }
}
