package br.com.sobreiraromulo.carrentalmanagement.modules.cars.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateCarRequestDTO(
        @Schema(description = "Status do carro", example = "MAINTENANCE") String status) {
}