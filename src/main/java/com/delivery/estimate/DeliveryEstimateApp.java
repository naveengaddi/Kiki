package com.delivery.estimate;

import com.delivery.estimate.service.DeliveryApp;
import com.delivery.estimate.service.io.DisplayService;
import com.delivery.estimate.service.io.InputParser;

import java.util.Scanner;


public class DeliveryEstimateApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DeliveryApp deliveryApp = create();
        String choice;
        do {
            Logger.log("Press enter key to start or enter Q to quit");
            choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("Q")) {
                try {
                    deliveryApp.start();
                } catch (Exception exception) {
                    Logger.log("Error occured " + exception.getMessage());
                }
            }
        } while (!choice.equalsIgnoreCase("Q"));
    }

    private static DeliveryApp create() {
        return new DeliveryApp(new InputParser(), new DisplayService());
    }
}
