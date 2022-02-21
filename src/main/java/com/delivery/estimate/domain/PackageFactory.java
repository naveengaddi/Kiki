package com.delivery.estimate.domain;

import com.delivery.estimate.offer.NoOffer;
import com.delivery.estimate.offer.Offer;

import java.math.BigDecimal;

public class PackageFactory {
    public static Package createPackage(String packageId,
                                        BigDecimal parcelWeight,
                                        BigDecimal deliveryDistance,
                                        String offerCode,
                                        BigDecimal basePrice) {
        Offer offer = getOffer(offerCode);
        return new Package(packageId, parcelWeight, deliveryDistance, basePrice, offer, null);
    }

    private static Offer getOffer(String offerCode) {
        Offer offer;
        switch (offerCode) {
            case "OFR001":
                offer = new Offer("OFR001",
                        BigDecimal.valueOf(70),
                        BigDecimal.valueOf(200),
                        BigDecimal.valueOf(0),
                        BigDecimal.valueOf(199),
                        BigDecimal.valueOf(10));
                break;
            case "OFR002":
                offer = new Offer("OFR002",
                        BigDecimal.valueOf(100),
                        BigDecimal.valueOf(250),
                        BigDecimal.valueOf(50),
                        BigDecimal.valueOf(150),
                        BigDecimal.valueOf(7));
                break;
            case "OFR003":
                offer = new Offer("OFR003",
                        BigDecimal.valueOf(10),
                        BigDecimal.valueOf(150),
                        BigDecimal.valueOf(50),
                        BigDecimal.valueOf(250),
                        BigDecimal.valueOf(5));
                break;
            default:
                offer = new NoOffer();
        }
        return offer;
    }
}
