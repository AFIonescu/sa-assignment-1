import { CarManagementSystem } from '../../car-management-system/CarManagementSystem';
import { CarBuilder } from '../../car-configuration/CarBuilder';
import { EngineType, TransmissionType } from '../../car-configuration/CarOptions';
import { DocumentType } from '../../document-editor/DocumentFactory';

describe('CarManagementSystem', () => {
  let cms: CarManagementSystem;
  let builder: CarBuilder;

  beforeEach(() => {
    cms = new CarManagementSystem();
    builder = new CarBuilder();
  });

  describe('Car Management', () => {
    test('should add a valid car to the system', () => {
      const car = builder
        .setModel('Test Car')
        .setEngine(EngineType.V6)
        .setTransmission(TransmissionType.AUTOMATIC)
        .setExteriorOptions({ color: 'Blue' })
        .build();

      cms.addCar('car1', car);
      expect(cms.getCarCount()).toBe(1);
    });

    test('should throw error when adding invalid car', () => {
      const invalidCar = builder.build;
      expect(() => {
        // Create car without required fields
        builder.reset();
        cms.addCar('car1', builder.build());
      }).toThrow();
    });

    test('should get a car by ID', () => {
      const car = builder
        .setModel('Test Car')
        .setEngine(EngineType.V6)
        .setTransmission(TransmissionType.AUTOMATIC)
        .setExteriorOptions({ color: 'Red' })
        .build();

      cms.addCar('car1', car);
      const retrieved = cms.getCar('car1');

      expect(retrieved).toBeDefined();
      expect(retrieved?.getModel()).toBe('Test Car');
    });

    test('should return undefined for non-existent car', () => {
      expect(cms.getCar('nonexistent')).toBeUndefined();
    });

    test('should remove a car from the system', () => {
      const car = builder
        .setModel('Test Car')
        .setEngine(EngineType.V4)
        .setTransmission(TransmissionType.MANUAL)
        .setExteriorOptions({ color: 'White' })
        .build();

      cms.addCar('car1', car);
      expect(cms.getCarCount()).toBe(1);

      const removed = cms.removeCar('car1');
      expect(removed).toBe(true);
      expect(cms.getCarCount()).toBe(0);
    });

    test('should return false when removing non-existent car', () => {
      const removed = cms.removeCar('nonexistent');
      expect(removed).toBe(false);
    });

    test('should get all car IDs', () => {
      const car1 = builder
        .setModel('Car 1')
        .setEngine(EngineType.V6)
        .setTransmission(TransmissionType.AUTOMATIC)
        .setExteriorOptions({ color: 'Red' })
        .build();

      builder.reset();

      const car2 = builder
        .setModel('Car 2')
        .setEngine(EngineType.V4)
        .setTransmission(TransmissionType.MANUAL)
        .setExteriorOptions({ color: 'Blue' })
        .build();

      cms.addCar('car1', car1);
      cms.addCar('car2', car2);

      const ids = cms.getAllCarIds();
      expect(ids).toContain('car1');
      expect(ids).toContain('car2');
      expect(ids.length).toBe(2);
    });

    test('should get correct car count', () => {
      expect(cms.getCarCount()).toBe(0);

      const car = builder
        .setModel('Test')
        .setEngine(EngineType.V6)
        .setTransmission(TransmissionType.AUTOMATIC)
        .setExteriorOptions({ color: 'Black' })
        .build();

      cms.addCar('car1', car);
      expect(cms.getCarCount()).toBe(1);

      cms.addCar('car2', car);
      expect(cms.getCarCount()).toBe(2);
    });
  });

  describe('Document Generation - Single Car', () => {
    beforeEach(() => {
      const car = builder
        .setModel('Test Model')
        .setEngine(EngineType.V8)
        .setTransmission(TransmissionType.AUTOMATIC)
        .setInteriorFeatures({ leatherSeats: true, gps: true })
        .setExteriorOptions({ color: 'Black' })
        .setSafetyFeatures({ airbags: 6, rearCamera: true })
        .build();

      cms.addCar('test-car', car);
    });

    test('should generate PDF document for a car', () => {
      const doc = cms.generateCarDocument('test-car', DocumentType.PDF);
      expect(doc).toContain('[PDF Document]');
      expect(doc).toContain('Test Model');
      expect(doc).toContain('V8');
    });

    test('should generate Word document for a car', () => {
      const doc = cms.generateCarDocument('test-car', DocumentType.WORD);
      expect(doc).toContain('[Word Document]');
      expect(doc).toContain('Test Model');
    });

    test('should generate HTML document for a car', () => {
      const doc = cms.generateCarDocument('test-car', DocumentType.HTML);
      expect(doc).toContain('[HTML Document]');
      expect(doc).toContain('<h1>Test Model</h1>');
      expect(doc).toContain('Engine & Transmission');
    });

    test('should throw error when generating document for non-existent car', () => {
      expect(() => {
        cms.generateCarDocument('nonexistent', DocumentType.PDF);
      }).toThrow('Car with ID nonexistent not found');
    });

    test('should save car document with proper metadata', () => {
      const result = cms.saveCarDocument('test-car', DocumentType.PDF, 'car-spec');
      expect(result).toContain('PDF document saved to car-spec.pdf');
      expect(result).toContain('Test Model');
    });

    test('should throw error when saving document for non-existent car', () => {
      expect(() => {
        cms.saveCarDocument('nonexistent', DocumentType.PDF, 'test');
      }).toThrow('Car with ID nonexistent not found');
    });
  });

  describe('Document Generation - Fleet', () => {
    beforeEach(() => {
      const car1 = builder
        .setModel('Sedan')
        .setEngine(EngineType.V6)
        .setTransmission(TransmissionType.AUTOMATIC)
        .setExteriorOptions({ color: 'Blue' })
        .build();

      builder.reset();

      const car2 = builder
        .setModel('SUV')
        .setEngine(EngineType.V8)
        .setTransmission(TransmissionType.AUTOMATIC)
        .setExteriorOptions({ color: 'Black' })
        .build();

      cms.addCar('car1', car1);
      cms.addCar('car2', car2);
    });

    test('should generate fleet document in PDF format', () => {
      const doc = cms.generateFleetDocument(DocumentType.PDF);
      expect(doc).toContain('[PDF Document]');
      expect(doc).toContain('FLEET REPORT');
      expect(doc).toContain('Total Cars: 2');
      expect(doc).toContain('Sedan');
      expect(doc).toContain('SUV');
    });

    test('should generate fleet document in Word format', () => {
      const doc = cms.generateFleetDocument(DocumentType.WORD);
      expect(doc).toContain('[Word Document]');
      expect(doc).toContain('FLEET REPORT');
      expect(doc).toContain('car1: Sedan');
      expect(doc).toContain('car2: SUV');
    });

    test('should generate fleet document in HTML format', () => {
      const doc = cms.generateFleetDocument(DocumentType.HTML);
      expect(doc).toContain('[HTML Document]');
      expect(doc).toContain('<h1>Fleet Report</h1>');
      expect(doc).toContain('car1: Sedan');
      expect(doc).toContain('car2: SUV');
    });

    test('should save fleet document', () => {
      const result = cms.saveFleetDocument(DocumentType.PDF, 'fleet-report');
      expect(result).toContain('PDF document saved to fleet-report.pdf');
      expect(result).toContain('FLEET REPORT');
    });

    test('should throw error when generating fleet document with no cars', () => {
      const emptyCms = new CarManagementSystem();
      expect(() => {
        emptyCms.generateFleetDocument(DocumentType.PDF);
      }).toThrow('No cars in the system to generate fleet document');
    });

    test('should throw error when saving fleet document with no cars', () => {
      const emptyCms = new CarManagementSystem();
      expect(() => {
        emptyCms.saveFleetDocument(DocumentType.PDF, 'fleet');
      }).toThrow('No cars in the system to generate fleet document');
    });

    test('should include correct car count in fleet document', () => {
      const doc = cms.generateFleetDocument(DocumentType.PDF);
      expect(doc).toContain('Total Cars: 2');
    });

    test('should include all cars in fleet document', () => {
      builder.reset();
      const car3 = builder
        .setModel('Coupe')
        .setEngine(EngineType.V8)
        .setTransmission(TransmissionType.DUAL_CLUTCH)
        .setExteriorOptions({ color: 'Red' })
        .build();

      cms.addCar('car3', car3);

      const doc = cms.generateFleetDocument(DocumentType.HTML);
      expect(doc).toContain('car1: Sedan');
      expect(doc).toContain('car2: SUV');
      expect(doc).toContain('car3: Coupe');
      expect(doc).toContain('<strong>Total Cars:</strong> 3');
    });
  });

  describe('Integration Tests', () => {
    test('should handle complete workflow: add car, generate doc, save doc', () => {
      const car = builder
        .setModel('Luxury Sedan')
        .setEngine(EngineType.V8)
        .setTransmission(TransmissionType.AUTOMATIC)
        .setInteriorFeatures({
          leatherSeats: true,
          gps: true,
          soundSystem: 'Premium',
          climateControl: true
        })
        .setExteriorOptions({ color: 'Silver', rims: 'Chrome' })
        .setSafetyFeatures({
          abs: true,
          airbags: 8,
          rearCamera: true,
          blindSpotMonitoring: true
        })
        .build();

      cms.addCar('luxury1', car);

      const display = cms.generateCarDocument('luxury1', DocumentType.HTML);
      expect(display).toContain('Luxury Sedan');

      const saved = cms.saveCarDocument('luxury1', DocumentType.PDF, 'luxury-spec');
      expect(saved).toContain('PDF document saved');
    });

    test('should handle multiple cars and multiple document types', () => {
      const car1 = builder
        .setModel('Economy')
        .setEngine(EngineType.V4)
        .setTransmission(TransmissionType.MANUAL)
        .setExteriorOptions({ color: 'White' })
        .build();

      builder.reset();

      const car2 = builder
        .setModel('Sport')
        .setEngine(EngineType.V8)
        .setTransmission(TransmissionType.DUAL_CLUTCH)
        .setExteriorOptions({ color: 'Red' })
        .build();

      cms.addCar('eco1', car1);
      cms.addCar('sport1', car2);

      const pdfDoc = cms.generateCarDocument('eco1', DocumentType.PDF);
      const htmlDoc = cms.generateCarDocument('sport1', DocumentType.HTML);
      const fleetDoc = cms.generateFleetDocument(DocumentType.WORD);

      expect(pdfDoc).toContain('Economy');
      expect(htmlDoc).toContain('<h1>Sport</h1>');
      expect(fleetDoc).toContain('eco1: Economy');
      expect(fleetDoc).toContain('sport1: Sport');
    });
  });
});
