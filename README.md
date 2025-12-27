# Design Patterns Assignment

Implementation of **Factory Pattern** and **Builder Pattern** in TypeScript with 85%+ test coverage.

## Overview

1. **Document Editor** - Factory Pattern for creating PDF, Word, and HTML documents
2. **Car Configuration** - Builder Pattern for step-by-step car configuration
3. **Car Management System** (Bonus) - Combines both patterns

## Installation

```bash
npm install
```

## Usage

```bash
npm test              # Run tests
npm run test:coverage # Run tests with coverage
npm start             # Run demo
npm run build         # Compile TypeScript
```

## Expected Results

### Running Tests (`npm test`)
- ✅ **108 tests passing** across 7 test suites
- Completes in ~2-3 seconds
- No errors or warnings

### Running Coverage (`npm run test:coverage`)
```
All files: 88.65% Statements | 89.07% Branches | 85.86% Functions | 92.95% Lines
```
- ✅ All metrics exceed 85% requirement
- Generates HTML coverage report in `coverage/` folder

### Running Demo (`npm start`)
Displays:
- Document creation examples (PDF, Word, HTML)
- Car configuration examples (Custom, Economy, Luxury, Sports, Electric)
- Car Management System demo with document generation

### Building (`npm run build`)
- ✅ Successful TypeScript compilation
- Output in `dist/` folder

## Quick Examples

### Document Editor (Factory Pattern)

```typescript
import { DocumentEditor, DocumentType } from './document-editor';

const editor = new DocumentEditor();
editor.createNewDocument(DocumentType.PDF, 'Content here');
editor.addMetadata('author', 'John Doe');
editor.saveDocument('output.pdf');
```

### Car Configuration (Builder Pattern)

```typescript
import { CarBuilder, EngineType, TransmissionType } from './car-configuration';

const car = new CarBuilder()
  .setModel('Sports Car')
  .setEngine(EngineType.V8)
  .setTransmission(TransmissionType.AUTOMATIC)
  .setExteriorOptions({ color: 'Red' })
  .build();
```

### Car Management System (Bonus)

```typescript
import { CarManagementSystem } from './car-management-system';

const cms = new CarManagementSystem();
cms.addCar('car-001', car);
cms.generateCarDocument('car-001', DocumentType.HTML);
cms.generateFleetDocument(DocumentType.PDF);
```

## Project Structure

```
src/
├── document-editor/        # Factory Pattern implementation
├── car-configuration/      # Builder Pattern implementation
├── car-management-system/  # Combined system
└── __tests__/             # Unit tests (108 tests)
```

## Technologies

- TypeScript 5.9.3
- Jest 30.2.0
- Node.js
