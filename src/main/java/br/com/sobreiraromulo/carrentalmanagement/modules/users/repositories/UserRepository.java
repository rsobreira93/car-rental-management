package br.com.sobreiraromulo.carrentalmanagement.modules.users.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sobreiraromulo.carrentalmanagement.modules.users.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByDocument(String document);
}
