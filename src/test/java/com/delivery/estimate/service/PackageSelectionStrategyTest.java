package com.delivery.estimate.service;

import com.delivery.estimate.domain.Package;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PackageSelectionStrategyTest {
    private PackageSelectionStrategy packageSelectionStrategy;

    @BeforeEach
    void setUp() {
        packageSelectionStrategy = new PackageSelectionStrategy();
    }

    @Test
    void shouldReturnSamePackageIfOnlyOnePackageExists() {
        BigDecimal maxLoad = BigDecimal.valueOf(200);
        Package packageItem = mock(Package.class);
        when(packageItem.getId()).thenReturn("PKG1");
        when(packageItem.getWeight()).thenReturn(BigDecimal.ONE);
        when(packageItem.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(10));
        List<Package> packages = List.of(packageItem);

        List<Package> packageList = packageSelectionStrategy.findPackagesWithin(maxLoad, packages);
        assertEquals(1, packageList.size());
        assertTrue(packageList.contains(packageItem));
    }

    @Test
    void shouldReturnEmptyListWhenNoPackageExistsWithinMaxLoad() {
        BigDecimal maxLoad = BigDecimal.valueOf(100);
        Package packageItem = mock(Package.class);
        when(packageItem.getId()).thenReturn("PKG1");
        when(packageItem.getWeight()).thenReturn(BigDecimal.valueOf(200));
        when(packageItem.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(10));
        List<Package> packages = List.of(packageItem);

        List<Package> packageList = packageSelectionStrategy.findPackagesWithin(maxLoad, packages);
        assertEquals(0, packageList.size());
    }

    @Test
    void shouldReturnTwoPackagesWhenTwoPackagesAreWithinMaxLoad() {
        BigDecimal maxLoad = BigDecimal.valueOf(100);
        Package packageItem1 = mock(Package.class);
        Package packageItem2 = mock(Package.class);
        when(packageItem1.getId()).thenReturn("PKG1");
        when(packageItem2.getId()).thenReturn("PKG2");
        when(packageItem1.getWeight()).thenReturn(BigDecimal.valueOf(50));
        when(packageItem1.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(10));
        when(packageItem2.getWeight()).thenReturn(BigDecimal.valueOf(30));
        when(packageItem2.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(10));
        List<Package> packages = List.of(packageItem1, packageItem2);

        List<Package> packageList = packageSelectionStrategy.findPackagesWithin(maxLoad, packages);
        assertEquals(2, packageList.size());
        assertTrue(packageList.contains(packageItem1));
        assertTrue(packageList.contains(packageItem2));
    }

    @Test
    void shouldReturnTwoPackages50And40WhenMaxLoadIs100AndThreePackagesAreGivenWithWeight50And40And30() {
        BigDecimal maxLoad = BigDecimal.valueOf(100);
        Package packageItem1 = mock(Package.class);
        Package packageItem2 = mock(Package.class);
        Package packageItem3 = mock(Package.class);
        when(packageItem1.getId()).thenReturn("PKG1");
        when(packageItem2.getId()).thenReturn("PKG2");
        when(packageItem3.getId()).thenReturn("PKG3");
        when(packageItem1.getWeight()).thenReturn(BigDecimal.valueOf(50));
        when(packageItem2.getWeight()).thenReturn(BigDecimal.valueOf(40));
        when(packageItem3.getWeight()).thenReturn(BigDecimal.valueOf(30));
        when(packageItem1.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(10));
        when(packageItem2.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(10));
        when(packageItem3.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(10));
        List<Package> packages = List.of(packageItem1, packageItem2, packageItem3);

        List<Package> packageList = packageSelectionStrategy.findPackagesWithin(maxLoad, packages);
        assertEquals(2, packageList.size());
        assertTrue(packageList.contains(packageItem1));
        assertTrue(packageList.contains(packageItem2));
    }

    @Test
    void shouldReturnPackagesWithMaxNumberOfPackagesCount() {
        BigDecimal maxLoad = BigDecimal.valueOf(100);
        Package packageItem1 = mock(Package.class);
        Package packageItem2 = mock(Package.class);
        Package packageItem3 = mock(Package.class);
        when(packageItem1.getId()).thenReturn("PKG1");
        when(packageItem2.getId()).thenReturn("PKG2");
        when(packageItem3.getId()).thenReturn("PKG3");
        when(packageItem1.getWeight()).thenReturn(BigDecimal.valueOf(50));
        when(packageItem2.getWeight()).thenReturn(BigDecimal.valueOf(40));
        when(packageItem3.getWeight()).thenReturn(BigDecimal.valueOf(100));
        when(packageItem1.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(110));
        when(packageItem2.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(120));
        when(packageItem3.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(110));
        List<Package> packages = List.of(packageItem1, packageItem2, packageItem3);

        List<Package> packageList = packageSelectionStrategy.findPackagesWithin(maxLoad, packages);
        assertEquals(2, packageList.size());
        assertTrue(packageList.contains(packageItem1));
        assertTrue(packageList.contains(packageItem2));
    }

    @Test
    void shouldReturnPackagesWithMaxNumberOfPackagesCountWithHeavierPackages() {
        BigDecimal maxLoad = BigDecimal.valueOf(100);
        Package packageItem1 = mock(Package.class);
        Package packageItem2 = mock(Package.class);
        Package packageItem3 = mock(Package.class);
        Package packageItem4 = mock(Package.class);
        when(packageItem1.getId()).thenReturn("PKG1");
        when(packageItem2.getId()).thenReturn("PKG2");
        when(packageItem3.getId()).thenReturn("PKG3");
        when(packageItem4.getId()).thenReturn("PKG4");
        when(packageItem1.getWeight()).thenReturn(BigDecimal.valueOf(50));
        when(packageItem2.getWeight()).thenReturn(BigDecimal.valueOf(30));
        when(packageItem3.getWeight()).thenReturn(BigDecimal.valueOf(60));
        when(packageItem4.getWeight()).thenReturn(BigDecimal.valueOf(40));
        when(packageItem1.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(10));
        when(packageItem2.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(120));
        when(packageItem3.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(110));
        when(packageItem4.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(10));
        List<Package> packages = List.of(packageItem1, packageItem2, packageItem3, packageItem4);

        List<Package> packageList = packageSelectionStrategy.findPackagesWithin(maxLoad, packages);
        assertEquals(2, packageList.size());
        assertTrue(packageList.contains(packageItem3));
        assertTrue(packageList.contains(packageItem4));
    }

    @Test
    void shouldReturnPackagesWithMaxNumberOfPackagesCountAndWhichHasLessDeliveryTime() {
        BigDecimal maxLoad = BigDecimal.valueOf(100);
        Package packageItem1 = mock(Package.class);
        Package packageItem2 = mock(Package.class);
        Package packageItem3 = mock(Package.class);
        Package packageItem4 = mock(Package.class);
        Package packageItem5 = mock(Package.class);
        Package packageItem6 = mock(Package.class);
        when(packageItem1.getId()).thenReturn("PKG1");
        when(packageItem2.getId()).thenReturn("PKG2");
        when(packageItem3.getId()).thenReturn("PKG3");
        when(packageItem4.getId()).thenReturn("PKG4");
        when(packageItem5.getId()).thenReturn("PKG5");
        when(packageItem6.getId()).thenReturn("PKG6");
        when(packageItem1.getWeight()).thenReturn(BigDecimal.valueOf(50));
        when(packageItem2.getWeight()).thenReturn(BigDecimal.valueOf(50));
        when(packageItem1.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(30));
        when(packageItem2.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(20));

        when(packageItem3.getWeight()).thenReturn(BigDecimal.valueOf(60));
        when(packageItem4.getWeight()).thenReturn(BigDecimal.valueOf(40));
        when(packageItem3.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(10));
        when(packageItem4.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(20));

        when(packageItem5.getWeight()).thenReturn(BigDecimal.valueOf(40));
        when(packageItem6.getWeight()).thenReturn(BigDecimal.valueOf(40));
        when(packageItem5.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(30));
        when(packageItem6.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(20));

        List<Package> packages = List.of(packageItem1, packageItem2, packageItem3, packageItem4, packageItem5, packageItem6);

        List<Package> packageList = packageSelectionStrategy.findPackagesWithin(maxLoad, packages);
        assertEquals(2, packageList.size());
        assertTrue(packageList.contains(packageItem3));
        assertTrue(packageList.contains(packageItem4));
    }

    @Test
    void shouldReturnPackages2And4() {
        BigDecimal maxLoad = BigDecimal.valueOf(200);
        Package packageItem1 = mock(Package.class);
        Package packageItem2 = mock(Package.class);
        Package packageItem3 = mock(Package.class);
        Package packageItem4 = mock(Package.class);
        Package packageItem5 = mock(Package.class);

        when(packageItem1.getId()).thenReturn("PKG1");
        when(packageItem2.getId()).thenReturn("PKG2");
        when(packageItem3.getId()).thenReturn("PKG3");
        when(packageItem4.getId()).thenReturn("PKG4");
        when(packageItem5.getId()).thenReturn("PKG5");

        when(packageItem1.getWeight()).thenReturn(BigDecimal.valueOf(50));
        when(packageItem2.getWeight()).thenReturn(BigDecimal.valueOf(75));
        when(packageItem3.getWeight()).thenReturn(BigDecimal.valueOf(175));
        when(packageItem4.getWeight()).thenReturn(BigDecimal.valueOf(110));
        when(packageItem5.getWeight()).thenReturn(BigDecimal.valueOf(155));

        when(packageItem1.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(30));
        when(packageItem2.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(125));
        when(packageItem3.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(100));
        when(packageItem4.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(60));
        when(packageItem5.getDeliveryDistance()).thenReturn(BigDecimal.valueOf(95));


        List<Package> packages = List.of(packageItem1, packageItem2, packageItem3, packageItem4, packageItem5);

        List<Package> packageList = packageSelectionStrategy.findPackagesWithin(maxLoad, packages);
        assertEquals(2, packageList.size());
        assertTrue(packageList.contains(packageItem2));
        assertTrue(packageList.contains(packageItem4));
    }
}
