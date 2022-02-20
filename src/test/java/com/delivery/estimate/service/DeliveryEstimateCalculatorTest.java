package com.delivery.estimate.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Disabled
class DeliveryEstimateCalculatorTest {

    private DeliveryEstimateCalculator deliveryEstimateCalculator;
    private InputParser inputParserMock;
    private Scanner scannerMock;
    private DisplayService displayServiceMock;

    @BeforeEach
    void setUp() {
        inputParserMock = mock(InputParser.class);
        scannerMock = mock(Scanner.class);
        displayServiceMock = mock(DisplayService.class);
        deliveryEstimateCalculator = new DeliveryEstimateCalculator(inputParserMock, displayServiceMock);
    }

    @Test
    void start() {
        String input = "100 0";
        when(scannerMock.nextLine()).thenReturn("100  0");
        deliveryEstimateCalculator.start();

        verify(inputParserMock).extractBasePrice(anyString());
    }
}
