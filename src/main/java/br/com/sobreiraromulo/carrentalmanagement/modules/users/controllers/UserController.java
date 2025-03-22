package br.com.sobreiraromulo.carrentalmanagement.modules.users.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sobreiraromulo.carrentalmanagement.modules.users.dto.AuthUserRequestDTO;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.dto.AuthUserResponseDTO;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.dto.CreateUserRequestDTO;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.dto.CreateUserResponseDTO;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.dto.PaginatedResponse;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.dto.PaginationResponse;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.dto.ProfileUserResponseDTO;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.dto.UpdateUserRequestDTO;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.dto.UserResponseDTO;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.services.AuthUserService;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.services.CreateUserService;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.services.ListUsersService;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.services.ProfileUserService;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.services.UpdateUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@Tag(name = "Usuário", description = "Informações do usuário")

public class UserController {

        @Autowired
        private CreateUserService createUserService;

        @Autowired
        private AuthUserService authUserService;

        @Autowired
        private ProfileUserService profileUserService;

        @Autowired
        private ListUsersService listUsersService;

        @Autowired
        UpdateUserService updateUserService;

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

        @PostMapping("/auth")
        @Operation(summary = "Login do usuário", description = "Essa função é responsável por autenticar o usuário")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", content = {
                                        @Content(schema = @Schema(implementation = AuthUserRequestDTO.class))
                        }),
                        @ApiResponse(responseCode = "401", description = "Email/password incorrect")
        })
        public ResponseEntity<AuthUserResponseDTO> auth(@RequestBody AuthUserRequestDTO authUserRequestDTO)
                        throws AuthException {
                var token = this.authUserService.execute(authUserRequestDTO);

                return ResponseEntity.ok(token);
        }

        @GetMapping("/profile")
        @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('EMPLOYEE')")
        @Operation(summary = "Perfil do candidato", description = "Essa função é responsável por buscar as informações do perfil do candidato")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", content = {
                                        @Content(schema = @Schema(implementation = ProfileUserResponseDTO.class))
                        }),
                        @ApiResponse(responseCode = "400", description = "User not found")
        })
        @SecurityRequirement(name = "jwt_auth")
        public ResponseEntity<ProfileUserResponseDTO> getProfile(HttpServletRequest request) {
                var userId = request.getAttribute("user_id");

                var profile = this.profileUserService.execute(UUID.fromString(userId.toString()));

                return ResponseEntity.ok(profile);
        }

        @GetMapping
        @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
        @Operation(summary = "Lista usuários paginados", description = "Lista usuários com paginação, ordenação e metadados.")
        @ApiResponses({ @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso"),
                        @ApiResponse(responseCode = "401", description = "Acesso negado", content = @Content(examples = @ExampleObject(value = "Invalid Token."))),
        })
        @SecurityRequirement(name = "jwt_auth")
        public ResponseEntity<PaginatedResponse<UserResponseDTO>> listUsers(
                        @RequestParam(name = "query", required = false) String query,
                        @RequestParam(name = "page", defaultValue = "0") Integer page,
                        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
                PageRequest pageRequest = PageRequest.of(page, pageSize);

                var pageResponse = listUsersService.execute(query, pageRequest);

                return ResponseEntity.ok(new PaginatedResponse<>(
                                pageResponse.getContent(),
                                PaginationResponse.fromPage(pageResponse)));
        }

        @PutMapping("/{id}")
        public ResponseEntity<?> updateUser(@PathVariable UUID id,
                        @RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
                updateUserService.execute(id, updateUserRequestDTO);

                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

}
