package br.com.sobreiraromulo.carrentalmanagement.modules.users.services;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.sobreiraromulo.carrentalmanagement.modules.users.dto.AuthUserRequestDTO;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.dto.AuthUserResponseDTO;
import br.com.sobreiraromulo.carrentalmanagement.modules.users.repositories.UserRepository;
import jakarta.security.auth.message.AuthException;

@Service
public class AuthUserService {

    private String secretKey;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public AuthUserService(@Value("${security.token.secret}") String secretKey, UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.secretKey = secretKey;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthUserResponseDTO execute(AuthUserRequestDTO authUserRequestDTO) throws Exception {
        var user = this.userRepository.findByEmail(authUserRequestDTO.email())
                .orElseThrow(() -> new Exception("Email/password invalid"));

        var passwordMatches = this.passwordEncoder.matches(authUserRequestDTO.password(), user.getPassword());

        if (!passwordMatches) {
            throw new AuthException();
        }

        Algorithm algorithm = Algorithm.HMAC256(this.secretKey);

        var expires_in = Instant.now().plus(Duration.ofMinutes(10));

        var token = JWT.create()
                .withIssuer("cartRentalManagement")
                .withSubject(user.getId().toString())
                .withClaim("roles", Arrays.asList(user.getRole().toString()))
                .withExpiresAt(expires_in)
                .sign(algorithm);

        var authCandidateResponse = new AuthUserResponseDTO(token, expires_in.toEpochMilli());

        return authCandidateResponse;
    }
}
