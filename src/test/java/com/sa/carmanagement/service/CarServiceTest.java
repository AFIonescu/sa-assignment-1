package com.sa.carmanagement.service;

import com.sa.carmanagement.builder.Car;
import com.sa.carmanagement.dto.CreateCarRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    private CarService carService;

    @BeforeEach
    void setUp() {
        carService = new CarService();
        carService.clearAllCars();
    }

    @Test
    void testCreateCarWithAllFeatures() {
        CreateCarRequest request = new CreateCarRequest(
                "BMW M5",
                "V8",
                "Automatic",
                true,
                true,
                true,
                "Black",
                "19-inch Alloy",
                true,
                true,
                true,
                true
        );

        Car car = carService.createCar(request);

        assertNotNull(car);
        assertEquals("BMW M5", car.getModel());
        assertEquals("V8", car.getEngineType());
        assertEquals("Automatic", car.getTransmission());
        assertTrue(car.isHasLeatherSeats());
        assertTrue(car.isHasGPS());
        assertTrue(car.isHasSoundSystem());
        assertEquals("Black", car.getColor());
        assertEquals("19-inch Alloy", car.getRims());
        assertTrue(car.isHasSunroof());
        assertTrue(car.isHasABS());
        assertTrue(car.isHasAirbags());
        assertTrue(car.isHasRearCamera());
    }

    @Test
    void testCreateCarWithMinimalFeatures() {
        CreateCarRequest request = new CreateCarRequest(
                "Toyota Camry",
                "V6",
                "Manual",
                false,
                false,
                false,
                null,
                null,
                false,
                false,
                false,
                false
        );

        Car car = carService.createCar(request);

        assertNotNull(car);
        assertEquals("Toyota Camry", car.getModel());
        assertFalse(car.isHasLeatherSeats());
        assertFalse(car.isHasGPS());
        assertNull(car.getRims());
    }

    @Test
    void testGetAllCars() {
        CreateCarRequest request1 = new CreateCarRequest("Car 1", "V6", "Auto", false, false, false, null, null, false, false, false, false);
        CreateCarRequest request2 = new CreateCarRequest("Car 2", "V8", "Manual", false, false, false, null, null, false, false, false, false);

        carService.createCar(request1);
        carService.createCar(request2);

        List<Car> cars = carService.getAllCars();

        assertEquals(2, cars.size());
    }

    @Test
    void testGetCarCount() {
        assertEquals(0, carService.getCarCount());

        CreateCarRequest request = new CreateCarRequest("Test", "V6", "Auto", false, false, false, null, null, false, false, false, false);
        carService.createCar(request);

        assertEquals(1, carService.getCarCount());
    }

    @Test
    void testClearAllCars() {
        CreateCarRequest request = new CreateCarRequest("Test", "V6", "Auto", false, false, false, null, null, false, false, false, false);
        carService.createCar(request);

        assertEquals(1, carService.getCarCount());

        carService.clearAllCars();

        assertEquals(0, carService.getCarCount());
        assertTrue(carService.getAllCars().isEmpty());
    }
}
