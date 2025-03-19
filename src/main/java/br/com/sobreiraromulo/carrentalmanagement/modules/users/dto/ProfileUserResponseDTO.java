package br.com.sobreiraromulo.carrentalmanagement.modules.users.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProfileUserResponseDTO {
    private UUID id;

    @Schema(example = "Maria de Souza")
    private String name;

    @Schema(example = "maria@gmail.com")
    private String email;

    @Schema(example = "ROLE_ADMIN")
    private String role;

    @Schema(example = "Rua dos bobos, número 0")
    private String address;

    public ProfileUserResponseDTO() {
    }

    public ProfileUserResponseDTO(UUID id, String name, String email, String role, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
