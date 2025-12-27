import { CarBuilder } from '../../car-configuration/CarBuilder';
import { EngineType, TransmissionType } from '../../car-configuration/CarOptions';

describe('CarBuilder', () => {
  let builder: CarBuilder;

  beforeEach(() => {
    builder = new CarBuilder();
  });

  test('should build a valid car with all required fields', () => {
    const car = builder
      .setModel('Sedan')
      .setEngine(EngineType.V6)
      .setTransmission(TransmissionType.AUTOMATIC)
      .setExteriorOptions({ color: 'Black' })
      .build();

    expect(car.getModel()).toBe('Sedan');
    expect(car.getEngine()).toBe(EngineType.V6);
    expect(car.isValid()).toBe(true);
  });

  test('should throw error when building without required fields', () => {
    expect(() => {
      builder.build();
    }).toThrow('Invalid car configuration');
  });

  test('should set model correctly', () => {
    const car = builder
      .setModel('Sports Car')
      .setEngine(EngineType.V8)
      .setTransmission(TransmissionType.MANUAL)
      .setExteriorOptions({ color: 'Red' })
      .build();

    expect(car.getModel()).toBe('Sports Car');
  });

  test('should set engine type correctly', () => {
    const car = builder
      .setModel('Electric')
      .setEngine(EngineType.ELECTRIC)
      .setTransmission(TransmissionType.AUTOMATIC)
      .setExteriorOptions({ color: 'White' })
      .build();

    expect(car.getEngine()).toBe(EngineType.ELECTRIC);
  });

  test('should set transmission type correctly', () => {
    const car = builder
      .setModel('Manual Car')
      .setEngine(EngineType.V4)
      .setTransmission(TransmissionType.MANUAL)
      .setExteriorOptions({ color: 'Blue' })
      .build();

    expect(car.getTransmission()).toBe(TransmissionType.MANUAL);
  });

  test('should set interior features partially', () => {
    const car = builder
      .setModel('Luxury')
      .setEngine(EngineType.V8)
      .setTransmission(TransmissionType.AUTOMATIC)
      .setInteriorFeatures({ leatherSeats: true, gps: true })
      .setExteriorOptions({ color: 'Black' })
      .build();

    const interior = car.getInterior();
    expect(interior.leatherSeats).toBe(true);
    expect(interior.gps).toBe(true);
    expect(interior.heatedSeats).toBe(false);
  });

  test('should merge interior features on multiple calls', () => {
    const car = builder
      .setModel('Luxury')
      .setEngine(EngineType.V6)
      .setTransmission(TransmissionType.AUTOMATIC)
      .setInteriorFeatures({ leatherSeats: true })
      .setInteriorFeatures({ gps: true, soundSystem: 'Premium' })
      .setExteriorOptions({ color: 'Silver' })
      .build();

    const interior = car.getInterior();
    expect(interior.leatherSeats).toBe(true);
    expect(interior.gps).toBe(true);
    expect(interior.soundSystem).toBe('Premium');
  });

  test('should set exterior options partially', () => {
    const car = builder
      .setModel('Sports')
      .setEngine(EngineType.V8)
      .setTransmission(TransmissionType.DUAL_CLUTCH)
      .setExteriorOptions({ color: 'Red', spoiler: true })
      .build();

    const exterior = car.getExterior();
    expect(exterior.color).toBe('Red');
    expect(exterior.spoiler).toBe(true);
    expect(exterior.rims).toBeNull();
  });

  test('should merge exterior options on multiple calls', () => {
    const car = builder
      .setModel('Custom')
      .setEngine(EngineType.V6)
      .setTransmission(TransmissionType.AUTOMATIC)
      .setExteriorOptions({ color: 'Blue' })
      .setExteriorOptions({ rims: 'Chrome', spoiler: true })
      .build();

    const exterior = car.getExterior();
    expect(exterior.color).toBe('Blue');
    expect(exterior.rims).toBe('Chrome');
    expect(exterior.spoiler).toBe(true);
  });

  test('should set safety features partially', () => {
    const car = builder
      .setModel('Safe Car')
      .setEngine(EngineType.V4)
      .setTransmission(TransmissionType.AUTOMATIC)
      .setSafetyFeatures({ airbags: 8, rearCamera: true })
      .setExteriorOptions({ color: 'White' })
      .build();

    const safety = car.getSafety();
    expect(safety.airbags).toBe(8);
    expect(safety.rearCamera).toBe(true);
    expect(safety.abs).toBe(true);
  });

  test('should merge safety features on multiple calls', () => {
    const car = builder
      .setModel('Safe')
      .setEngine(EngineType.V6)
      .setTransmission(TransmissionType.AUTOMATIC)
      .setSafetyFeatures({ airbags: 6 })
      .setSafetyFeatures({ rearCamera: true, blindSpotMonitoring: true })
      .setExteriorOptions({ color: 'Gray' })
      .build();

    const safety = car.getSafety();
    expect(safety.airbags).toBe(6);
    expect(safety.rearCamera).toBe(true);
    expect(safety.blindSpotMonitoring).toBe(true);
  });

  test('should support method chaining', () => {
    const car = builder
      .setModel('Chained')
      .setEngine(EngineType.V6)
      .setTransmission(TransmissionType.AUTOMATIC)
      .setInteriorFeatures({ gps: true })
      .setExteriorOptions({ color: 'Green' })
      .setSafetyFeatures({ airbags: 4 })
      .build();

    expect(car.getModel()).toBe('Chained');
    expect(car.isValid()).toBe(true);
  });

  test('should reset builder to default state', () => {
    builder
      .setModel('Test')
      .setEngine(EngineType.V8)
      .setExteriorOptions({ color: 'Red' });

    builder.reset();

    expect(() => builder.build()).toThrow('Invalid car configuration');
  });

  test('should allow building multiple cars after reset', () => {
    const car1 = builder
      .setModel('First Car')
      .setEngine(EngineType.V6)
      .setTransmission(TransmissionType.AUTOMATIC)
      .setExteriorOptions({ color: 'Blue' })
      .build();

    builder.reset();

    const car2 = builder
      .setModel('Second Car')
      .setEngine(EngineType.V4)
      .setTransmission(TransmissionType.MANUAL)
      .setExteriorOptions({ color: 'Red' })
      .build();

    expect(car1.getModel()).toBe('First Car');
    expect(car2.getModel()).toBe('Second Car');
  });

  test('should have default values after reset', () => {
    builder
      .setModel('Test')
      .setEngine(EngineType.V8)
      .setTransmission(TransmissionType.DUAL_CLUTCH)
      .setInteriorFeatures({ leatherSeats: true, gps: true })
      .setExteriorOptions({ color: 'Black', rims: 'Chrome' })
      .setSafetyFeatures({ airbags: 8, rearCamera: true });

    builder.reset();

    const car = builder
      .setModel('After Reset')
      .setExteriorOptions({ color: 'White' })
      .build();

    const interior = car.getInterior();
    const safety = car.getSafety();

    expect(car.getEngine()).toBe(EngineType.V4);
    expect(car.getTransmission()).toBe(TransmissionType.MANUAL);
    expect(interior.leatherSeats).toBe(false);
    expect(interior.gps).toBe(false);
    expect(safety.abs).toBe(true);
    expect(safety.airbags).toBe(2);
  });
});
