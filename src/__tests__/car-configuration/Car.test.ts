import { Car } from '../../car-configuration/Car';
import { EngineType, TransmissionType } from '../../car-configuration/CarOptions';

describe('Car', () => {
  let car: Car;

  beforeEach(() => {
    car = new Car(
      'Test Model',
      EngineType.V6,
      TransmissionType.AUTOMATIC,
      {
        leatherSeats: true,
        gps: true,
        soundSystem: 'Premium',
        heatedSeats: true,
        sunroof: true,
        climateControl: true
      },
      {
        color: 'Blue',
        rims: 'Alloy',
        sunroof: true,
        spoiler: false
      },
      {
        abs: true,
        airbags: 6,
        rearCamera: true,
        blindSpotMonitoring: true,
        laneAssist: true,
        adaptiveCruiseControl: true
      }
    );
  });

  test('should create a car with all properties', () => {
    expect(car.getModel()).toBe('Test Model');
    expect(car.getEngine()).toBe(EngineType.V6);
    expect(car.getTransmission()).toBe(TransmissionType.AUTOMATIC);
  });

  test('should return interior features', () => {
    const interior = car.getInterior();
    expect(interior.leatherSeats).toBe(true);
    expect(interior.gps).toBe(true);
    expect(interior.soundSystem).toBe('Premium');
    expect(interior.heatedSeats).toBe(true);
  });

  test('should return exterior options', () => {
    const exterior = car.getExterior();
    expect(exterior.color).toBe('Blue');
    expect(exterior.rims).toBe('Alloy');
    expect(exterior.sunroof).toBe(true);
  });

  test('should return safety features', () => {
    const safety = car.getSafety();
    expect(safety.abs).toBe(true);
    expect(safety.airbags).toBe(6);
    expect(safety.rearCamera).toBe(true);
  });

  test('should return a copy of features to prevent mutation', () => {
    const interior1 = car.getInterior();
    interior1.gps = false;
    const interior2 = car.getInterior();
    expect(interior2.gps).toBe(true);
  });

  test('should generate a detailed description', () => {
    const description = car.getDescription();
    expect(description).toContain('Test Model');
    expect(description).toContain('V6');
    expect(description).toContain('Automatic');
    expect(description).toContain('Blue');
    expect(description).toContain('Ready for ordering');
  });

  test('should validate a complete car as valid', () => {
    expect(car.isValid()).toBe(true);
  });

  test('should validate car without model as invalid', () => {
    const invalidCar = new Car(
      '',
      EngineType.V6,
      TransmissionType.AUTOMATIC,
      { leatherSeats: false, gps: false, soundSystem: null, heatedSeats: false, sunroof: false, climateControl: false },
      { color: 'Red', rims: null, sunroof: false, spoiler: false },
      { abs: true, airbags: 2, rearCamera: false, blindSpotMonitoring: false, laneAssist: false, adaptiveCruiseControl: false }
    );
    expect(invalidCar.isValid()).toBe(false);
  });

  test('should return validation errors for missing required fields', () => {
    const invalidCar = new Car(
      '',
      '' as EngineType,
      '' as TransmissionType,
      { leatherSeats: false, gps: false, soundSystem: null, heatedSeats: false, sunroof: false, climateControl: false },
      { color: '', rims: null, sunroof: false, spoiler: false },
      { abs: true, airbags: 2, rearCamera: false, blindSpotMonitoring: false, laneAssist: false, adaptiveCruiseControl: false }
    );
    const errors = invalidCar.getValidationErrors();
    expect(errors).toContain('Model is required');
    expect(errors).toContain('Engine type is required');
    expect(errors).toContain('Transmission type is required');
    expect(errors).toContain('Exterior color is required');
  });

  test('should return empty array for valid car', () => {
    const errors = car.getValidationErrors();
    expect(errors).toEqual([]);
  });

  test('should handle missing color in validation', () => {
    const carNoColor = new Car(
      'Model',
      EngineType.V4,
      TransmissionType.MANUAL,
      { leatherSeats: false, gps: false, soundSystem: null, heatedSeats: false, sunroof: false, climateControl: false },
      { color: '', rims: null, sunroof: false, spoiler: false },
      { abs: true, airbags: 2, rearCamera: false, blindSpotMonitoring: false, laneAssist: false, adaptiveCruiseControl: false }
    );
    expect(carNoColor.isValid()).toBe(false);
    expect(carNoColor.getValidationErrors()).toContain('Exterior color is required');
  });
});
