package br.com.sobreiraromulo.carrentalmanagement.modules.cars.dto;

import java.util.UUID;

import br.com.sobreiraromulo.carrentalmanagement.modules.cars.CarEntity;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Informações do carro")
public record CarResponseDTO(
        @Schema(description = "ID do carro", example = "123e4567-e89b-12d3-a456-426614174000") UUID id,
        @Schema(description = "Marca do carro", example = "Mercedes") String brand,
        @Schema(description = "Modelo do carro", example = "Classe A") String model,
        @Schema(description = "Placa do carro", example = "AAAABBBB") String licensePlate,
        @Schema(description = "Ano do carro", example = "2009") String year,
        @Schema(description = "Status do carro", example = "AVAILABLE") String status) {

    public static CarResponseDTO fromEntity(CarEntity carEntity) {
        return new CarResponseDTO(carEntity.getId(), carEntity.getBrand(), carEntity.getModel(),
                carEntity.getLicensePlate(), carEntity.getYear(), carEntity.getStatus().name());
    }

}