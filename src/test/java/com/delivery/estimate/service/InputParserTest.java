package com.delivery.estimate.service;

import com.delivery.estimate.exception.InputInvalidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputParserTest {
    private InputParser inputParser;

    @BeforeEach
    void setUp() {
        inputParser = new InputParser();
    }

    @Test
    void shouldReturnBasePriceAs100() {
        String input = "100 3";
        BigDecimal basePrice = inputParser.extractBasePrice(input);
        assertEquals(BigDecimal.valueOf(100), basePrice);
    }

    @Test
    void shouldReturnBasePriceAs101Dot1() {
        String input = "101.1 3";
        BigDecimal basePrice = inputParser.extractBasePrice(input);
        assertEquals(BigDecimal.valueOf(101.1), basePrice);
    }

    @Test
    void shouldThrowInvalidInputExceptionWhenUnableToExtractBasePrice() {
        String input = "sdfsf3";
        assertThrows(InputInvalidException.class, () -> inputParser.extractBasePrice(input));
    }

    @Test
    void shouldReturnNumberOfPackagesAs3() {
        String input = "100 3";
        Integer numberOfPackages = inputParser.extractNumberOfPackages(input);
        assertEquals(3, numberOfPackages);
    }

    @Test
    void shouldReturnNumberOfPackagesAs5() {
        String input = "100 5";
        Integer numberOfPackages = inputParser.extractNumberOfPackages(input);
        assertEquals(5, numberOfPackages);
    }

    @Test
    void shouldThrowInvalidInputExceptionWhenUnableToExtractNumberOfPackages() {
        String input = "100 ssd";
        assertThrows(InputInvalidException.class, () -> inputParser.extractNumberOfPackages(input));
    }

    @Test
    void shouldReturnPackedIdAsPKG1() {
        String input = "PKG1 5 5 Offer1";
        String packageId = inputParser.extractPackageId(input);
        assertEquals("PKG1", packageId);
    }

    @Test
    void shouldReturnPackageWeightAs5() {
        String input = "PKG2 5 5 Offer1";
        BigDecimal packageWeight = inputParser.extractPackageWeight(input);
        assertEquals(BigDecimal.valueOf(5), packageWeight);
    }

    @Test
    void shouldThrowInvalidInputExceptionWhenUnableToExtractPackageWeight() {
        String input = "PKG2 sdfds 5 Offer1";
        assertThrows(InputInvalidException.class, () -> inputParser.extractPackageWeight(input));
    }

    @Test
    void shouldReturnPackageDistanceAs19() {
        String input = "PKG2 5 19 Offer1";
        BigDecimal packageWeight = inputParser.extractPackageDistance(input);
        assertEquals(BigDecimal.valueOf(19), packageWeight);
    }

    @Test
    void shouldThrowInvalidInputExceptionWhenUnableToExtractPackageDistance() {
        String input = "PKG2 sdfds sdd Offer1";
        assertThrows(InputInvalidException.class, () -> inputParser.extractPackageDistance(input));
    }

    @Test
    void shouldReturnOfferCodeAsOffer1() {
        String input = "PKG2 5 19 Offer1";
        String offerCode = inputParser.extractOfferCode(input);
        assertEquals("Offer1", offerCode);
    }

    @Test
    void shouldReturnNumberOfVehiclesAs3() {
        String input = "3 70 200";
        Integer numberOfVehicles = inputParser.extractNumberOfVehicles(input);
        assertEquals(3, numberOfVehicles);
    }

    @Test
    void shouldReturnMaxSpeedAs70() {
        String input = "3 70 200";
        BigDecimal maxSpeed = inputParser.extractMaxSpeed(input);
        assertEquals(BigDecimal.valueOf(70), maxSpeed);
    }

    @Test
    void shouldReturnMaxLoadAs200() {
        String input = "3 70 200";
        BigDecimal maxLoad = inputParser.extractMaxLoad(input);
        assertEquals(BigDecimal.valueOf(200), maxLoad);
    }

    @Test
    void shouldThrowInvalidInputExceptionWhenUnableToExtractNumberOfVehicles() {
        String input = "3ss 70 200";
        assertThrows(InputInvalidException.class, () -> inputParser.extractNumberOfVehicles(input));
    }

    @Test
    void shouldThrowInvalidInputExceptionWhenUnableToExtractMaxSpeed() {
        String input = "3 70s 200";
        assertThrows(InputInvalidException.class, () -> inputParser.extractMaxSpeed(input));
    }

    @Test
    void shouldThrowInvalidInputExceptionWhenUnableToExtractMaxLoad() {
        String input = "3 70 200d";
        assertThrows(InputInvalidException.class, () -> inputParser.extractMaxLoad(input));
    }

}
