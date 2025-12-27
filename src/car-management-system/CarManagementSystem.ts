import { Car } from '../car-configuration/Car';
import { CarBuilder } from '../car-configuration/CarBuilder';
import { DocumentEditor } from '../document-editor/DocumentEditor';
import { DocumentType } from '../document-editor/DocumentFactory';

/**
 * Car Management System - Combines Document Editor and Car Configuration
 * This system allows users to configure cars and generate documents describing them
 */
export class CarManagementSystem {
  private cars: Map<string, Car> = new Map();
  private documentEditor: DocumentEditor;

  constructor() {
    this.documentEditor = new DocumentEditor();
  }

  /**
   * Add a car to the management system
   */
  addCar(id: string, car: Car): void {
    if (!car.isValid()) {
      throw new Error(`Cannot add invalid car: ${car.getValidationErrors().join(', ')}`);
    }
    this.cars.set(id, car);
  }

  /**
   * Get a car from the management system
   */
  getCar(id: string): Car | undefined {
    return this.cars.get(id);
  }

  /**
   * Remove a car from the management system
   */
  removeCar(id: string): boolean {
    return this.cars.delete(id);
  }

  /**
   * Get all car IDs
   */
  getAllCarIds(): string[] {
    return Array.from(this.cars.keys());
  }

  /**
   * Get total number of cars
   */
  getCarCount(): number {
    return this.cars.size;
  }

  /**
   * Generate a document for a specific car
   */
  generateCarDocument(carId: string, documentType: DocumentType): string {
    const car = this.cars.get(carId);
    if (!car) {
      throw new Error(`Car with ID ${carId} not found`);
    }

    const content = this.generateCarContent(car, documentType);
    this.documentEditor.createNewDocument(documentType, content);
    this.documentEditor.addMetadata('carId', carId);
    this.documentEditor.addMetadata('model', car.getModel());
    this.documentEditor.addMetadata('generatedDate', new Date().toISOString());

    return this.documentEditor.displayDocument();
  }

  /**
   * Save a car document to a file
   */
  saveCarDocument(carId: string, documentType: DocumentType, filePath: string): string {
    const car = this.cars.get(carId);
    if (!car) {
      throw new Error(`Car with ID ${carId} not found`);
    }

    const content = this.generateCarContent(car, documentType);
    this.documentEditor.createNewDocument(documentType, content);
    this.documentEditor.addMetadata('carId', carId);
    this.documentEditor.addMetadata('model', car.getModel());
    this.documentEditor.addMetadata('generatedDate', new Date().toISOString());

    return this.documentEditor.saveDocument(filePath);
  }

  /**
   * Generate a fleet document containing all cars
   */
  generateFleetDocument(documentType: DocumentType): string {
    if (this.cars.size === 0) {
      throw new Error('No cars in the system to generate fleet document');
    }

    const content = this.generateFleetContent(documentType);
    this.documentEditor.createNewDocument(documentType, content);
    this.documentEditor.addMetadata('documentType', 'Fleet Report');
    this.documentEditor.addMetadata('carCount', this.cars.size.toString());
    this.documentEditor.addMetadata('generatedDate', new Date().toISOString());

    return this.documentEditor.displayDocument();
  }

  /**
   * Save a fleet document to a file
   */
  saveFleetDocument(documentType: DocumentType, filePath: string): string {
    if (this.cars.size === 0) {
      throw new Error('No cars in the system to generate fleet document');
    }

    const content = this.generateFleetContent(documentType);
    this.documentEditor.createNewDocument(documentType, content);
    this.documentEditor.addMetadata('documentType', 'Fleet Report');
    this.documentEditor.addMetadata('carCount', this.cars.size.toString());
    this.documentEditor.addMetadata('generatedDate', new Date().toISOString());

    return this.documentEditor.saveDocument(filePath);
  }

  /**
   * Generate content for a single car based on document type
   */
  private generateCarContent(car: Car, documentType: DocumentType): string {
    switch (documentType) {
      case DocumentType.HTML:
        return this.generateCarHtmlContent(car);
      case DocumentType.PDF:
      case DocumentType.WORD:
        return car.getDescription();
      default:
        return car.getDescription();
    }
  }

