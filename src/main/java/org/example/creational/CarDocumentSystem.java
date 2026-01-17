package org.example.creational;

import org.example.creational.builder.Car;
import org.example.creational.factory.Document;
import org.example.creational.factory.DocumentFactory;

public class CarDocumentSystem {

    public static String generateCarDescription(Car car) {
        StringBuilder sb = new StringBuilder();
        sb.append("Car Configuration Report\n");
        sb.append("========================\n\n");
        sb.append("Engine: ").append(car.getEngine()).append("\n");
        sb.append("Transmission: ").append(car.getTransmission()).append("\n");
        sb.append("Color: ").append(car.getColor()).append("\n");
        sb.append("\nInterior Features:\n");
        sb.append("  - Leather Seats: ").append(car.hasLeatherSeats() ? "Yes" : "No").append("\n");
        sb.append("  - GPS: ").append(car.hasGPS() ? "Yes" : "No").append("\n");
        sb.append("  - Sound System: ").append(car.hasSoundSystem() ? "Yes" : "No").append("\n");
        sb.append("\nExterior Options:\n");
        sb.append("  - Rims: ").append(car.getRims() != null ? car.getRims() : "Standard").append("\n");
        sb.append("  - Sunroof: ").append(car.hasSunroof() ? "Yes" : "No").append("\n");
        sb.append("\nSafety Features:\n");
        sb.append("  - ABS: ").append(car.hasABS() ? "Yes" : "No").append("\n");
        sb.append("  - Airbags: ").append(car.hasAirbags() ? "Yes" : "No").append("\n");
        sb.append("  - Rear Camera: ").append(car.hasRearCamera() ? "Yes" : "No").append("\n");
        return sb.toString();
    }

    public static Document createCarDocument(Car car, String documentType) {
        Document doc = DocumentFactory.createDocument(documentType);
        String carDescription = generateCarDescription(car);
        doc.setContent(carDescription);
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
        System.out.println();

        // Create PDF document for the car
        Document pdfDoc = createCarDocument(car, "PDF");
        pdfDoc.open();
        pdfDoc.display();
        pdfDoc.save();
        System.out.println();

        // Create Word document for the car
        Document wordDoc = createCarDocument(car, "Word");
        wordDoc.open();
        wordDoc.display();
        wordDoc.save();
        System.out.println();

        // Create HTML document for the car
        Document htmlDoc = createCarDocument(car, "HTML");
        htmlDoc.open();
        htmlDoc.display();
        htmlDoc.save();
    }
}
