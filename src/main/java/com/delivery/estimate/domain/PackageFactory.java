package com.delivery.estimate.domain;

import com.delivery.estimate.offer.NoOffer;
import com.delivery.estimate.offer.OFR001;
import com.delivery.estimate.offer.OFR002;
import com.delivery.estimate.offer.OFR003;
import com.delivery.estimate.offer.Offer;

import java.math.BigDecimal;

public class PackageFactory {
    public static Package createPackage(String packageId,
                                        BigDecimal parcelWeight,
                                        BigDecimal deliveryDistance,
                                        String offerCode,
                                        BigDecimal basePrice) {
        Offer offer = getOffer(offerCode);
        return new Package(packageId, parcelWeight, deliveryDistance, basePrice, offer);
    }

    private static Offer getOffer(String offerCode) {
        Offer offer;
        switch (offerCode) {
            case "OFR001":
                offer = new OFR001();
                break;
            case "OFR002":
                offer = new OFR002();
                break;
            case "OFR003":
                offer = new OFR003();
                break;
            default:
                offer = new NoOffer();
        }
        return offer;
    }
}
