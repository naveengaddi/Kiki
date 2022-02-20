package com.delivery.estimate.service;

import com.delivery.estimate.exception.InputInvalidException;

import java.math.BigDecimal;

public class InputParser {
    public BigDecimal extractBasePrice(String input) {
        try {
            String[] data = input.split(" ");
            return new BigDecimal(data[0]);
        } catch (Exception e) {
            throw new InputInvalidException(e.getMessage());
        }
    }

    public Integer extractNumberOfPackages(String input) {
        try {
            String[] data = input.split(" ");
            return Integer.parseInt(data[1]);
        } catch (Exception e) {
            throw new InputInvalidException(e.getMessage());
        }
    }

    public String extractPackageId(String input) {
        String[] data = input.split(" ");
        return data[0];
    }

    public BigDecimal extractPackageWeight(String input) {
        try {
            String[] data = input.split(" ");
            return new BigDecimal(data[1]);
        } catch (Exception e) {
            throw new InputInvalidException(e.getMessage());
        }
    }

    public BigDecimal extractPackageDistance(String input) {
        try {
            String[] data = input.split(" ");
            return new BigDecimal(data[2]);
        } catch (Exception e) {
            throw new InputInvalidException(e.getMessage());
        }
    }

    public String extractOfferCode(String input) {
        String[] data = input.split(" ");
        return data[3];
    }
}
