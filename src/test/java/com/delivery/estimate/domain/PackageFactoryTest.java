package com.delivery.estimate.domain;

import com.delivery.estimate.offer.NoOffer;
import com.delivery.estimate.offer.OFR001;
import com.delivery.estimate.offer.OFR002;
import com.delivery.estimate.offer.OFR003;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PackageFactoryTest {
    @Test
    void shouldReturnPackageWithOfferCode1Details() {
        BigDecimal deliveryDistance = BigDecimal.valueOf(11);
        BigDecimal parcelWeight = BigDecimal.valueOf(10);
        BigDecimal baseDeliveryCost = BigDecimal.valueOf(100);
        String offerCode = "OFR001";
        String packageId = "1";
        Package packageItem = PackageFactory.createPackage(packageId, parcelWeight, deliveryDistance, offerCode, baseDeliveryCost);
        assertNotNull(packageItem);
        assertEquals("1", packageItem.getId());
        assertEquals(baseDeliveryCost, packageItem.getBaseDeliveryCost());
        assertEquals(parcelWeight, packageItem.getWeight());
        assertEquals(deliveryDistance, packageItem.getDeliveryDistance());
        assertEquals(new OFR001(), packageItem.getOffer());
    }

    @Test
    void shouldReturnPackageWithOfferCode2Details() {
        BigDecimal deliveryDistance = BigDecimal.valueOf(111);
        BigDecimal parcelWeight = BigDecimal.valueOf(110);
        BigDecimal baseDeliveryCost = BigDecimal.valueOf(1100);
        String offerCode = "OFR002";
        String packageId = "21";
        Package packageItem = PackageFactory.createPackage(packageId, parcelWeight, deliveryDistance, offerCode, baseDeliveryCost);
        assertNotNull(packageItem);
        assertEquals("21", packageItem.getId());
        assertEquals(baseDeliveryCost, packageItem.getBaseDeliveryCost());
        assertEquals(parcelWeight, packageItem.getWeight());
        assertEquals(deliveryDistance, packageItem.getDeliveryDistance());
        assertEquals(new OFR002(), packageItem.getOffer());
    }

    @Test
    void shouldReturnPackageWithOfferCode3Details() {
        BigDecimal deliveryDistance = BigDecimal.valueOf(4111);
        BigDecimal parcelWeight = BigDecimal.valueOf(1105);
        BigDecimal baseDeliveryCost = BigDecimal.valueOf(1100);
        String offerCode = "OFR003";
        String packageId = "211";
        Package packageItem = PackageFactory.createPackage(packageId, parcelWeight, deliveryDistance, offerCode, baseDeliveryCost);
        assertNotNull(packageItem);
        assertEquals("211", packageItem.getId());
        assertEquals(baseDeliveryCost, packageItem.getBaseDeliveryCost());
        assertEquals(parcelWeight, packageItem.getWeight());
        assertEquals(deliveryDistance, packageItem.getDeliveryDistance());
        assertEquals(new OFR003(), packageItem.getOffer());
    }

    @Test
    void shouldReturnPackageWithNoOfferCodeDetails() {
        BigDecimal deliveryDistance = BigDecimal.valueOf(4111);
        BigDecimal parcelWeight = BigDecimal.valueOf(1105);
        BigDecimal baseDeliveryCost = BigDecimal.valueOf(1100);
        String offerCode = "NA";
        String packageId = "211";
        Package packageItem = PackageFactory.createPackage(packageId, parcelWeight, deliveryDistance, offerCode, baseDeliveryCost);
        assertNotNull(packageItem);
        assertEquals("211", packageItem.getId());
        assertEquals(baseDeliveryCost, packageItem.getBaseDeliveryCost());
        assertEquals(parcelWeight, packageItem.getWeight());
        assertEquals(deliveryDistance, packageItem.getDeliveryDistance());
        assertEquals(new NoOffer(), packageItem.getOffer());
    }
}
