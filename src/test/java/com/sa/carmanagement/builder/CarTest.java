package com.sa.carmanagement.builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void testCarBuilderWithAllFeatures() {
        Car car = Car.builder()
                .model("BMW M5")
                .engineType("V8")
                .transmission("Automatic")
                .hasLeatherSeats(true)
                .hasGPS(true)
                .hasSoundSystem(true)
                .color("Black")
                .hasSunroof(true)
                .hasABS(true)
                .hasAirbags(true)
                .hasRearCamera(true)
                .build();

        assertNotNull(car);
        assertEquals("BMW M5", car.getModel());
        assertEquals("V8", car.getEngineType());
        assertEquals("Automatic", car.getTransmission());
        assertTrue(car.isHasLeatherSeats());
        assertTrue(car.isHasGPS());
        assertTrue(car.isHasSoundSystem());
        assertEquals("Black", car.getColor());
        assertTrue(car.isHasSunroof());
        assertTrue(car.isHasABS());
        assertTrue(car.isHasAirbags());
        assertTrue(car.isHasRearCamera());
    }

    @Test
    void testCarBuilderWithMinimalFeatures() {
        Car car = Car.builder()
                .model("Toyota Camry")
                .engineType("V6")
                .transmission("Manual")
                .build();

        assertNotNull(car);
        assertEquals("Toyota Camry", car.getModel());
        assertEquals("V6", car.getEngineType());
        assertEquals("Manual", car.getTransmission());
        assertFalse(car.isHasLeatherSeats());
        assertFalse(car.isHasGPS());
        assertFalse(car.isHasSoundSystem());
        assertNull(car.getColor());
        assertFalse(car.isHasSunroof());
        assertFalse(car.isHasABS());
        assertFalse(car.isHasAirbags());
        assertFalse(car.isHasRearCamera());
    }

    @Test
    void testCarBuilderPartialFeatures() {
        Car car = Car.builder()
                .model("Honda Accord")
                .engineType("V6")
                .transmission("Automatic")
                .hasGPS(true)
                .color("Red")
                .hasABS(true)
                .hasAirbags(true)
                .build();

        assertNotNull(car);
        assertEquals("Honda Accord", car.getModel());
        assertTrue(car.isHasGPS());
        assertEquals("Red", car.getColor());
        assertTrue(car.isHasABS());
        assertTrue(car.isHasAirbags());
        assertFalse(car.isHasLeatherSeats());
        assertFalse(car.isHasSoundSystem());
        assertFalse(car.isHasSunroof());
        assertFalse(car.isHasRearCamera());
    }

    @Test
    void testCarToString() {
        Car car = Car.builder()
                .model("Tesla Model S")
                .engineType("Electric")
                .transmission("Automatic")
                .build();

        String carString = car.toString();
        assertNotNull(carString);
        assertTrue(carString.contains("Tesla Model S"));
        assertTrue(carString.contains("Electric"));
        assertTrue(carString.contains("Automatic"));
    }

    @Test
    void testMultipleCarsIndependence() {
        Car car1 = Car.builder()
                .model("Car 1")
                .engineType("V6")
                .transmission("Manual")
                .hasGPS(true)
                .build();

        Car car2 = Car.builder()
                .model("Car 2")
                .engineType("V8")
                .transmission("Automatic")
                .hasGPS(false)
                .build();

        assertNotEquals(car1.getModel(), car2.getModel());
        assertNotEquals(car1.getEngineType(), car2.getEngineType());
        assertNotEquals(car1.isHasGPS(), car2.isHasGPS());
    }
}
