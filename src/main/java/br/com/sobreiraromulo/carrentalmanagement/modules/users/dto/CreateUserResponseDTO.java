package br.com.sobreiraromulo.carrentalmanagement.modules.users.dto;

import java.time.LocalDateTime;

public record CreateUserResponseDTO(

                String name,
                String document,
                String email,
                String phone,
                LocalDateTime createdAt

) {
}
