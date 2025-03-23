package br.com.sobreiraromulo.carrentalmanagement.modules.cars.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCarRequestDTO(
        @NotBlank(message = "Brand is required.") String brand,

        @NotBlank(message = "Model is required.") String model,

        @NotBlank(message = "License plate is required.") String licensePlate,

        @NotBlank(message = "Year is required.") @Size(max = 4, message = "Year must be 4 characters") String year) {

}
