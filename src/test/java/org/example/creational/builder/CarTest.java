package org.example.creational.builder;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void testFullyConfiguredCar() {
        Car car = new Car.Builder()
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

        assertNotNull(car);
        String carString = car.toString();
        assertTrue(carString.contains("engine=V8"));
        assertTrue(carString.contains("transmission=Automatic"));
        assertTrue(carString.contains("hasLeatherSeats=true"));
        assertTrue(carString.contains("hasGPS=true"));
        assertTrue(carString.contains("hasSoundSystem=true"));
        assertTrue(carString.contains("color=Black"));
        assertTrue(carString.contains("rims=19-inch Alloy"));
        assertTrue(carString.contains("hasSunroof=true"));
        assertTrue(carString.contains("hasABS=true"));
        assertTrue(carString.contains("hasAirbags=true"));
        assertTrue(carString.contains("hasRearCamera=true"));
    }

    @Test
    void testMinimalCar() {
        Car car = new Car.Builder()
                .engine("V6")
                .transmission("Manual")
                .build();

        assertNotNull(car);
        String carString = car.toString();
        assertTrue(carString.contains("engine=V6"));
        assertTrue(carString.contains("transmission=Manual"));
        assertTrue(carString.contains("hasLeatherSeats=false"));
        assertTrue(carString.contains("hasGPS=false"));
    }

    @Test
    void testCarWithInteriorFeatures() {
        Car car = new Car.Builder()
                .engine("V6")
                .hasLeatherSeats(true)
                .hasGPS(true)
                .hasSoundSystem(true)
                .build();

        assertNotNull(car);
        String carString = car.toString();
        assertTrue(carString.contains("hasLeatherSeats=true"));
        assertTrue(carString.contains("hasGPS=true"));
        assertTrue(carString.contains("hasSoundSystem=true"));
    }

    @Test
    void testCarWithExteriorOptions() {
        Car car = new Car.Builder()
                .color("Red")
                .rims("18-inch Sport")
                .hasSunroof(true)
                .build();

        assertNotNull(car);
        String carString = car.toString();
        assertTrue(carString.contains("color=Red"));
        assertTrue(carString.contains("rims=18-inch Sport"));
        assertTrue(carString.contains("hasSunroof=true"));
    }

    @Test
    void testCarWithSafetyFeatures() {
        Car car = new Car.Builder()
                .hasABS(true)
                .hasAirbags(true)
                .hasRearCamera(true)
                .build();

        assertNotNull(car);
        String carString = car.toString();
        assertTrue(carString.contains("hasABS=true"));
        assertTrue(carString.contains("hasAirbags=true"));
        assertTrue(carString.contains("hasRearCamera=true"));
    }
}
