package com.delivery.estimate.domain;

import com.delivery.estimate.offer.Offer;
import com.delivery.estimate.service.OfferService;

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
        return new OfferService().getOffer(offerCode);
    }
}
