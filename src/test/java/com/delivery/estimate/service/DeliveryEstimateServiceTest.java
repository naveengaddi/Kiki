package com.delivery.estimate.service;

import com.delivery.estimate.domain.Package;
import com.delivery.estimate.domain.PackageFactory;
import com.delivery.estimate.domain.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeliveryEstimateServiceTest {

    private DeliveryEstimateService deliveryEstimateService;
    private VehicleSelectionStrategy vehicleSelectionStrategy;
    private PackageSelectionStrategy packageSelectionStrategy;
    private DeliveryTimeCalculator deliveryTimeCalculator;

    @BeforeEach
    void setUp() {
        vehicleSelectionStrategy = new VehicleSelectionStrategy();
        packageSelectionStrategy = new PackageSelectionStrategy();
        deliveryTimeCalculator = new DeliveryTimeCalculator();
        deliveryEstimateService = new DeliveryEstimateService(
                vehicleSelectionStrategy,
                packageSelectionStrategy,
                deliveryTimeCalculator);
    }

    @Test
    void shouldReturnEstimatedPackageList() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle(BigDecimal.valueOf(200), BigDecimal.valueOf(70), BigDecimal.ZERO));
        vehicles.add(new Vehicle(BigDecimal.valueOf(200), BigDecimal.valueOf(70), BigDecimal.ZERO));

        BigDecimal baseDeliveryCost = BigDecimal.valueOf(100);
        Package packageItem1 = PackageFactory.createPackage("PKG1", getDecimal("50"), getDecimal("30"), "OFR001", baseDeliveryCost);
        Package packageItem2 = PackageFactory.createPackage("PKG2", getDecimal("75"), getDecimal("125"), "OFFR0008", baseDeliveryCost);
        Package packageItem3 = PackageFactory.createPackage("PKG3", getDecimal("175"), getDecimal("100"), "OFFR003", baseDeliveryCost);
        Package packageItem4 = PackageFactory.createPackage("PKG4", getDecimal("110"), getDecimal("60"), "OFR002", baseDeliveryCost);
        Package packageItem5 = PackageFactory.createPackage("PKG5", getDecimal("155"), getDecimal("95"), "NA", baseDeliveryCost);
        List<Package> packages = List.of(packageItem1, packageItem2, packageItem3, packageItem4, packageItem5);
        List<Package> packageList = deliveryEstimateService.estimateDelivery(packages, vehicles);
        packageList.forEach(System.out::println);
        assertEquals(getDecimal("3.98"), packageItem1.getDeliveryTime());
        assertEquals(getDecimal("1.78"), packageItem2.getDeliveryTime());
        assertEquals(getDecimal("1.42"), packageItem3.getDeliveryTime());
        assertEquals(getDecimal("0.85"), packageItem4.getDeliveryTime());
        assertEquals(getDecimal("4.19"), packageItem5.getDeliveryTime());

        assertEquals(getDecimal("750"), packageItem1.totalCost());
        assertEquals(getDecimal("1475"), packageItem2.totalCost());
        assertEquals(getDecimal("2350"), packageItem3.totalCost());
        assertEquals(getDecimal("1395.00"), packageItem4.totalCost());
        assertEquals(getDecimal("2125"), packageItem5.totalCost());

        assertEquals(getDecimal("0"), packageItem1.discount());
        assertEquals(getDecimal("0"), packageItem2.discount());
        assertEquals(getDecimal("0"), packageItem3.discount());
        assertEquals(getDecimal("105.00"), packageItem4.discount());
        assertEquals(getDecimal("0"), packageItem5.discount());
    }

    private BigDecimal getDecimal(String value) {
        return new BigDecimal(value);
    }
}
