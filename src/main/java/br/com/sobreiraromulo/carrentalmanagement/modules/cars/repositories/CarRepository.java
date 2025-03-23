package br.com.sobreiraromulo.carrentalmanagement.modules.cars.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sobreiraromulo.carrentalmanagement.modules.cars.CarEntity;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, UUID> {
    Optional<CarEntity> findByLicensePlate(String licensePlate);
}
