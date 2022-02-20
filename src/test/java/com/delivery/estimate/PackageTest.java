package com.delivery.estimate;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PackageTest {

    @Test
    void shouldContainPackageIdWeightDeliveryDistanceAndBaseDeliveryCost() {
        BigDecimal weight = BigDecimal.valueOf(100);
        BigDecimal deliveryDistance = BigDecimal.valueOf(100);
        BigDecimal baseDeliveryCost = BigDecimal.valueOf(100);
        String packageId = "PKG1";
        Package packageItem = new Package(packageId, weight, deliveryDistance, baseDeliveryCost);

        assertEquals(packageId, packageItem.getId());
        assertEquals(deliveryDistance, packageItem.getDeliveryDistance());
        assertEquals(baseDeliveryCost, packageItem.getBaseDeliveryCost());
        assertEquals(weight, packageItem.getWeight());
    }
}
