package br.com.sobreiraromulo.carrentalmanagement.modules.users.dto;

import java.util.UUID;

public class AuthUserResponseDTO {

    private String access_token;
    private Long expires_in;
    private UUID id;
    private String role;

    public AuthUserResponseDTO() {
    }

    public AuthUserResponseDTO(String access_token, Long expires_in, UUID id, String role) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.id = id;
        this.role = role;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
