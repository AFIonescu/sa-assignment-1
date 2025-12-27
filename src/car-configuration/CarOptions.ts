/**
 * Engine types available for cars
 */
export enum EngineType {
  V6 = 'V6',
  V8 = 'V8',
  V4 = 'V4',
  ELECTRIC = 'Electric',
  HYBRID = 'Hybrid'
}

/**
 * Transmission types
 */
export enum TransmissionType {
  MANUAL = 'Manual',
  AUTOMATIC = 'Automatic',
  CVT = 'CVT',
  DUAL_CLUTCH = 'Dual-Clutch'
}

/**
 * Interior feature options
 */
export interface InteriorFeatures {
  leatherSeats: boolean;
  gps: boolean;
  soundSystem: string | null;
  heatedSeats: boolean;
  sunroof: boolean;
  climateControl: boolean;
}

/**
 * Exterior options
 */
export interface ExteriorOptions {
  color: string;
  rims: string | null;
  sunroof: boolean;
  spoiler: boolean;
}

/**
 * Safety features
 */
export interface SafetyFeatures {
  abs: boolean;
  airbags: number;
  rearCamera: boolean;
  blindSpotMonitoring: boolean;
  laneAssist: boolean;
  adaptiveCruiseControl: boolean;
}
