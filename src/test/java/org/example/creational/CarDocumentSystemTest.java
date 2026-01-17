package org.example.creational;

import org.example.creational.builder.Car;
import org.example.creational.factory.Document;
import org.example.creational.factory.PdfDocument;
import org.example.creational.factory.WordDocument;
import org.example.creational.factory.HtmlDocument;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarDocumentSystemTest {

    private Car createValidCar() {
        return new Car.Builder()
                .engine("V8")
                .transmission("Automatic")
                .color("Black")
                .build();
    }

    @Test
    void testGenerateCarDescription() {
        Car car = createValidCar();

        String description = CarDocumentSystem.generateCarDescription(car);
        assertNotNull(description);
        assertTrue(description.contains("V8"));
        assertTrue(description.contains("Black"));
        assertTrue(description.contains("Automatic"));
        assertTrue(description.contains("Car Configuration Report"));
    }

    @Test
    void testGenerateCarDescriptionContainsAllSections() {
        Car car = new Car.Builder()
                .engine("V6")
                .transmission("Manual")
                .color("Red")
                .hasLeatherSeats(true)
                .hasGPS(true)
                .hasABS(true)
                .build();

        String description = CarDocumentSystem.generateCarDescription(car);
        assertTrue(description.contains("Interior Features"));
        assertTrue(description.contains("Exterior Options"));
        assertTrue(description.contains("Safety Features"));
        assertTrue(description.contains("Leather Seats: Yes"));
        assertTrue(description.contains("GPS: Yes"));
        assertTrue(description.contains("ABS: Yes"));
    }

    @Test
    void testCreateCarDocumentPdf() {
        Car car = createValidCar();

        Document doc = CarDocumentSystem.createCarDocument(car, "PDF");
        assertNotNull(doc);
        assertTrue(doc instanceof PdfDocument);
        assertNotNull(doc.getContent());
        assertTrue(doc.getContent().contains("V8"));
    }

    @Test
    void testCreateCarDocumentWord() {
        Car car = createValidCar();

        Document doc = CarDocumentSystem.createCarDocument(car, "Word");
        assertNotNull(doc);
        assertTrue(doc instanceof WordDocument);
        assertNotNull(doc.getContent());
        assertTrue(doc.getContent().contains("V8"));
    }

    @Test
    void testCreateCarDocumentHtml() {
        Car car = createValidCar();

        Document doc = CarDocumentSystem.createCarDocument(car, "HTML");
        assertNotNull(doc);
        assertTrue(doc instanceof HtmlDocument);
        assertNotNull(doc.getContent());
        assertTrue(doc.getContent().contains("V8"));
    }

    @Test
    void testCreateCarDocumentUnknownType() {
        Car car = createValidCar();
        assertThrows(IllegalArgumentException.class, () -> {
            CarDocumentSystem.createCarDocument(car, "Unknown");
        });
    }

    @Test
    void testDocumentContainsCarContent() {
        Car car = new Car.Builder()
                .engine("V8")
                .transmission("Automatic")
                .color("Blue")
                .hasLeatherSeats(true)
                .hasGPS(true)
                .hasSunroof(true)
                .hasABS(true)
                .build();

        Document doc = CarDocumentSystem.createCarDocument(car, "PDF");
        String content = doc.getContent();

        assertTrue(content.contains("V8"));
        assertTrue(content.contains("Automatic"));
        assertTrue(content.contains("Blue"));
        assertTrue(content.contains("Leather Seats: Yes"));
        assertTrue(content.contains("GPS: Yes"));
        assertTrue(content.contains("Sunroof: Yes"));
        assertTrue(content.contains("ABS: Yes"));
    }

    @Test
    void testMain() {
        assertDoesNotThrow(() -> CarDocumentSystem.main(new String[]{}));
    }
}
