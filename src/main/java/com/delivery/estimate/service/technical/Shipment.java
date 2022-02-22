package com.delivery.estimate.service.technical;

import com.delivery.estimate.domain.Package;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

@Getter
public class Shipment extends ArrayList<Package> {
    private BigDecimal totalWeight = BigDecimal.ZERO;
    private BigDecimal totalDeliveryDistance = BigDecimal.ZERO;

    public Shipment() {
    }

    public Shipment(Collection<? extends Package> c) {
        this.addAll(c);
    }

    @Override
    public boolean add(Package packageItem) {
        totalWeight = totalWeight.add(packageItem.getWeight());
        totalDeliveryDistance = totalDeliveryDistance.add(packageItem.getDeliveryDistance());
        return super.add(packageItem);
    }

    @Override
    public void clear() {
        this.totalWeight = BigDecimal.ZERO;
        this.totalDeliveryDistance = BigDecimal.ZERO;
        super.clear();
    }

    @Override
    public boolean addAll(Collection<? extends Package> c) {
        c.forEach(this::add);
        return true;
    }

    public boolean isBetterThan(Shipment that) {
        if (that.size() < this.size()) {
            return true;
        }
        if (that.size() > this.size()) {
            return false;
        }
        if (that.getTotalWeight().compareTo(this.getTotalWeight()) < 0) {
            return true;
        }
        if (that.getTotalWeight().compareTo(this.getTotalWeight()) > 0) {
            return false;
        }
        return that.getTotalDeliveryDistance().compareTo(this.getTotalDeliveryDistance()) > 0;
    }
}
