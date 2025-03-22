package br.com.sobreiraromulo.carrentalmanagement.modules.users.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateUserRequestDTO(
        @Size(max = 100, message = "Name cannot exceed 100 characters.") String name,
        @Size(min = 11, max = 14, message = "Phone number must be between 11 and 14 characters. Example: with 11, 119XXXX-YYYY. with 14: +55119XXXX-YYYY") String phone,
        @Size(max = 100, message = "Address is required.") String address,
        @Size(min = 8, message = "password must be at least 8 characters") @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).+$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character.") String password

) {
}