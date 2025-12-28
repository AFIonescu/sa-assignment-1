package com.sa.carmanagement.controller;

import com.sa.carmanagement.builder.Car;
import com.sa.carmanagement.dto.CreateCarRequest;
import com.sa.carmanagement.dto.DocumentResponse;
import com.sa.carmanagement.service.CarDocumentService;
import com.sa.carmanagement.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car-documents")
@RequiredArgsConstructor
public class CarDocumentController {

    private final CarService carService;
    private final CarDocumentService carDocumentService;

    @PostMapping
    public ResponseEntity<DocumentResponse> createCarWithDocument(
            @Valid @RequestBody CreateCarRequest carRequest,
            @RequestParam(defaultValue = "PDF") String documentType) {

        Car car = carService.createCar(carRequest);
        DocumentResponse document = carDocumentService.createCarDocument(car, documentType);

        return ResponseEntity.status(HttpStatus.CREATED).body(document);
    }
}
