package br.com.sobreiraromulo.carrentalmanagement.modules.users.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.sobreiraromulo.carrentalmanagement.modules.users.controllers.dto.UserResponseDTO;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.repositories.UserRepository;

@Service
public class ListUsersService {
    private final UserRepository userRepository;

    public ListUsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<UserResponseDTO> execute(PageRequest pageRequest) {
        var users = userRepository.findAll(pageRequest);

        return users.map(UserResponseDTO::fromEntity);
    }
}
