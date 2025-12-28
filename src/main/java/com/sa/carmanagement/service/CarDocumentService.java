package com.sa.carmanagement.service;

import com.sa.carmanagement.builder.Car;
import com.sa.carmanagement.dto.CreateDocumentRequest;
import com.sa.carmanagement.dto.DocumentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarDocumentService {

    private final CarService carService;
    private final DocumentService documentService;

    public DocumentResponse createCarDocument(Car car, String documentType) {
        log.info("CarDocumentService: Creating {} document for car: {}", documentType, car.getModel());

        String carDescription = generateCarDescription(car);

        CreateDocumentRequest documentRequest = new CreateDocumentRequest(
                documentType,
                carDescription
        );

        return documentService.createDocument(documentRequest);
    }

    private String generateCarDescription(Car car) {
        StringBuilder description = new StringBuilder();
        description.append("Car Configuration\n");
        description.append("=================\n\n");
        description.append("Model: ").append(car.getModel()).append("\n");
        description.append("Engine: ").append(car.getEngineType()).append("\n");
        description.append("Transmission: ").append(car.getTransmission()).append("\n");

        if (car.getColor() != null && !car.getColor().isEmpty()) {
            description.append("Color: ").append(car.getColor()).append("\n");
        }

        description.append("\nInterior Features:\n");
        description.append("- Leather Seats: ").append(car.isHasLeatherSeats() ? "Yes" : "No").append("\n");
        description.append("- GPS: ").append(car.isHasGPS() ? "Yes" : "No").append("\n");
        description.append("- Sound System: ").append(car.isHasSoundSystem() ? "Yes" : "No").append("\n");

        description.append("\nExterior Features:\n");
        description.append("- Sunroof: ").append(car.isHasSunroof() ? "Yes" : "No").append("\n");

        description.append("\nSafety Features:\n");
        description.append("- ABS: ").append(car.isHasABS() ? "Yes" : "No").append("\n");
        description.append("- Airbags: ").append(car.isHasAirbags() ? "Yes" : "No").append("\n");
        description.append("- Rear Camera: ").append(car.isHasRearCamera() ? "Yes" : "No").append("\n");

        return description.toString();
    }
}
