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
        assertEquals("V8", car.getEngine());
        assertEquals("Automatic", car.getTransmission());
        assertTrue(car.hasLeatherSeats());
        assertTrue(car.hasGPS());
        assertTrue(car.hasSoundSystem());
        assertEquals("Black", car.getColor());
        assertEquals("19-inch Alloy", car.getRims());
        assertTrue(car.hasSunroof());
        assertTrue(car.hasABS());
        assertTrue(car.hasAirbags());
        assertTrue(car.hasRearCamera());
    }

    @Test
    void testMinimalCar() {
        Car car = new Car.Builder()
                .engine("V6")
                .transmission("Manual")
                .color("White")
                .build();

        assertNotNull(car);
        assertEquals("V6", car.getEngine());
        assertEquals("Manual", car.getTransmission());
        assertEquals("White", car.getColor());
        assertFalse(car.hasLeatherSeats());
        assertFalse(car.hasGPS());
    }

    @Test
    void testCarWithInteriorFeatures() {
        Car car = new Car.Builder()
                .engine("V6")
                .transmission("Automatic")
                .color("Blue")
                .hasLeatherSeats(true)
                .hasGPS(true)
                .hasSoundSystem(true)
                .build();

        assertNotNull(car);
        assertTrue(car.hasLeatherSeats());
        assertTrue(car.hasGPS());
        assertTrue(car.hasSoundSystem());
    }

    @Test
    void testCarWithExteriorOptions() {
        Car car = new Car.Builder()
                .engine("V8")
                .transmission("Automatic")
                .color("Red")
                .rims("18-inch Sport")
                .hasSunroof(true)
                .build();

        assertNotNull(car);
        assertEquals("Red", car.getColor());
        assertEquals("18-inch Sport", car.getRims());
        assertTrue(car.hasSunroof());
    }

    @Test
    void testCarWithSafetyFeatures() {
        Car car = new Car.Builder()
                .engine("V6")
                .transmission("Manual")
                .color("Silver")
                .hasABS(true)
                .hasAirbags(true)
                .hasRearCamera(true)
                .build();

        assertNotNull(car);
        assertTrue(car.hasABS());
        assertTrue(car.hasAirbags());
        assertTrue(car.hasRearCamera());
    }

    @Test
    void testValidationMissingEngine() {
        assertThrows(IllegalStateException.class, () -> {
            new Car.Builder()
                    .transmission("Automatic")
                    .color("Black")
                    .build();
        });
    }

    @Test
    void testValidationMissingTransmission() {
        assertThrows(IllegalStateException.class, () -> {
            new Car.Builder()
                    .engine("V8")
                    .color("Black")
                    .build();
        });
    }

    @Test
    void testValidationMissingColor() {
        assertThrows(IllegalStateException.class, () -> {
            new Car.Builder()
                    .engine("V8")
                    .transmission("Automatic")
                    .build();
        });
    }

    @Test
    void testValidationEmptyEngine() {
        assertThrows(IllegalStateException.class, () -> {
            new Car.Builder()
                    .engine("")
                    .transmission("Automatic")
                    .color("Black")
                    .build();
        });
    }

    @Test
    void testToString() {
        Car car = new Car.Builder()
                .engine("V8")
                .transmission("Automatic")
                .color("Black")
                .build();

        String carString = car.toString();
        assertTrue(carString.contains("engine=V8"));
        assertTrue(carString.contains("transmission=Automatic"));
        assertTrue(carString.contains("color=Black"));
    }
}
