package com.delivery.estimate.service.technical;

import com.delivery.estimate.domain.Package;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

@Getter
@NoArgsConstructor
public class Shipment extends ArrayList<Package> {
    private BigDecimal totalWeight = BigDecimal.ZERO;
    private BigDecimal totalDeliveryDistance = BigDecimal.ZERO;
    private BigDecimal maxCapacity = BigDecimal.valueOf(1000); //TODO : change this

    public Shipment(BigDecimal maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Shipment(Collection<? extends Package> c) {
        this.addAll(c);
    }

    @Override
    public boolean add(Package packageItem) {
        if (totalWeight.add(packageItem.getWeight()).compareTo(maxCapacity) <= 0) {
            totalWeight = totalWeight.add(packageItem.getWeight());
            totalDeliveryDistance = totalDeliveryDistance.add(packageItem.getDeliveryDistance());
            return super.add(packageItem);
        }
        return false;
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
        } else if (that.size() > this.size()) {
            return false;
        } else if (that.getTotalWeight().compareTo(this.getTotalWeight()) < 0) {
            return true;
        } else if (that.getTotalWeight().compareTo(this.getTotalWeight()) > 0) {
            return false;
        }
        return that.getTotalDeliveryDistance().compareTo(this.getTotalDeliveryDistance()) > 0;
    }

    public BigDecimal lastPackageDeliveryTime() {
        return this.stream().max(Comparator.comparing(Package::getDeliveryTime)).get().getDeliveryTime();
    }
}
