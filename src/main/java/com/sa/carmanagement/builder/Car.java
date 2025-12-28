package com.sa.carmanagement.builder;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Car {
    private final String model;
    private final String engineType;
    private final String transmission;
    private final boolean hasLeatherSeats;
    private final boolean hasGPS;
    private final boolean hasSoundSystem;
    private final String color;
    private final boolean hasSunroof;
    private final boolean hasABS;
    private final boolean hasAirbags;
    private final boolean hasRearCamera;
}
