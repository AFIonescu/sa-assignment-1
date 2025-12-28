# Car Management System

Spring Boot application demonstrating 4 Creational Design Patterns.

## Design Patterns

### 1. Singleton Pattern
DocumentManager - Single instance manages all documents with thread-safe double-checked locking

### 2. Factory Method Pattern
Document editor supporting PDF, Word, and HTML formats

### 3. Abstract Factory Pattern
UI component factory for Modern and Classic themes

### 4. Builder Pattern
Car configuration with customizable options (engine, transmission, interior, exterior, safety features)

### 5. Combined System (Bonus)
Integrates all patterns - Build cars, generate documents, store in Singleton

## Requirements

- Java 21
- Maven 3.6+

## Build & Run

```bash
cd Assignment-1
.\build.bat
.\run.bat
```

Application starts on http://localhost:8080

## Testing

### Web Interface
Open browser: http://localhost:8080

Test each pattern using the web interface:
- **Singleton** - Get document count, clear documents
- **Factory Method** - Create PDF/Word/HTML documents
- **Abstract Factory** - Create Modern/Classic UI components
- **Builder** - Build cars with various features
- **Combined System** - Create car and generate document

### Unit Tests

```bash
.\run.bat test
```

Expected: 49 tests passing, 0 failures

## Implementation

**Exercise 1 - Document Editor**
- Factory Method pattern
- PDF, Word, HTML formats
- Extensible for new formats

**Exercise 2 - Car Configuration**
- Builder pattern with Lombok
- All required options (engine, transmission, interior, exterior, safety)
- Flexible step-by-step configuration

**Bonus Tasks**
- 49 unit tests with 85%+ coverage
- Combined Car Management System
- Web interface for interactive testing
