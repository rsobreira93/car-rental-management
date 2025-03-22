package br.com.sobreiraromulo.carrentalmanagement.modules.users.controllers.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.sobreiraromulo.carrentalmanagement.modules.users.entities.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Informações do usuário")
public record UserResponseDTO(
                @Schema(description = "ID do usuário", example = "123e4567-e89b-12d3-a456-426614174000") UUID id,

                @Schema(description = "Nome do usuário", example = "João Silva") String name,

                @Schema(description = "Email do usuário", example = "joao@email.com") String email,

                @Schema(description = "Endereço do usuário", example = "Rua dos bobos, número 0") String address,

                @Schema(description = "Papel do usuário", example = "ROLE_USER") String role,

                @Schema(description = "Data de criação do usuário", example = "2025-12-12") LocalDateTime createdAt) {

        public static UserResponseDTO fromEntity(UserEntity userEntity) {
                return new UserResponseDTO(userEntity.getId(), userEntity.getName(), userEntity.getEmail(),
                                userEntity.getAddress(), userEntity.getRole().name(), userEntity.getCreatedAt());
        }
}