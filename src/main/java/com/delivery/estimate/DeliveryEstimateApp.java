package com.delivery.estimate;

import com.delivery.estimate.service.DeliveryEstimateCalculator;
import com.delivery.estimate.service.DisplayService;
import com.delivery.estimate.service.InputParser;
import com.delivery.estimate.service.PackageSelectionStrategy;
import com.delivery.estimate.service.VehicleSelectionStrategy;

import java.util.Scanner;


public class DeliveryEstimateApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DeliveryEstimateCalculator deliveryEstimateCalculator = createDeliveryEstimateCalculator();
        String choice;
        do {
            Logger.log("Press enter key to start or enter Q to quit");
            choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("Q")) {
                try {
                    deliveryEstimateCalculator.start();
                } catch (Exception exception) {
                    Logger.log("Error occured " + exception.getMessage());
                }
            }
        } while (!choice.equalsIgnoreCase("Q"));
    }

    private static DeliveryEstimateCalculator createDeliveryEstimateCalculator() {
        return new DeliveryEstimateCalculator(
                new InputParser(),
                new DisplayService(),
                new VehicleSelectionStrategy(),
                new PackageSelectionStrategy()
        );
    }
}
