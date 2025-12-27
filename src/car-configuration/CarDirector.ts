import { CarBuilder } from './CarBuilder';
import { Car } from './Car';
import { EngineType, TransmissionType } from './CarOptions';

/**
 * Director class for managing common car configurations
 * Provides pre-configured car setups for convenience
 */
export class CarDirector {
  private builder: CarBuilder;

  constructor(builder: CarBuilder) {
    this.builder = builder;
  }

  /**
   * Build a basic economy car
   */
  buildEconomyCar(): Car {
    return this.builder
      .reset()
      .setModel('Economy Sedan')
      .setEngine(EngineType.V4)
      .setTransmission(TransmissionType.MANUAL)
      .setExteriorOptions({ color: 'White' })
      .setSafetyFeatures({ abs: true, airbags: 2 })
      .build();
  }

  /**
   * Build a luxury car with premium features
   */
  buildLuxuryCar(): Car {
    return this.builder
      .reset()
      .setModel('Luxury Sedan')
      .setEngine(EngineType.V8)
      .setTransmission(TransmissionType.AUTOMATIC)
      .setInteriorFeatures({
        leatherSeats: true,
        gps: true,
        soundSystem: 'Premium Bose',
        heatedSeats: true,
        sunroof: true,
        climateControl: true
      })
      .setExteriorOptions({
        color: 'Black',
        rims: 'Chrome 20-inch',
        sunroof: true,
        spoiler: false
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
  }

  /**
   * Build a sports car
   */
  buildSportsCar(): Car {
    return this.builder
      .reset()
      .setModel('Sports Coupe')
      .setEngine(EngineType.V8)
      .setTransmission(TransmissionType.DUAL_CLUTCH)
      .setInteriorFeatures({
        leatherSeats: true,
        gps: true,
        soundSystem: 'Premium Audio',
        heatedSeats: false,
        sunroof: false,
        climateControl: true
      })
      .setExteriorOptions({
        color: 'Red',
        rims: 'Carbon Fiber 19-inch',
        sunroof: false,
        spoiler: true
      })
      .setSafetyFeatures({
        abs: true,
        airbags: 6,
        rearCamera: true,
        blindSpotMonitoring: true,
        laneAssist: false,
        adaptiveCruiseControl: false
      })
      .build();
  }

  /**
   * Build an electric eco-friendly car
   */
  buildElectricCar(): Car {
    return this.builder
      .reset()
      .setModel('Electric Hatchback')
      .setEngine(EngineType.ELECTRIC)
      .setTransmission(TransmissionType.AUTOMATIC)
      .setInteriorFeatures({
        leatherSeats: false,
        gps: true,
        soundSystem: 'Standard',
        heatedSeats: true,
        sunroof: true,
        climateControl: true
      })
      .setExteriorOptions({
        color: 'Blue',
        rims: 'Aerodynamic 18-inch',
        sunroof: true,
        spoiler: false
      })
      .setSafetyFeatures({
        abs: true,
        airbags: 6,
        rearCamera: true,
        blindSpotMonitoring: true,
        laneAssist: true,
        adaptiveCruiseControl: true
      })
      .build();
  }

  /**
   * Set a different builder
   */
  setBuilder(builder: CarBuilder): void {
    this.builder = builder;
  }
}
