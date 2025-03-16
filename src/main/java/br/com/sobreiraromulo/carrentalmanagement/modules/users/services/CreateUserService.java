package br.com.sobreiraromulo.carrentalmanagement.modules.users.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sobreiraromulo.carrentalmanagement.exceptions.UserAlreadyExists;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.dto.CreateUserRequestDTO;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.dto.CreateUserResponseDTO;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.entities.UserEntity;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.repositories.UserRepository;

@Service
public class CreateUserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public CreateUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CreateUserResponseDTO execute(CreateUserRequestDTO createUserRequestDTO) {

        this.userRepository.findByEmail(createUserRequestDTO.email())
                .ifPresent((user -> {
                    throw new UserAlreadyExists("Email already exists.");
                }));

        this.userRepository.findByDocument(createUserRequestDTO.document())
                .ifPresent((user -> {
                    throw new UserAlreadyExists("Document already exists.");
                }));

        var passwordHashed = this.passwordEncoder.encode(createUserRequestDTO.password());

        UserEntity userEntity = new UserEntity(
                createUserRequestDTO.name(),
                createUserRequestDTO.document(),
                createUserRequestDTO.email(),
                passwordHashed,
                createUserRequestDTO.phone(),
                createUserRequestDTO.address(),
                createUserRequestDTO.role());

        UserEntity userSaved = this.userRepository.save(userEntity);

        return new CreateUserResponseDTO(
                userSaved.getName(),
                userSaved.getDocument(),
                userSaved.getEmail(),
                userSaved.getPhone(),
                userSaved.getCreatedAt());

    }
}
