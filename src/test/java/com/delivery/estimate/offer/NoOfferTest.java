package com.delivery.estimate.offer;

import com.delivery.estimate.Package;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class NoOfferTest {
    private Offer offer;

    @BeforeEach
    void setUp() {
        offer = new NoOffer();
    }

    @Test
    void shouldReturn0AsDiscount() {
        Package packageMock = mock(Package.class);

        BigDecimal discount = offer.applyOn(packageMock);

        assertEquals(BigDecimal.ZERO, discount);
    }
}
