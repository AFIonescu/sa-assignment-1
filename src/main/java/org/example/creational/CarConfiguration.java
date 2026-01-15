package org.example.creational;

import org.example.creational.builder.Car;

public class CarConfiguration {

    public static void main(String[] args) {
        // Fully configured car
        Car car1 = new Car.Builder()
                .engine("V8")
                .transmission("Automatic")
                .hasLeatherSeats(true)
                .hasGPS(true)
                .hasSoundSystem(true)
                .color("Black")
                .rims("19-inch Alloy")
                .hasSunroof(true)
                .hasABS(true)
                .hasAirbags(true)
                .hasRearCamera(true)
                .build();

        System.out.println(car1);

        // Minimal car (only required options)
        Car car2 = new Car.Builder()
                .engine("V6")
                .transmission("Manual")
                .color("Red")
                .build();

        System.out.println(car2);
    }
}
