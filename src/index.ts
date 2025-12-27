/**
 * Main entry point demonstrating the usage of both systems
 */

import { DocumentEditor } from './document-editor/DocumentEditor';
import { DocumentType } from './document-editor/DocumentFactory';
import { CarBuilder } from './car-configuration/CarBuilder';
import { CarDirector } from './car-configuration/CarDirector';
import { EngineType, TransmissionType } from './car-configuration/CarOptions';
import { CarManagementSystem } from './car-management-system/CarManagementSystem';

console.log('='.repeat(80));
console.log('EXERCISE 1: DOCUMENT EDITOR (Factory Pattern)');
console.log('='.repeat(80));

// Create a document editor
const editor = new DocumentEditor();

// Example 1: Create and save a PDF document
console.log('\n--- Creating PDF Document ---');
editor.createNewDocument(DocumentType.PDF, 'This is a sample PDF document content.');
editor.addMetadata('author', 'John Doe');
editor.addMetadata('title', 'Sample PDF');
console.log(editor.displayDocument());

// Example 2: Create and save a Word document
console.log('\n--- Creating Word Document ---');
editor.createNewDocument(DocumentType.WORD, 'This is a sample Word document content.');
editor.addMetadata('author', 'Jane Smith');
editor.addMetadata('title', 'Sample Word Document');
console.log(editor.displayDocument());

// Example 3: Create and save an HTML document
console.log('\n--- Creating HTML Document ---');
editor.createNewDocument(
  DocumentType.HTML,
  '<h1>Welcome</h1><p>This is a sample HTML document.</p>'
);
editor.addMetadata('title', 'Sample HTML Page');
editor.addMetadata('author', 'Web Developer');
console.log(editor.displayDocument());

console.log('\n' + '='.repeat(80));
console.log('EXERCISE 2: CAR CONFIGURATION (Builder Pattern)');
console.log('='.repeat(80));

// Example 1: Build a custom car using CarBuilder
console.log('\n--- Building Custom Car ---');
const builder = new CarBuilder();
const customCar = builder
  .setModel('Custom Sports Car')
  .setEngine(EngineType.V8)
  .setTransmission(TransmissionType.DUAL_CLUTCH)
  .setInteriorFeatures({
    leatherSeats: true,
    gps: true,
    soundSystem: 'Premium Bose',
    heatedSeats: true,
    climateControl: true
  })
  .setExteriorOptions({
    color: 'Metallic Red',
    rims: 'Carbon Fiber 20-inch',
    spoiler: true
  })
  .setSafetyFeatures({
    abs: true,
    airbags: 8,
    rearCamera: true,
    blindSpotMonitoring: true,
    laneAssist: true,
    adaptiveCruiseControl: true
  })
  .build();

console.log(customCar.getDescription());

// Example 2: Use CarDirector to build pre-configured cars
console.log('\n--- Using CarDirector for Pre-configured Cars ---');
const director = new CarDirector(builder);

console.log('\n1. Economy Car:');
const economyCar = director.buildEconomyCar();
console.log(economyCar.getDescription());

console.log('\n2. Luxury Car:');
const luxuryCar = director.buildLuxuryCar();
console.log(luxuryCar.getDescription());

console.log('\n3. Electric Car:');
const electricCar = director.buildElectricCar();
console.log(electricCar.getDescription());

console.log('\n' + '='.repeat(80));
console.log('BONUS: CAR MANAGEMENT SYSTEM (Combined Exercise)');
console.log('='.repeat(80));

// Create a Car Management System
const cms = new CarManagementSystem();

// Add cars to the system
console.log('\n--- Adding Cars to Management System ---');
cms.addCar('sport-001', customCar);
cms.addCar('economy-001', economyCar);
cms.addCar('luxury-001', luxuryCar);
cms.addCar('electric-001', electricCar);
console.log(`Total cars in system: ${cms.getCarCount()}`);

// Generate individual car documents
console.log('\n--- Generating Individual Car Document (HTML) ---');
const carDoc = cms.generateCarDocument('sport-001', DocumentType.HTML);
console.log(carDoc);

// Generate fleet report
console.log('\n--- Generating Fleet Report (PDF) ---');
const fleetDoc = cms.generateFleetDocument(DocumentType.PDF);
console.log(fleetDoc);

console.log('\n' + '='.repeat(80));
console.log('DEMONSTRATION COMPLETE');
console.log('='.repeat(80));
console.log('\nKey Design Patterns Demonstrated:');
console.log('1. Factory Pattern - DocumentFactory creates different document types');
console.log('2. Builder Pattern - CarBuilder builds complex car objects step-by-step');
console.log('3. Director Pattern - CarDirector provides pre-configured car setups');
console.log('4. Integration - CarManagementSystem combines both systems seamlessly');
console.log('\nAll systems working correctly!\n');

// Export all modules
export * from './document-editor';
export * from './car-configuration';
export * from './car-management-system';
