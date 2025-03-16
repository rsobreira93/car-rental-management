package br.com.sobreiraromulo.carrentalmanagement.modules.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sobreiraromulo.carrentalmanagement.modules.users.dto.CreateUserRequestDTO;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.dto.CreateUserResponseDTO;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.services.CreateUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@Tag(name = "Usuário", description = "Informações do usuário")

public class UserController {

    @Autowired
    private CreateUserService createUserService;

    @PostMapping
    @Operation(summary = "Cadastro de usuário", description = "Essa função é responsável por cadastrar um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = CreateUserResponseDTO.class))
            }),
            @ApiResponse(responseCode = "409", description = "Usuário já existe")
    })
    public ResponseEntity<CreateUserResponseDTO> createUser(@Valid @RequestBody CreateUserRequestDTO userEntity) {
        var result = this.createUserService.execute(userEntity);

        return ResponseEntity.ok(result);

    }

}
