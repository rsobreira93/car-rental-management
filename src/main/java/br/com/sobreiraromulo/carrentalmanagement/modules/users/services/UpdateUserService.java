package br.com.sobreiraromulo.carrentalmanagement.modules.users.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.sobreiraromulo.carrentalmanagement.exceptions.UserNotFound;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.dto.UpdateUserRequestDTO;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.entities.UserEntity;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.repositories.UserRepository;

@Service
public class UpdateUserService {

    private UserRepository userRepository;

    public UpdateUserService(UserRepository userRepositorys) {
        this.userRepository = userRepository;
    }

    public void execute(UUID userId, UpdateUserRequestDTO updateUser) {
        UserEntity user = this.userRepository.findById(userId)
                .orElseThrow(() -> {
                    throw new UserNotFound("User not found");
                });

        Optional.ofNullable(updateUser.name()).ifPresent(user::setName);
        Optional.ofNullable(updateUser.phone()).ifPresent(user::setPhone);
        Optional.ofNullable(updateUser.email()).ifPresent(user::setEmail);
        Optional.ofNullable(updateUser.address()).ifPresent(user::setAddress);

        this.userRepository.save(user);

    }

}
