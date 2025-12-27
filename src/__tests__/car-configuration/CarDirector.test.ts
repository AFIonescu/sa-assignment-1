import { CarDirector } from '../../car-configuration/CarDirector';
import { CarBuilder } from '../../car-configuration/CarBuilder';
import { EngineType, TransmissionType } from '../../car-configuration/CarOptions';

describe('CarDirector', () => {
  let builder: CarBuilder;
  let director: CarDirector;

  beforeEach(() => {
    builder = new CarBuilder();
    director = new CarDirector(builder);
  });

  describe('buildEconomyCar', () => {
    test('should build an economy car with basic features', () => {
      const car = director.buildEconomyCar();

      expect(car.getModel()).toBe('Economy Sedan');
      expect(car.getEngine()).toBe(EngineType.V4);
      expect(car.getTransmission()).toBe(TransmissionType.MANUAL);
      expect(car.getExterior().color).toBe('White');
      expect(car.isValid()).toBe(true);
    });

    test('should have basic safety features', () => {
      const car = director.buildEconomyCar();
      const safety = car.getSafety();

      expect(safety.abs).toBe(true);
      expect(safety.airbags).toBe(2);
    });

    test('should have minimal interior features', () => {
      const car = director.buildEconomyCar();
      const interior = car.getInterior();

      expect(interior.leatherSeats).toBe(false);
      expect(interior.gps).toBe(false);
    });
  });

  describe('buildLuxuryCar', () => {
    test('should build a luxury car with premium features', () => {
      const car = director.buildLuxuryCar();

      expect(car.getModel()).toBe('Luxury Sedan');
      expect(car.getEngine()).toBe(EngineType.V8);
      expect(car.getTransmission()).toBe(TransmissionType.AUTOMATIC);
      expect(car.isValid()).toBe(true);
    });

    test('should have premium interior features', () => {
      const car = director.buildLuxuryCar();
      const interior = car.getInterior();

      expect(interior.leatherSeats).toBe(true);
      expect(interior.gps).toBe(true);
      expect(interior.soundSystem).toBe('Premium Bose');
      expect(interior.heatedSeats).toBe(true);
      expect(interior.sunroof).toBe(true);
      expect(interior.climateControl).toBe(true);
    });

    test('should have luxury exterior options', () => {
      const car = director.buildLuxuryCar();
      const exterior = car.getExterior();

      expect(exterior.color).toBe('Black');
      expect(exterior.rims).toBe('Chrome 20-inch');
      expect(exterior.sunroof).toBe(true);
    });

    test('should have advanced safety features', () => {
      const car = director.buildLuxuryCar();
      const safety = car.getSafety();

      expect(safety.abs).toBe(true);
      expect(safety.airbags).toBe(8);
      expect(safety.rearCamera).toBe(true);
      expect(safety.blindSpotMonitoring).toBe(true);
      expect(safety.laneAssist).toBe(true);
      expect(safety.adaptiveCruiseControl).toBe(true);
    });
  });

  describe('buildSportsCar', () => {
    test('should build a sports car with performance features', () => {
      const car = director.buildSportsCar();

      expect(car.getModel()).toBe('Sports Coupe');
      expect(car.getEngine()).toBe(EngineType.V8);
      expect(car.getTransmission()).toBe(TransmissionType.DUAL_CLUTCH);
      expect(car.isValid()).toBe(true);
    });

    test('should have sporty exterior', () => {
      const car = director.buildSportsCar();
      const exterior = car.getExterior();

      expect(exterior.color).toBe('Red');
      expect(exterior.rims).toBe('Carbon Fiber 19-inch');
      expect(exterior.spoiler).toBe(true);
      expect(exterior.sunroof).toBe(false);
    });

    test('should have performance-oriented interior', () => {
      const car = director.buildSportsCar();
      const interior = car.getInterior();

      expect(interior.leatherSeats).toBe(true);
      expect(interior.gps).toBe(true);
      expect(interior.soundSystem).toBe('Premium Audio');
      expect(interior.heatedSeats).toBe(false);
    });

    test('should have good safety features', () => {
      const car = director.buildSportsCar();
      const safety = car.getSafety();

      expect(safety.abs).toBe(true);
      expect(safety.airbags).toBe(6);
      expect(safety.rearCamera).toBe(true);
      expect(safety.blindSpotMonitoring).toBe(true);
    });
  });

  describe('buildElectricCar', () => {
    test('should build an electric car with eco-friendly features', () => {
      const car = director.buildElectricCar();

      expect(car.getModel()).toBe('Electric Hatchback');
      expect(car.getEngine()).toBe(EngineType.ELECTRIC);
      expect(car.getTransmission()).toBe(TransmissionType.AUTOMATIC);
      expect(car.isValid()).toBe(true);
    });

    test('should have modern interior features', () => {
      const car = director.buildElectricCar();
      const interior = car.getInterior();

      expect(interior.leatherSeats).toBe(false);
      expect(interior.gps).toBe(true);
      expect(interior.heatedSeats).toBe(true);
      expect(interior.climateControl).toBe(true);
    });

    test('should have eco-friendly exterior', () => {
      const car = director.buildElectricCar();
      const exterior = car.getExterior();

      expect(exterior.color).toBe('Blue');
      expect(exterior.rims).toBe('Aerodynamic 18-inch');
      expect(exterior.sunroof).toBe(true);
    });

    test('should have modern safety features', () => {
      const car = director.buildElectricCar();
      const safety = car.getSafety();

      expect(safety.abs).toBe(true);
      expect(safety.airbags).toBe(6);
      expect(safety.rearCamera).toBe(true);
      expect(safety.blindSpotMonitoring).toBe(true);
      expect(safety.laneAssist).toBe(true);
      expect(safety.adaptiveCruiseControl).toBe(true);
    });
  });

  describe('setBuilder', () => {
    test('should allow changing the builder', () => {
      const newBuilder = new CarBuilder();
      director.setBuilder(newBuilder);

      const car = director.buildEconomyCar();
      expect(car).toBeDefined();
      expect(car.isValid()).toBe(true);
    });

    test('should use the new builder for subsequent builds', () => {
      const newBuilder = new CarBuilder();
      director.setBuilder(newBuilder);

      const car1 = director.buildSportsCar();
      const car2 = director.buildLuxuryCar();

      expect(car1.getModel()).toBe('Sports Coupe');
      expect(car2.getModel()).toBe('Luxury Sedan');
    });
  });

  test('should reset builder between builds', () => {
    const car1 = director.buildSportsCar();
    const car2 = director.buildEconomyCar();

    expect(car1.getModel()).toBe('Sports Coupe');
    expect(car2.getModel()).toBe('Economy Sedan');
    expect(car1.getEngine()).not.toBe(car2.getEngine());
  });
});
