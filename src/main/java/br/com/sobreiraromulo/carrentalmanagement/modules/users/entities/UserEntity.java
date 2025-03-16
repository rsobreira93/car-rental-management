package br.com.sobreiraromulo.carrentalmanagement.modules.users.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Name is required.")
    @Size(max = 100, message = "Name cannot exceed 100 characters.")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Document is required.")
    @Size(max = 11, message = "Document cannot exceed 11 characters.")
    @Column(nullable = false, length = 11)
    private String document;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email should be valid.")
    @Size(max = 50, message = "Email cannot exceed 50 characters.")
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @NotBlank(message = "Password is required.")
    @Column(nullable = false)
    private String password;

    @Size(max = 15, message = "Phone number cannot exceed 15 characters.")
    @Column(length = 15)
    private String phone;

    @NotBlank(message = "Address is required.")
    @Size(max = 100, message = "Address cannot exceed 100 characters.")
    @Column(name = "address", nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public enum Role {
        ROLE_ADMIN,
        ROLE_USER,
        ROLE_EMPLOYEE
    }

    public UserEntity() {
    }

    public UserEntity(
            String name,
            String document,
            String email,
            String password,
            String phone,
            String address,
            String role) {
        this.name = name;
        this.document = document;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.role = Role.valueOf(role);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
