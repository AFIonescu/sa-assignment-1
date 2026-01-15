package com.sa.carmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequest {
    @NotBlank(message = "Model is required")
    private String model;

    @NotBlank(message = "Engine type is required")
    private String engineType;

    @NotBlank(message = "Transmission is required")
    private String transmission;

    private boolean hasLeatherSeats;
    private boolean hasGPS;
    private boolean hasSoundSystem;
    private String color;
    private String rims;
    private boolean hasSunroof;
    private boolean hasABS;
    private boolean hasAirbags;
    private boolean hasRearCamera;
}
