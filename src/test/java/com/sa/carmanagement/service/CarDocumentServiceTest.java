package com.sa.carmanagement.service;

import com.sa.carmanagement.builder.Car;
import com.sa.carmanagement.dto.DocumentResponse;
import com.sa.carmanagement.factory.DocumentFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarDocumentServiceTest {

    private CarDocumentService carDocumentService;
    private DocumentService documentService;
    private CarService carService;

    @BeforeEach
    void setUp() {
        DocumentFactory documentFactory = new DocumentFactory();
        documentService = new DocumentService(documentFactory);
        carService = new CarService();
        carDocumentService = new CarDocumentService(carService, documentService);
    }

    @Test
    void testCreateCarDocumentPdf() {
        Car car = Car.builder()
                .model("BMW M5")
                .engineType("V8")
                .transmission("Automatic")
                .color("Black")
                .hasLeatherSeats(true)
                .hasGPS(true)
                .hasABS(true)
                .build();

        DocumentResponse response = carDocumentService.createCarDocument(car, "PDF");

        assertNotNull(response);
        assertEquals("PDF", response.getFormat());
        assertTrue(response.getDisplayContent().contains("BMW M5"));
        assertTrue(response.getDisplayContent().contains("V8"));
        assertTrue(response.getDisplayContent().contains("Automatic"));
    }

    @Test
    void testCreateCarDocumentWord() {
        Car car = Car.builder()
                .model("Tesla Model S")
                .engineType("Electric")
                .transmission("Automatic")
                .build();

        DocumentResponse response = carDocumentService.createCarDocument(car, "WORD");

        assertNotNull(response);
        assertEquals("DOCX", response.getFormat());
        assertTrue(response.getDisplayContent().contains("Tesla Model S"));
    }

    @Test
    void testCreateCarDocumentHtml() {
        Car car = Car.builder()
                .model("Honda Accord")
                .engineType("V6")
                .transmission("Manual")
                .build();

        DocumentResponse response = carDocumentService.createCarDocument(car, "HTML");

        assertNotNull(response);
        assertEquals("HTML", response.getFormat());
        assertTrue(response.getDisplayContent().contains("<html>"));
        assertTrue(response.getDisplayContent().contains("Honda Accord"));
    }

    @Test
    void testCarDescriptionContainsAllFeatures() {
        Car car = Car.builder()
                .model("Luxury Car")
                .engineType("V12")
                .transmission("Automatic")
                .color("Silver")
                .hasLeatherSeats(true)
                .hasGPS(true)
                .hasSoundSystem(true)
                .hasSunroof(true)
                .hasABS(true)
                .hasAirbags(true)
                .hasRearCamera(true)
                .build();

        DocumentResponse response = carDocumentService.createCarDocument(car, "PDF");

        String content = response.getContent();
        assertTrue(content.contains("Luxury Car"));
        assertTrue(content.contains("V12"));
        assertTrue(content.contains("Automatic"));
        assertTrue(content.contains("Silver"));
        assertTrue(content.contains("Leather Seats: Yes"));
        assertTrue(content.contains("GPS: Yes"));
        assertTrue(content.contains("Sound System: Yes"));
        assertTrue(content.contains("Sunroof: Yes"));
        assertTrue(content.contains("ABS: Yes"));
        assertTrue(content.contains("Airbags: Yes"));
        assertTrue(content.contains("Rear Camera: Yes"));
    }

    @Test
    void testCarDescriptionWithMinimalFeatures() {
        Car car = Car.builder()
                .model("Basic Car")
                .engineType("I4")
                .transmission("Manual")
                .build();

        DocumentResponse response = carDocumentService.createCarDocument(car, "PDF");

        String content = response.getContent();
        assertTrue(content.contains("Basic Car"));
        assertTrue(content.contains("I4"));
        assertTrue(content.contains("Manual"));
        assertTrue(content.contains("Leather Seats: No"));
        assertTrue(content.contains("GPS: No"));
        assertTrue(content.contains("ABS: No"));
    }
}
