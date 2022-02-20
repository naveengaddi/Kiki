package com.delivery.estimate.domain;

import com.delivery.estimate.offer.NoOffer;
import com.delivery.estimate.offer.OFR001;
import com.delivery.estimate.offer.OFR003;
import com.delivery.estimate.offer.Offer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PackageTest {
    private Offer offer;

    @BeforeEach
    void setUp() {
        offer = new NoOffer();
    }

    @Test
    void shouldContainPackageIdWeightDeliveryDistanceAndBaseDeliveryCostAndOffer() {
        BigDecimal weight = BigDecimal.valueOf(100);
        BigDecimal deliveryDistance = BigDecimal.valueOf(100);
        BigDecimal baseDeliveryCost = BigDecimal.valueOf(100);
        String packageId = "PKG1";
        Package packageItem = createPackageWith(weight, deliveryDistance, baseDeliveryCost, packageId, offer);

        assertEquals(packageId, packageItem.getId());
        assertEquals(deliveryDistance, packageItem.getDeliveryDistance());
        assertEquals(baseDeliveryCost, packageItem.getBaseDeliveryCost());
        assertEquals(weight, packageItem.getWeight());
        assertEquals(offer, packageItem.getOffer());
    }

    @Test
    void shouldReturnDeliveryCostAs115() {
        BigDecimal weight = BigDecimal.valueOf(1);
        BigDecimal deliveryDistance = BigDecimal.valueOf(1);
        BigDecimal baseDeliveryCost = BigDecimal.valueOf(100);
        String packageId = "PKG1";
        Package packageItem = createPackageWith(weight, deliveryDistance, baseDeliveryCost, packageId, offer);

        BigDecimal deliveryCost = packageItem.deliveryCost();
        assertNotNull(deliveryCost);
        assertEquals(BigDecimal.valueOf(115), deliveryCost);
    }

    @Test
    void shouldReturnDeliveryCostAs205() {
        BigDecimal weight = BigDecimal.valueOf(10);
        BigDecimal deliveryDistance = BigDecimal.valueOf(1);
        BigDecimal baseDeliveryCost = BigDecimal.valueOf(100);
        String packageId = "PKG1";
        Package packageItem = createPackageWith(weight, deliveryDistance, baseDeliveryCost, packageId, offer);

        BigDecimal deliveryCost = packageItem.deliveryCost();
        assertNotNull(deliveryCost);
        assertEquals(BigDecimal.valueOf(205), deliveryCost);
    }

    @Test
    void shouldReturnDeliveryCostAs250() {
        BigDecimal weight = BigDecimal.valueOf(10);
        BigDecimal deliveryDistance = BigDecimal.valueOf(10);
        BigDecimal baseDeliveryCost = BigDecimal.valueOf(100);
        String packageId = "PKG1";
        Package packageItem = createPackageWith(weight, deliveryDistance, baseDeliveryCost, packageId, offer);

        BigDecimal deliveryCost = packageItem.deliveryCost();
        assertNotNull(deliveryCost);
        assertEquals(BigDecimal.valueOf(250), deliveryCost);
    }

    @Test
    void shouldReturnDeliveryCostAs700AndDiscountAs35AndTotalCostAs665WhenWeightIs10AndDistanceIs100OfferCode3IsApplied() {
        BigDecimal weight = BigDecimal.valueOf(10);
        BigDecimal deliveryDistance = BigDecimal.valueOf(100);
        BigDecimal baseDeliveryCost = BigDecimal.valueOf(100);
        String packageId = "PKG1";
        Offer offer3 = new OFR003();
        Package packageItem = createPackageWith(weight, deliveryDistance, baseDeliveryCost, packageId, offer3);

        assertEquals(BigDecimal.valueOf(700), packageItem.deliveryCost());
        assertEquals(new BigDecimal("35.00"), packageItem.discount());
        assertEquals(new BigDecimal("665.00"), packageItem.totalCost());
    }

    @Test
    void shouldReturnDeliveryCostAs175AndDiscountAs0AndTotalCostAs175WhenWeightIs5AndDistanceIs5OfferCode1IsApplied() {
        BigDecimal weight = BigDecimal.valueOf(5);
        BigDecimal deliveryDistance = BigDecimal.valueOf(5);
        BigDecimal baseDeliveryCost = BigDecimal.valueOf(100);
        String packageId = "PKG1";
        Offer offer1 = new OFR001();
        Package packageItem = createPackageWith(weight, deliveryDistance, baseDeliveryCost, packageId, offer1);

        assertEquals(BigDecimal.valueOf(175), packageItem.deliveryCost());
        assertEquals(BigDecimal.ZERO, packageItem.discount());
        assertEquals(BigDecimal.valueOf(175), packageItem.totalCost());
    }

    private Package createPackageWith(BigDecimal weight,
                                      BigDecimal deliveryDistance,
                                      BigDecimal baseDeliveryCost,
                                      String packageId,
                                      Offer offer
    ) {
        return new Package(packageId, weight, deliveryDistance, baseDeliveryCost, offer);
    }
}
