package br.com.sobreiraromulo.carrentalmanagement.modules.cars;

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
import jakarta.validation.constraints.NotBlank;

@Entity(name = "cars")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Brand is required.")
    @Column(nullable = false)
    private String brand;

    @NotBlank(message = "Model is required.")
    @Column(nullable = false)
    private String model;

    @NotBlank(message = "License plate is required.")
    @Column(name = "license_plate", nullable = false, unique = true)
    private String licensePlate;

    @NotBlank(message = "year is required.")
    @Column(nullable = false)
    private String year;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public enum Status {
        AVAILABLE,
        RENTED,
        MAINTENANCE
    }

    public CarEntity() {
    }

    public CarEntity(@NotBlank(message = "Brand is required.") String brand,
            @NotBlank(message = "Model is required.") String model,
            @NotBlank(message = "License plate is required.") String licensePlate,
            @NotBlank(message = "year is required.") String year, Status status) {
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.year = year;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
