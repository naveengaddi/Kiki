package com.delivery.estimate.service.technical;

import com.delivery.estimate.domain.Vehicle;

import java.util.Comparator;
import java.util.List;

public class VehicleSelectionStrategy {

    public Vehicle findVehicleWithMinimumWaitTime(List<Vehicle> vehicles) {
        return vehicles.stream().min(Comparator.comparing(Vehicle::getAvailableAt)).get();
    }
}
