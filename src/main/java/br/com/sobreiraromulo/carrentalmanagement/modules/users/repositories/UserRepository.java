package br.com.sobreiraromulo.carrentalmanagement.modules.users.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.sobreiraromulo.carrentalmanagement.modules.users.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByDocument(String document);

    // @Query("SELECT u FROM UserEntity u " +
    // "WHERE LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%')) " +
    // "OR LOWER(u.document) LIKE LOWER(CONCAT('%', :search, '%'))")
    @Query("SELECT u FROM users u WHERE (:query IS NULL OR u.email LIKE %:query% OR u.document LIKE %:query%)")
    Page<UserEntity> findByEmailOrDocument(@Param("query") String query, PageRequest pageRequest);
}
