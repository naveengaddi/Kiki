package com.delivery.estimate.domain;

import com.delivery.estimate.offer.NoOffer;
import com.delivery.estimate.offer.Offer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.delivery.estimate.offer.OfferTest.createOffer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PackageTest {
    private Offer offer;

    @BeforeEach
    void setUp() {
        offer = new NoOffer();
    }

    @Test
    void shouldContainPackageIdWeightDeliveryDistanceAndBaseDeliveryCostAndOfferAndDeliveryTime() {
        BigDecimal weight = BigDecimal.valueOf(100);
        BigDecimal deliveryDistance = BigDecimal.valueOf(100);
        BigDecimal baseDeliveryCost = BigDecimal.valueOf(100);
        BigDecimal deliveryTime = BigDecimal.valueOf(1.0);
        String packageId = "PKG1";
        Package packageItem = createPackageWith(weight, deliveryDistance, baseDeliveryCost, packageId, offer, deliveryTime);

        assertEquals(packageId, packageItem.getId());
        assertEquals(deliveryDistance, packageItem.getDeliveryDistance());
        assertEquals(baseDeliveryCost, packageItem.getBaseDeliveryCost());
        assertEquals(weight, packageItem.getWeight());
        assertEquals(offer, packageItem.getOffer());
        assertEquals(deliveryTime, packageItem.getDeliveryTime());
    }

    @Test
    void shouldUpdateDeliveryTime() {
        BigDecimal weight = BigDecimal.valueOf(100);
        BigDecimal deliveryDistance = BigDecimal.valueOf(100);
        BigDecimal baseDeliveryCost = BigDecimal.valueOf(100);
        BigDecimal deliveryTime = BigDecimal.valueOf(1.0);
        String packageId = "PKG1";
        Package packageItem = createPackageWith(weight, deliveryDistance, baseDeliveryCost, packageId, offer, deliveryTime);
        packageItem.updateDeliveryTime(BigDecimal.TEN);
        assertEquals(BigDecimal.TEN, packageItem.getDeliveryTime());
    }

    @Test
    void shouldReturnDeliveryCostAs115() {
        BigDecimal weight = BigDecimal.valueOf(1);
        BigDecimal deliveryDistance = BigDecimal.valueOf(1);
        BigDecimal baseDeliveryCost = BigDecimal.valueOf(100);
        BigDecimal deliveryTime = BigDecimal.valueOf(1.0);
        String packageId = "PKG1";
        Package packageItem = createPackageWith(weight, deliveryDistance, baseDeliveryCost, packageId, offer, deliveryTime);

        BigDecimal deliveryCost = packageItem.deliveryCost();
        assertNotNull(deliveryCost);
        assertEquals(BigDecimal.valueOf(115), deliveryCost);
    }

    @Test
    void shouldReturnDeliveryCostAs205() {
        BigDecimal weight = BigDecimal.valueOf(10);
        BigDecimal deliveryDistance = BigDecimal.valueOf(1);
        BigDecimal baseDeliveryCost = BigDecimal.valueOf(100);
        BigDecimal deliveryTime = BigDecimal.valueOf(1.0);
        String packageId = "PKG1";
        Package packageItem = createPackageWith(weight, deliveryDistance, baseDeliveryCost, packageId, offer, deliveryTime);

        BigDecimal deliveryCost = packageItem.deliveryCost();
        assertNotNull(deliveryCost);
        assertEquals(BigDecimal.valueOf(205), deliveryCost);
    }

    @Test
    void shouldReturnDeliveryCostAs250() {
        BigDecimal weight = BigDecimal.valueOf(10);
        BigDecimal deliveryDistance = BigDecimal.valueOf(10);
        BigDecimal baseDeliveryCost = BigDecimal.valueOf(100);
        BigDecimal deliveryTime = BigDecimal.valueOf(1.0);
        String packageId = "PKG1";
        Package packageItem = createPackageWith(weight, deliveryDistance, baseDeliveryCost, packageId, offer, deliveryTime);

        BigDecimal deliveryCost = packageItem.deliveryCost();
        assertNotNull(deliveryCost);
        assertEquals(BigDecimal.valueOf(250), deliveryCost);
    }

    @Test
    void shouldReturnDeliveryCostAs700AndDiscountAs35AndTotalCostAs665WhenWeightIs10AndDistanceIs100OfferCode3IsApplied() {
        BigDecimal weight = BigDecimal.valueOf(10);
        BigDecimal deliveryDistance = BigDecimal.valueOf(100);
        BigDecimal baseDeliveryCost = BigDecimal.valueOf(100);
        BigDecimal deliveryTime = BigDecimal.valueOf(1.0);
        String packageId = "PKG1";
        Offer offer3 = createOffer(
                "OFR003",
                BigDecimal.valueOf(10),
                BigDecimal.valueOf(150),
                BigDecimal.valueOf(50),
                BigDecimal.valueOf(250),
                BigDecimal.valueOf(5)
        );
        Package packageItem = createPackageWith(weight, deliveryDistance, baseDeliveryCost, packageId, offer3, deliveryTime);

        assertEquals(BigDecimal.valueOf(700), packageItem.deliveryCost());
        assertEquals(new BigDecimal("35.00"), packageItem.discount());
        assertEquals(new BigDecimal("665.00"), packageItem.totalCost());
    }

    @Test
    void shouldReturnDeliveryCostAs175AndDiscountAs0AndTotalCostAs175WhenWeightIs5AndDistanceIs5OfferCode1IsApplied() {
        BigDecimal weight = BigDecimal.valueOf(5);
        BigDecimal deliveryDistance = BigDecimal.valueOf(5);
        BigDecimal baseDeliveryCost = BigDecimal.valueOf(100);
        BigDecimal deliveryTime = BigDecimal.valueOf(1.0);
        String packageId = "PKG1";
        Offer offer1 = createOffer(
                "OFR001",
                BigDecimal.valueOf(70),
                BigDecimal.valueOf(200),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(199),
                BigDecimal.valueOf(10)
        );
        Package packageItem = createPackageWith(weight, deliveryDistance, baseDeliveryCost, packageId, offer1, deliveryTime);

        assertEquals(BigDecimal.valueOf(175), packageItem.deliveryCost());
        assertEquals(BigDecimal.ZERO, packageItem.discount());
        assertEquals(BigDecimal.valueOf(175), packageItem.totalCost());
    }

    private Package createPackageWith(BigDecimal weight,
                                      BigDecimal deliveryDistance,
                                      BigDecimal baseDeliveryCost,
                                      String packageId,
                                      Offer offer,
                                      BigDecimal deliveryTime) {
        return new Package(packageId, weight, deliveryDistance, baseDeliveryCost, offer, deliveryTime);
    }
}
