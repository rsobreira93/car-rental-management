package br.com.sobreiraromulo.carrentalmanagement.modules.users.dto;

import br.com.sobreiraromulo.carrentalmanagement.modules.users.entities.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateUserRequestDTO(
                @NotBlank(message = "Name is required.") @Size(max = 100, message = "Name cannot exceed 100 characters.") String name,

                @NotBlank(message = "Document is required.") @Size(max = 11, message = "Document cannot exceed 11 characters.") String document,

                @NotBlank(message = "Email is required.") @Email(message = "Email should be valid.") @Size(max = 50, message = "Email cannot exceed 50 characters.") String email,

                @NotBlank(message = "Password is required.") @Size(min = 8, message = "password must be at least 8 characters") @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).+$", message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character.") String password,

                @Size(min = 11, max = 14, message = "Phone number must be between 11 and 14 characters. Example: with 11, 119XXXX-YYYY. with 14: +55119XXXX-YYYY") String phone,

                @NotBlank(message = "Address is required.") String address,

                @NotBlank(message = "Role is required.") String role) {

        public UserEntity toUser() {
                return new UserEntity(name, document, email, password, phone, address, role);
        }
}
