package com.delivery.estimate;

import com.delivery.estimate.service.DeliveryEstimateApp;
import com.delivery.estimate.service.io.DisplayService;
import com.delivery.estimate.service.io.InputParser;

import java.util.Scanner;


public class DeliveryEstimateAppMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DeliveryEstimateApp deliveryEstimateApp = create();
        String choice;
        do {
            Logger.log("Press enter key to start or enter Q to quit");
            choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("Q")) {
                try {
                    deliveryEstimateApp.start();
                } catch (Exception exception) {
                    Logger.log("Error occured " + exception.getMessage());
                }
            }
        } while (!choice.equalsIgnoreCase("Q"));
    }

    private static DeliveryEstimateApp create() {
        return new DeliveryEstimateApp(new InputParser(), new DisplayService());
    }
}
