package br.com.sobreiraromulo.carrentalmanagement.modules.users.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.sobreiraromulo.carrentalmanagement.exceptions.UserNotFound;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.dto.ProfileUserResponseDTO;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.repositories.UserRepository;

@Service
public class ProfileUserService {

    private UserRepository userRepository;

    public ProfileUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ProfileUserResponseDTO execute(UUID userId) {
        var user = this.userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw new UserNotFound("User not found");
                });

        var userResponseDTO = new ProfileUserResponseDTO(user.getId(), user.getName(),
                user.getEmail(), user.getRole().toString(), user.getAddress());

        return userResponseDTO;
    }
}
