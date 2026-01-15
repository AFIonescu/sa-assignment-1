# Creational Design Patterns

### 1. Factory Method (Document Editor)
- `DocumentFactory` creates documents based on type (PDF, Word, HTML)
- `Document` interface with `PdfDocument`, `WordDocument`, `HtmlDocument` implementations

### 2. Builder (Car Configuration)
- `Car` class with Builder pattern for step-by-step configuration
- Engine type (V6, V8)
- Transmission (manual, automatic)
- Interior features (leather seats, GPS, sound system)
- Exterior options (color, rims, sunroof)
- Safety features (ABS, airbags, rear camera)

### 3. Combined Solution (Bonus)
- `CarDocumentSystem` combines both patterns
- Configure a car using Builder
- Generate documents describing the car using Factory

## Project Structure

```
src/main/java/org/example/creational/
├── DocumentEditor.java
├── CarConfiguration.java
├── CarDocumentSystem.java
├── factory/
│   ├── Document.java
│   ├── DocumentFactory.java
│   ├── PdfDocument.java
│   ├── WordDocument.java
│   └── HtmlDocument.java
└── builder/
    └── Car.java
```

## Requirements

- Java 17
- Maven

## Build & Run

```bash
mvn clean compile
```

### Run Document Editor
```bash
mvn exec:java -Dexec.mainClass="org.example.creational.DocumentEditor"
```

### Run Car Configuration
```bash
mvn exec:java -Dexec.mainClass="org.example.creational.CarConfiguration"
```

### Run Combined System
```bash
mvn exec:java -Dexec.mainClass="org.example.creational.CarDocumentSystem"
```

## Tests

```bash
mvn test
```

- 23 tests
- 97% coverage

Coverage report: `target/site/jacoco/index.html`
