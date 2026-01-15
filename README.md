# Car Management System

Spring Boot application demonstrating Creational Design Patterns.

## Design Patterns Implemented

### 1. Factory Method Pattern (Document Editor)
- `DocumentFactory` creates documents based on type (PDF, Word, HTML)
- Each document type has its own implementation (`PdfDocument`, `WordDocument`, `HtmlDocument`)
- New formats can be added without modifying existing code
- Core logic is decoupled from specific document types

### 2. Builder Pattern (Car Configuration)
- `Car` class uses Lombok `@Builder` for step-by-step configuration
- Supports all required options:
  - Engine type (V6, V8, etc.)
  - Transmission (manual, automatic)
  - Interior features (leather seats, GPS, sound system)
  - Exterior options (color, rims, sunroof)
  - Safety features (ABS, airbags, rear camera)
- Optional configurations supported
- Final car object is always valid

### 3. Combined System (Bonus)
- `CarDocumentService` combines both patterns
- Configure a car using Builder pattern
- Generate a document describing the car using Factory Method pattern

## Project Structure

```
src/main/java/com/sa/carmanagement/
├── builder/
│   └── Car.java                    # Builder pattern
├── factory/
│   ├── Document.java               # Document interface
│   ├── DocumentFactory.java        # Factory Method pattern
│   ├── PdfDocument.java
│   ├── WordDocument.java
│   └── HtmlDocument.java
├── service/
│   ├── CarService.java
│   ├── DocumentService.java
│   └── CarDocumentService.java     # Combined solution
├── controller/
│   ├── CarController.java
│   ├── DocumentController.java
│   └── CarDocumentController.java
├── dto/
│   ├── CreateCarRequest.java
│   ├── CreateDocumentRequest.java
│   └── DocumentResponse.java
└── CarManagementApplication.java
```

## Requirements

- Java 17
- Maven 3.6+

## Build & Run

```bash
cd Assignment-1
mvn clean install
mvn spring-boot:run
```

Application starts on http://localhost:8080

## API Endpoints

### Documents (Factory Method)
- `POST /api/documents` - Create a document (PDF, WORD, HTML)
- `GET /api/documents` - Get all documents
- `GET /api/documents/count` - Get document count
- `DELETE /api/documents` - Clear all documents

### Cars (Builder)
- `POST /api/cars` - Create a configured car
- `GET /api/cars` - Get all cars
- `GET /api/cars/count` - Get car count
- `DELETE /api/cars` - Clear all cars

### Combined (Bonus)
- `POST /api/car-documents?documentType=PDF` - Create car and generate document

## Example Requests

### Create a Document
```json
POST /api/documents
{
  "type": "PDF",
  "content": "This is a test document"
}
```

### Create a Car
```json
POST /api/cars
{
  "model": "BMW M5",
  "engineType": "V8",
  "transmission": "Automatic",
  "hasLeatherSeats": true,
  "hasGPS": true,
  "hasSoundSystem": true,
  "color": "Black",
  "rims": "19-inch Alloy",
  "hasSunroof": true,
  "hasABS": true,
  "hasAirbags": true,
  "hasRearCamera": true
}
```

### Create Car with Document
```json
POST /api/car-documents?documentType=PDF
{
  "model": "Tesla Model S",
  "engineType": "Electric",
  "transmission": "Automatic",
  "hasGPS": true,
  "hasABS": true,
  "hasAirbags": true
}
```

## Testing

Run all tests:
```bash
mvn test
```

Coverage report available at: `target/site/jacoco/index.html`

### Test Coverage
- Factory Pattern: 100%
- Service Layer: 96%
- **Overall: 88%** 

## Unit Tests

| Test Class | Tests | Description |
|------------|-------|-------------|
| `DocumentFactoryTest` | 9 | Factory Method pattern tests |
| `CarTest` | 5 | Builder pattern tests |
| `DocumentServiceTest` | 6 | Document service tests |
| `CarServiceTest` | 5 | Car service tests |
| `CarDocumentServiceTest` | 5 | Combined solution tests |
| `DocumentControllerTest` | 4 | Controller endpoint tests |
| **Total** | **34** | |
