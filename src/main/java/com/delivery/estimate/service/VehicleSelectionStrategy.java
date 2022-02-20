package com.delivery.estimate.service;

import com.delivery.estimate.domain.Vehicle;

import java.util.Comparator;
import java.util.List;

public class VehicleSelectionStrategy {

    Vehicle findVehicleWithMinimumWaitTime(List<Vehicle> vehicles) {
        return vehicles.stream().min(Comparator.comparing(Vehicle::getAvailableAt)).get();
    }
}
