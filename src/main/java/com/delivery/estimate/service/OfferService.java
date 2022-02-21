package com.delivery.estimate.service;

import com.delivery.estimate.offer.NoOffer;
import com.delivery.estimate.offer.Offer;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class OfferService {
    private final Map<String, Offer> offerCodes;

    public OfferService() {
        offerCodes = new HashMap<>();
        seedInitialData(); //Usually this will fetch from db.
    }

    public Offer getOffer(String offerCode) {
        if (offerCodes.containsKey(offerCode)) {
            return offerCodes.get(offerCode);
        }
        return new NoOffer();
    }

    private void seedInitialData() {
        offerCodes.put("OFR001", new Offer("OFR001",
                BigDecimal.valueOf(70),
                BigDecimal.valueOf(200),
                BigDecimal.valueOf(0),
                BigDecimal.valueOf(199),
                BigDecimal.valueOf(10)));
        offerCodes.put("OFR002", new Offer("OFR002",
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(250),
                BigDecimal.valueOf(50),
                BigDecimal.valueOf(150),
                BigDecimal.valueOf(7)));
        offerCodes.put("OFR003", new Offer("OFR003",
                BigDecimal.valueOf(10),
                BigDecimal.valueOf(150),
                BigDecimal.valueOf(50),
                BigDecimal.valueOf(250),
                BigDecimal.valueOf(5)));
    }
}