  /**
   * Generate HTML content for a car
   */
  private generateCarHtmlContent(car: Car): string {
    const interior = car.getInterior();
    const exterior = car.getExterior();
    const safety = car.getSafety();

    return `
<div class="car-details">
  <h1>${car.getModel()}</h1>

  <section class="engine-transmission">
    <h2>Engine & Transmission</h2>
    <ul>
      <li><strong>Engine:</strong> ${car.getEngine()}</li>
      <li><strong>Transmission:</strong> ${car.getTransmission()}</li>
    </ul>
  </section>

  <section class="interior">
    <h2>Interior Features</h2>
    <ul>
      <li>Leather Seats: ${interior.leatherSeats ? 'Yes' : 'No'}</li>
      <li>GPS Navigation: ${interior.gps ? 'Yes' : 'No'}</li>
      <li>Sound System: ${interior.soundSystem || 'Standard'}</li>
      <li>Heated Seats: ${interior.heatedSeats ? 'Yes' : 'No'}</li>
      <li>Sunroof: ${interior.sunroof ? 'Yes' : 'No'}</li>
      <li>Climate Control: ${interior.climateControl ? 'Yes' : 'No'}</li>
    </ul>
  </section>

  <section class="exterior">
    <h2>Exterior Options</h2>
    <ul>
      <li>Color: ${exterior.color}</li>
      <li>Rims: ${exterior.rims || 'Standard'}</li>
      <li>Sunroof: ${exterior.sunroof ? 'Yes' : 'No'}</li>
      <li>Spoiler: ${exterior.spoiler ? 'Yes' : 'No'}</li>
    </ul>
  </section>

  <section class="safety">
    <h2>Safety Features</h2>
    <ul>
      <li>ABS: ${safety.abs ? 'Yes' : 'No'}</li>
      <li>Airbags: ${safety.airbags}</li>
      <li>Rear Camera: ${safety.rearCamera ? 'Yes' : 'No'}</li>
      <li>Blind Spot Monitoring: ${safety.blindSpotMonitoring ? 'Yes' : 'No'}</li>
      <li>Lane Assist: ${safety.laneAssist ? 'Yes' : 'No'}</li>
      <li>Adaptive Cruise Control: ${safety.adaptiveCruiseControl ? 'Yes' : 'No'}</li>
    </ul>
  </section>

  <p class="status"><strong>Status:</strong> Ready for ordering</p>
</div>
    `.trim();
  }

  /**
   * Generate fleet content based on document type
   */
  private generateFleetContent(documentType: DocumentType): string {
    switch (documentType) {
      case DocumentType.HTML:
        return this.generateFleetHtmlContent();
      case DocumentType.PDF:
      case DocumentType.WORD:
        return this.generateFleetTextContent();
      default:
        return this.generateFleetTextContent();
    }
  }

  /**
   * Generate HTML content for fleet
   */
  private generateFleetHtmlContent(): string {
    const carsList = Array.from(this.cars.entries())
      .map(([id, car]) => {
        return `
      <div class="car-item">
        <h3>${id}: ${car.getModel()}</h3>
        <p>Engine: ${car.getEngine()} | Transmission: ${car.getTransmission()}</p>
        <p>Color: ${car.getExterior().color}</p>
      </div>`;
      })
      .join('\n');

    return `
<div class="fleet-report">
  <h1>Fleet Report</h1>
  <p><strong>Total Cars:</strong> ${this.cars.size}</p>
  <p><strong>Generated:</strong> ${new Date().toLocaleString()}</p>

  <section class="cars-list">
    <h2>Cars in Fleet</h2>
    ${carsList}
  </section>
</div>
    `.trim();
  }

  /**
   * Generate text content for fleet
   */
  private generateFleetTextContent(): string {
    const carsList = Array.from(this.cars.entries())
      .map(([id, car]) => {
        return `\n${id}: ${car.getModel()}
  Engine: ${car.getEngine()}
  Transmission: ${car.getTransmission()}
  Color: ${car.getExterior().color}`;
      })
      .join('\n');

    return `
FLEET REPORT
============

Total Cars: ${this.cars.size}
Generated: ${new Date().toLocaleString()}

CARS IN FLEET:
${carsList}
    `.trim();
  }
}
