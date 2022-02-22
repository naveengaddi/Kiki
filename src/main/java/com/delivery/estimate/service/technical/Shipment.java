package com.delivery.estimate.service.technical;

import com.delivery.estimate.domain.Package;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;

@Getter
public class Shipment extends ArrayList<Package> {
    private BigDecimal totalWeight = BigDecimal.ZERO;
    private BigDecimal totalDeliveryDistance = BigDecimal.ZERO;

    @Override
    public boolean add(Package packageItem) {
        totalWeight = totalWeight.add(packageItem.getWeight());
        totalDeliveryDistance = totalDeliveryDistance.add(packageItem.getDeliveryDistance());
        return super.add(packageItem);
    }

}
