## Kiki Delivery Estimate App

is a courier service app which estimates the total delivery cost and delivery time of each package with offer code (if
applicable).

## System Requirements

1. JDK 13 or later
2. Gradle 7.1 or later

## Usage

1. To run all tests
    ```shell
    ./gradlew test
    ```
2. To build jar (App snapshot will be generated in `build/libs/<App>.jar` path)
   ```shell
   ./gradlew clean build
   ```


3. To start the application
   ```shell
    java -jar build/libs/KikiDeliveryEstimate-1.0.jar
   ```

## To Estimate cost and delivery time

1. Clean build app
   `./gradlew clean build`
2. Start the app
   `java -jar build/libs/KikiDeliveryEstimate-1.0.jar`
3. Provide input (like example below)

![](example.png)

Note:
1. Package Weight is in Kilograms(KG)
2. Delivery Distance is in Kilometers(KM)
3. Max Speed is in KM/Hour
4. Estimated DeliveryTime is in hours

## Input and Output format

Input:

```
baseDeliveryCost  numberOfPackages
packageId1 packageWeight1 deliveryDistance1 offerCode1
packageId2 packageWeight2 deliveryDistance2 offerCode2
...
...
``` 

Output format:

```
packageId1 discountAmount1 totalCost1 estimatedDeliveryTime1
packageId2 discountAmount2 totalCost2 estimatedDeliveryTime2
...
...
```


### Example input and output

Input:
```text
100 5
PKG1 50 30 OFR001
PKG2 75 125 OFFR0008
PKG3 175 100 OFR003
PKG4 110 60 OFR002
PKG5 155 95 NA
2 70 200
```

Output:
```text
PKG1 0 750 3.98
PKG2 0 1475 1.78
PKG3 0 2350 1.42
PKG4 105 1395 0.85
PKG5 0 2125 4.19
```


### Architecture diagram

![](architechture.png)


### Scope for improvement
1. is BigDecimal needed for time, weight, distance ?
2. DeliveryEstimateService can be improved further.

