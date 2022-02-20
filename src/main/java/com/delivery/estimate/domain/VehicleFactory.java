package com.delivery.estimate.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VehicleFactory {
    public static List<Vehicle> vehicles(int numberOfVehiclesRequired, BigDecimal maxLoad, BigDecimal maxSpeed) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        for (int count = 1; count <= numberOfVehiclesRequired; count++) {
            vehicles.add(new Vehicle(maxLoad, maxSpeed, BigDecimal.ZERO));
        }
        return vehicles;
    }
}
