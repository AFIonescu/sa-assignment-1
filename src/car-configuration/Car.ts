import {
  EngineType,
  TransmissionType,
  InteriorFeatures,
  ExteriorOptions,
  SafetyFeatures
} from './CarOptions';

/**
 * Car class representing a fully configured car
 */
export class Car {
  private model: string;
  private engine: EngineType;
  private transmission: TransmissionType;
  private interior: InteriorFeatures;
  private exterior: ExteriorOptions;
  private safety: SafetyFeatures;

  constructor(
    model: string,
    engine: EngineType,
    transmission: TransmissionType,
    interior: InteriorFeatures,
    exterior: ExteriorOptions,
    safety: SafetyFeatures
  ) {
    this.model = model;
    this.engine = engine;
    this.transmission = transmission;
    this.interior = interior;
    this.exterior = exterior;
    this.safety = safety;
  }

  getModel(): string {
    return this.model;
  }

  getEngine(): EngineType {
    return this.engine;
  }

  getTransmission(): TransmissionType {
    return this.transmission;
  }

  getInterior(): InteriorFeatures {
    return { ...this.interior };
  }

  getExterior(): ExteriorOptions {
    return { ...this.exterior };
  }

  getSafety(): SafetyFeatures {
    return { ...this.safety };
  }

  /**
   * Get a detailed description of the car
   */
  getDescription(): string {
    return `
=== ${this.model} ===

ENGINE & TRANSMISSION:
- Engine: ${this.engine}
- Transmission: ${this.transmission}

INTERIOR FEATURES:
- Leather Seats: ${this.interior.leatherSeats ? 'Yes' : 'No'}
- GPS Navigation: ${this.interior.gps ? 'Yes' : 'No'}
- Sound System: ${this.interior.soundSystem || 'Standard'}
- Heated Seats: ${this.interior.heatedSeats ? 'Yes' : 'No'}
- Sunroof: ${this.interior.sunroof ? 'Yes' : 'No'}
- Climate Control: ${this.interior.climateControl ? 'Yes' : 'No'}

EXTERIOR OPTIONS:
- Color: ${this.exterior.color}
- Rims: ${this.exterior.rims || 'Standard'}
- Sunroof: ${this.exterior.sunroof ? 'Yes' : 'No'}
- Spoiler: ${this.exterior.spoiler ? 'Yes' : 'No'}

SAFETY FEATURES:
- ABS: ${this.safety.abs ? 'Yes' : 'No'}
- Airbags: ${this.safety.airbags}
- Rear Camera: ${this.safety.rearCamera ? 'Yes' : 'No'}
- Blind Spot Monitoring: ${this.safety.blindSpotMonitoring ? 'Yes' : 'No'}
- Lane Assist: ${this.safety.laneAssist ? 'Yes' : 'No'}
- Adaptive Cruise Control: ${this.safety.adaptiveCruiseControl ? 'Yes' : 'No'}

Status: Ready for ordering
    `.trim();
  }

  /**
   * Validate that the car configuration is ready for ordering
   */
  isValid(): boolean {
    return !!(
      this.model &&
      this.engine &&
      this.transmission &&
      this.exterior.color
    );
  }

  /**
   * Get validation errors if any
   */
  getValidationErrors(): string[] {
    const errors: string[] = [];

    if (!this.model) {
      errors.push('Model is required');
    }
    if (!this.engine) {
      errors.push('Engine type is required');
    }
    if (!this.transmission) {
      errors.push('Transmission type is required');
    }
    if (!this.exterior.color) {
      errors.push('Exterior color is required');
    }

    return errors;
  }
}
