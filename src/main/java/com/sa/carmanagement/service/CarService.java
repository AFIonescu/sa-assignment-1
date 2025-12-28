package com.sa.carmanagement.service;

import com.sa.carmanagement.builder.Car;
import com.sa.carmanagement.dto.CreateCarRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CarService {

    private final List<Car> cars = new ArrayList<>();

    public Car createCar(CreateCarRequest request) {
        log.info("CarService: Creating car - Model: {}", request.getModel());

        // Use Builder pattern (Lombok @Builder) to create Car
        Car car = Car.builder()
                .model(request.getModel())
                .engineType(request.getEngineType())
                .transmission(request.getTransmission())
                .hasLeatherSeats(request.isHasLeatherSeats())
                .hasGPS(request.isHasGPS())
                .hasSoundSystem(request.isHasSoundSystem())
                .color(request.getColor())
                .hasSunroof(request.isHasSunroof())
                .hasABS(request.isHasABS())
                .hasAirbags(request.isHasAirbags())
                .hasRearCamera(request.isHasRearCamera())
                .build();

        cars.add(car);
        log.info("CarService: Car created successfully - {}", car);

        return car;
    }

    public List<Car> getAllCars() {
        log.info("CarService: Retrieving all cars - Count: {}", cars.size());
        return new ArrayList<>(cars);
    }

    public int getCarCount() {
        return cars.size();
    }

    public void clearAllCars() {
        log.info("CarService: Clearing all cars");
        cars.clear();
    }
}
