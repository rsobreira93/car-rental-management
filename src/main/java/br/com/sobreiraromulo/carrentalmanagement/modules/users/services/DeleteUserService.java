package br.com.sobreiraromulo.carrentalmanagement.modules.users.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.sobreiraromulo.carrentalmanagement.exceptions.UserNotFound;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.entities.UserEntity;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.repositories.UserRepository;

@Service
public class DeleteUserService {

    private UserRepository userRepository;

    public DeleteUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(UUID userId) {
        UserEntity user = this.userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw new UserNotFound("User not found");
                });

        this.userRepository.delete(user);
    }

}
