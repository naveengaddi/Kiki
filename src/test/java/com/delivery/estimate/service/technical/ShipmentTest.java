package com.delivery.estimate.service.technical;

import com.delivery.estimate.domain.Package;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
