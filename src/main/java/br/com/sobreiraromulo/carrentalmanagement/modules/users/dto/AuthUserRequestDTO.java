package br.com.sobreiraromulo.carrentalmanagement.modules.users.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthUserRequestDTO(
                @NotBlank(message = "Email is required") String email,

                @NotBlank(message = "Password is required.") String password) {
}
