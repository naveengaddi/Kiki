package com.delivery.estimate.service;

import com.delivery.estimate.domain.Package;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DisplayServiceTest {

    @Test
    void shouldLogMessageInConsole() {
        DisplayService displayService = new DisplayService();
        displayService.displayPackageEstimates(List.of());
    }

    @Test
    void shouldLogMessageInConsoleWithItems() {
        DisplayService displayService = new DisplayService();
        Package packageMock = mock(Package.class);
        when(packageMock.discount()).thenReturn(BigDecimal.TEN);
        when(packageMock.totalCost()).thenReturn(BigDecimal.TEN);
        displayService.displayPackageEstimates(List.of(packageMock));
    }
}
