package com.sa.carmanagement.controller;

import com.sa.carmanagement.builder.Car;
import com.sa.carmanagement.dto.CreateCarRequest;
import com.sa.carmanagement.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<Car> createCar(@Valid @RequestBody CreateCarRequest request) {
        Car car = carService.createCar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(car);
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getCarCount() {
        return ResponseEntity.ok(carService.getCarCount());
    }

    @DeleteMapping
    public ResponseEntity<Void> clearAllCars() {
        carService.clearAllCars();
        return ResponseEntity.noContent().build();
    }
}
