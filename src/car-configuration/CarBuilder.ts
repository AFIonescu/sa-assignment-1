import { Car } from './Car';
import {
  EngineType,
  TransmissionType,
  InteriorFeatures,
  ExteriorOptions,
  SafetyFeatures
} from './CarOptions';

/**
 * Builder interface for car configuration
 */
export interface ICarBuilder {
  setModel(model: string): this;
  setEngine(engine: EngineType): this;
  setTransmission(transmission: TransmissionType): this;
  setInteriorFeatures(features: Partial<InteriorFeatures>): this;
  setExteriorOptions(options: Partial<ExteriorOptions>): this;
  setSafetyFeatures(features: Partial<SafetyFeatures>): this;
  build(): Car;
  reset(): void;
}

/**
 * Concrete Builder implementation for car configuration
 * Implements the Builder Pattern for step-by-step car construction
 */
export class CarBuilder implements ICarBuilder {
  private model: string = '';
  private engine: EngineType = EngineType.V4;
  private transmission: TransmissionType = TransmissionType.MANUAL;
  private interior: InteriorFeatures = {
    leatherSeats: false,
    gps: false,
    soundSystem: null,
    heatedSeats: false,
    sunroof: false,
    climateControl: false
  };
  private exterior: ExteriorOptions = {
    color: '',
    rims: null,
    sunroof: false,
    spoiler: false
  };
  private safety: SafetyFeatures = {
    abs: true,
    airbags: 2,
    rearCamera: false,
    blindSpotMonitoring: false,
    laneAssist: false,
    adaptiveCruiseControl: false
  };

  /**
   * Set the car model
   */
  setModel(model: string): this {
    this.model = model;
    return this;
  }

  /**
   * Set the engine type
   */
  setEngine(engine: EngineType): this {
    this.engine = engine;
    return this;
  }

  /**
   * Set the transmission type
   */
  setTransmission(transmission: TransmissionType): this {
    this.transmission = transmission;
    return this;
  }

  /**
   * Set interior features (partial updates supported)
   */
  setInteriorFeatures(features: Partial<InteriorFeatures>): this {
    this.interior = { ...this.interior, ...features };
    return this;
  }

  /**
   * Set exterior options (partial updates supported)
   */
  setExteriorOptions(options: Partial<ExteriorOptions>): this {
    this.exterior = { ...this.exterior, ...options };
    return this;
  }

  /**
   * Set safety features (partial updates supported)
   */
  setSafetyFeatures(features: Partial<SafetyFeatures>): this {
    this.safety = { ...this.safety, ...features };
    return this;
  }

  /**
   * Build and return the configured car
   */
  build(): Car {
    const car = new Car(
      this.model,
      this.engine,
      this.transmission,
      this.interior,
      this.exterior,
      this.safety
    );

    if (!car.isValid()) {
      throw new Error(
        `Invalid car configuration: ${car.getValidationErrors().join(', ')}`
      );
    }

    return car;
  }

  /**
   * Reset the builder to default state
   */
  reset(): this {
    this.model = '';
    this.engine = EngineType.V4;
    this.transmission = TransmissionType.MANUAL;
    this.interior = {
      leatherSeats: false,
      gps: false,
      soundSystem: null,
      heatedSeats: false,
      sunroof: false,
      climateControl: false
    };
    this.exterior = {
      color: '',
      rims: null,
      sunroof: false,
      spoiler: false
    };
    this.safety = {
      abs: true,
      airbags: 2,
      rearCamera: false,
      blindSpotMonitoring: false,
      laneAssist: false,
      adaptiveCruiseControl: false
    };
    return this;
  }
}
