package org.example.creational;

import org.example.creational.builder.Car;
import org.example.creational.factory.Document;
import org.example.creational.factory.PdfDocument;
import org.example.creational.factory.WordDocument;
import org.example.creational.factory.HtmlDocument;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarDocumentSystemTest {

    @Test
    void testGenerateCarDescription() {
        Car car = new Car.Builder()
                .engine("V8")
                .color("Black")
                .build();

        String description = CarDocumentSystem.generateCarDescription(car);
        assertNotNull(description);
        assertTrue(description.contains("V8"));
        assertTrue(description.contains("Black"));
    }

    @Test
    void testCreateCarDocumentPdf() {
        Car car = new Car.Builder()
                .engine("V6")
                .build();

        Document doc = CarDocumentSystem.createCarDocument(car, "PDF");
        assertNotNull(doc);
        assertTrue(doc instanceof PdfDocument);
    }

    @Test
    void testCreateCarDocumentWord() {
        Car car = new Car.Builder()
                .engine("V6")
                .build();

        Document doc = CarDocumentSystem.createCarDocument(car, "Word");
        assertNotNull(doc);
        assertTrue(doc instanceof WordDocument);
    }

    @Test
    void testCreateCarDocumentHtml() {
        Car car = new Car.Builder()
                .engine("V6")
                .build();

        Document doc = CarDocumentSystem.createCarDocument(car, "HTML");
        assertNotNull(doc);
        assertTrue(doc instanceof HtmlDocument);
    }

    @Test
    void testMain() {
        assertDoesNotThrow(() -> CarDocumentSystem.main(new String[]{}));
    }
}
