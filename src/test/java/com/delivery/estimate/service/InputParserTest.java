package com.delivery.estimate.service;

import com.delivery.estimate.exception.InputInvalidException;
import com.delivery.estimate.service.dto.BasePriceAndPackageCount;
import com.delivery.estimate.service.dto.PackageDetails;
import com.delivery.estimate.service.dto.VehicleDetails;
import com.delivery.estimate.service.io.InputParser;
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
    void shouldReturnBasePriceAs100AndPackageCountAs3() {
        String input = "100 3";
        BasePriceAndPackageCount priceAndPackageCount = inputParser.parseBasePriceAndPackageCount(input);
        assertEquals(BigDecimal.valueOf(100), priceAndPackageCount.getBasePrice());
        assertEquals(3, priceAndPackageCount.getNumberOfPackages());
    }

    @Test
    void shouldThrowExceptionWhenUnableToParseBasePriceAs100AndPackageCountAs3() {
        String input = "100s d";
        assertThrows(InputInvalidException.class, () -> inputParser.parseBasePriceAndPackageCount(input));
    }

    @Test
    void shouldReturnPackageDetailsIdAsPKG1() {
        String input = "PKG1 5 10 Offer1";
        PackageDetails packageDetails = inputParser.parsePackageDetails(input);
        assertEquals("PKG1", packageDetails.getId());
        assertEquals("Offer1", packageDetails.getOfferCode());
        assertEquals(new BigDecimal("5"), packageDetails.getWeight());
        assertEquals(new BigDecimal("10"), packageDetails.getDeliveryDistance());
    }

    @Test
    void shouldThrowExceptionWhenUnableToParsePackageDetails() {
        String input = "PKG1 d5 10 Offer1";
        assertThrows(InputInvalidException.class, () -> inputParser.parsePackageDetails(input));
    }

    @Test
    void shouldThrowExceptionWhenPackageIdIsMissing() {
        String input = "5 10 Offer1";
        assertThrows(InputInvalidException.class, () -> inputParser.parsePackageDetails(input));
    }

    @Test
    void shouldReturnVehicleDetails() {
        String input = "2 70 200";
        VehicleDetails vehicleDetails = inputParser.parseVehicleDetails(input);
        assertEquals(2, vehicleDetails.getNumberOfVehicles());
        assertEquals(new BigDecimal("70"), vehicleDetails.getMaxSpeed());
        assertEquals(new BigDecimal("200"), vehicleDetails.getMaxLoad());
    }

    @Test
    void shouldInputInvalidExceptionWhenUnableToParseVehicleDetails() {
        String input = "2s 70 200";
        assertThrows(InputInvalidException.class, () -> inputParser.parseVehicleDetails(input));
    }

}
