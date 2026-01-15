package org.example.creational;

import org.example.creational.builder.Car;
import org.example.creational.factory.Document;
import org.example.creational.factory.DocumentFactory;

public class CarDocumentSystem {

    public static String generateCarDescription(Car car) {
        return car.toString();
    }

    public static Document createCarDocument(Car car, String documentType) {
        Document doc = DocumentFactory.createDocument(documentType);
        System.out.println("Created " + documentType + " document for: " + car);
        return doc;
    }

    public static void main(String[] args) {
        // Configure a car using Builder
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

        System.out.println("Configured car: " + car);

        // Create documents describing the car using Factory
        Document pdfDoc = createCarDocument(car, "PDF");
        pdfDoc.open();

        Document wordDoc = createCarDocument(car, "Word");
        wordDoc.open();

        Document htmlDoc = createCarDocument(car, "HTML");
        htmlDoc.open();
    }
}
