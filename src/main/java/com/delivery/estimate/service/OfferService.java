package com.delivery.estimate.service;

import com.delivery.estimate.offer.NoOffer;
import com.delivery.estimate.offer.Offer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OfferService {
    private final Map<String, Offer> offerCodes;

    public OfferService() {
        offerCodes = new HashMap<>();
        loadOffersFromFileStorage();
    }

    public Offer getOffer(String offerCode) {
        if (offerCodes.containsKey(offerCode)) {
            return offerCodes.get(offerCode);
        }
        return new NoOffer();
    }

    private void loadOffersFromFileStorage() {
        String filename = "offers.json";
        String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource(filename)).getPath();

        try {
            String offersData = Files.readString(Paths.get(filePath));
            OfferDto[] list = getOfferDtos(offersData);
            Arrays.stream(list).forEach(offerDto -> this.offerCodes.put(offerDto.offerCode,
                    new Offer(offerDto.offerCode,
                            offerDto.minWeight,
                            offerDto.maxWeight,
                            offerDto.minDistance,
                            offerDto.maxDistance,
                            offerDto.percentage)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private OfferDto[] getOfferDtos(String offersData) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(offersData, OfferDto[].class);
    }

    private static class OfferDto {
        @JsonProperty("offerCode")
        private String offerCode;

        @JsonProperty("minWeight")
        private BigDecimal minWeight;

        @JsonProperty("maxWeight")
        private BigDecimal maxWeight;

        @JsonProperty("minDistance")
        private BigDecimal minDistance;

        @JsonProperty("maxDistance")
        private BigDecimal maxDistance;

        @JsonProperty("percentage")
        private BigDecimal percentage;
    }

}
