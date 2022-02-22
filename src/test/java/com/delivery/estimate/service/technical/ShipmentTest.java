package com.delivery.estimate.service.technical;

import com.delivery.estimate.domain.Package;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShipmentTest {
    @Test
    void shouldReturnTotalWeightAs300AndTotalDeliveryDistance150() {
        Shipment shipment = new Shipment();
        Package packageItem = mock(Package.class);
        when(packageItem.getWeight()).thenReturn(BigDecimal.valueOf(100));
        when(packageItem.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(50));
        shipment.add(packageItem);
        shipment.add(packageItem);
        shipment.add(packageItem);

        assertEquals(BigDecimal.valueOf(300), shipment.getTotalWeight());
        assertEquals(BigDecimal.valueOf(150), shipment.getTotalDeliveryDistance());
    }

    @Test
    void shouldReturnTotalWeightAs90AndTotalDeliveryDistance14() {
        Shipment shipment = new Shipment();
        Package packageItem1 = mock(Package.class);
        Package packageItem2 = mock(Package.class);
        Package packageItem3 = mock(Package.class);
        when(packageItem1.getWeight()).thenReturn(BigDecimal.valueOf(30));
        when(packageItem2.getWeight()).thenReturn(BigDecimal.valueOf(40));
        when(packageItem3.getWeight()).thenReturn(BigDecimal.valueOf(20));
        when(packageItem1.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(2));
        when(packageItem2.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(8));
        when(packageItem3.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(4));
        shipment.add(packageItem1);
        shipment.add(packageItem2);
        shipment.add(packageItem3);

        assertEquals(BigDecimal.valueOf(90), shipment.getTotalWeight());
        assertEquals(BigDecimal.valueOf(14), shipment.getTotalDeliveryDistance());
    }

    @Test
    void shouldReturnTotalWeightAs30AndTotalDeliveryDistance14() {
        Package packageItem1 = mock(Package.class);
        Package packageItem2 = mock(Package.class);
        Package packageItem3 = mock(Package.class);
        when(packageItem1.getWeight()).thenReturn(BigDecimal.valueOf(10));
        when(packageItem2.getWeight()).thenReturn(BigDecimal.valueOf(5));
        when(packageItem3.getWeight()).thenReturn(BigDecimal.valueOf(15));
        when(packageItem1.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(6));
        when(packageItem2.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(4));
        when(packageItem3.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(4));

        List<Package> packages = List.of(packageItem1, packageItem2, packageItem3);
        Shipment shipment = new Shipment(packages);

        assertEquals(BigDecimal.valueOf(30), shipment.getTotalWeight());
        assertEquals(BigDecimal.valueOf(14), shipment.getTotalDeliveryDistance());
    }

    @Test
    void shouldReturnTotalWeightAs130AndTotalDeliveryDistance140() {
        Package packageItem1 = mock(Package.class);
        Package packageItem2 = mock(Package.class);
        Package packageItem3 = mock(Package.class);
        when(packageItem1.getWeight()).thenReturn(BigDecimal.valueOf(100));
        when(packageItem2.getWeight()).thenReturn(BigDecimal.valueOf(5));
        when(packageItem3.getWeight()).thenReturn(BigDecimal.valueOf(25));
        when(packageItem1.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(60));
        when(packageItem2.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(40));
        when(packageItem3.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(40));

        List<Package> packages = List.of(packageItem1, packageItem2, packageItem3);
        Shipment shipment = new Shipment();
        shipment.addAll(packages);

        assertEquals(BigDecimal.valueOf(130), shipment.getTotalWeight());
        assertEquals(BigDecimal.valueOf(140), shipment.getTotalDeliveryDistance());
    }


    @Test
    void shouldReturnTrueWhenShipmentSize1GreaterThanShipment2() {
        Package packageItem1 = mock(Package.class);
        when(packageItem1.getWeight()).thenReturn(BigDecimal.valueOf(100));
        when(packageItem1.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(60));

        Shipment shipment1 = new Shipment();
        Shipment shipment2 = new Shipment();

        shipment1.add(packageItem1);
        shipment1.add(packageItem1);

        shipment2.add(packageItem1);

        assertTrue(shipment1.isBetterThan(shipment2));
    }

    @Test
    void shouldReturnFalseWhenShipmentSize1LessThanShipment2() {
        Package packageItem1 = mock(Package.class);
        when(packageItem1.getWeight()).thenReturn(BigDecimal.valueOf(100));
        when(packageItem1.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(60));

        Shipment shipment1 = new Shipment();
        Shipment shipment2 = new Shipment();

        shipment1.add(packageItem1);

        shipment2.add(packageItem1);
        shipment2.add(packageItem1);

        assertFalse(shipment1.isBetterThan(shipment2));
    }

    @Test
    void shouldReturnTrueWhenShipment1WeightMoreThanShipment2() {
        Package packageItem1 = mock(Package.class);
        Package packageItem2 = mock(Package.class);
        when(packageItem1.getWeight()).thenReturn(BigDecimal.valueOf(150));
        when(packageItem2.getWeight()).thenReturn(BigDecimal.valueOf(100));
        when(packageItem1.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(60));
        when(packageItem2.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(60));

        Shipment shipment1 = new Shipment();
        Shipment shipment2 = new Shipment();

        shipment1.add(packageItem1);

        shipment2.add(packageItem2);

        assertTrue(shipment1.isBetterThan(shipment2));
    }

    @Test
    void shouldReturnFalseWhenShipment1WeightLessThanShipment2() {
        Package packageItem1 = mock(Package.class);
        Package packageItem2 = mock(Package.class);
        when(packageItem1.getWeight()).thenReturn(BigDecimal.valueOf(100));
        when(packageItem2.getWeight()).thenReturn(BigDecimal.valueOf(150));
        when(packageItem1.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(60));
        when(packageItem2.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(60));

        Shipment shipment1 = new Shipment();
        Shipment shipment2 = new Shipment();

        shipment1.add(packageItem1);

        shipment2.add(packageItem2);

        assertFalse(shipment1.isBetterThan(shipment2));
    }

    @Test
    void shouldReturnTrueWhenShipment1DistanceLessThanShipment2() {
        Package packageItem1 = mock(Package.class);
        Package packageItem2 = mock(Package.class);
        when(packageItem1.getWeight()).thenReturn(BigDecimal.valueOf(100));
        when(packageItem2.getWeight()).thenReturn(BigDecimal.valueOf(100));
        when(packageItem1.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(40));
        when(packageItem2.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(60));

        Shipment shipment1 = new Shipment();
        Shipment shipment2 = new Shipment();

        shipment1.add(packageItem1);

        shipment2.add(packageItem2);

        assertTrue(shipment1.isBetterThan(shipment2));
    }

    @Test
    void shouldReturnFalseWhenShipment1DistanceGreaterThanShipment2() {
        Package packageItem1 = mock(Package.class);
        Package packageItem2 = mock(Package.class);
        when(packageItem1.getWeight()).thenReturn(BigDecimal.valueOf(100));
        when(packageItem2.getWeight()).thenReturn(BigDecimal.valueOf(100));
        when(packageItem1.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(60));
        when(packageItem2.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(40));

        Shipment shipment1 = new Shipment();
        Shipment shipment2 = new Shipment();

        shipment1.add(packageItem1);

        shipment2.add(packageItem2);

        assertFalse(shipment1.isBetterThan(shipment2));
    }

    @Test
    void shouldClearShipment() {
        Package packageItem1 = mock(Package.class);
        when(packageItem1.getWeight()).thenReturn(BigDecimal.valueOf(100));
        when(packageItem1.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(60));
        Shipment shipment = new Shipment();
        shipment.add(packageItem1);
        shipment.clear();
        assertEquals(0, shipment.size());
        assertEquals(BigDecimal.ZERO, shipment.getTotalWeight());
        assertEquals(BigDecimal.ZERO, shipment.getTotalDeliveryDistance());
    }
}
