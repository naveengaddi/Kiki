package com.delivery.estimate;

import com.delivery.estimate.offer.NoOffer;
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

    private Package createPackageWith(BigDecimal weight,
                                      BigDecimal deliveryDistance,
                                      BigDecimal baseDeliveryCost,
                                      String packageId,
                                      Offer offer
    ) {
        return new Package(packageId, weight, deliveryDistance, baseDeliveryCost, offer);
    }
}
