package br.com.sobreiraromulo.carrentalmanagement.modules.users.services;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sobreiraromulo.carrentalmanagement.exceptions.SamePasswordException;
import br.com.sobreiraromulo.carrentalmanagement.exceptions.UserNotFound;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.dto.UpdateUserRequestDTO;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.entities.UserEntity;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.repositories.UserRepository;

@Service
public class UpdateUserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UpdateUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void execute(UUID userId, UpdateUserRequestDTO updateUser) {
        UserEntity user = this.userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw new UserNotFound("User not found");
                });

        var passwordHashed = this.passwordEncoder.encode(updateUser.password());

        if (passwordHashed.equals(user.getPassword())) {
            throw new SamePasswordException("Password cannot be the same as the last password");
        }
    }

}
