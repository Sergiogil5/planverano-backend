package com.entrenamiento.planverano.user;

import com.entrenamiento.planverano.config.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);


    public AuthService(UserRepository userRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse login(LoginRequest request) {
        // 1. Le pedimos a Spring Security que autentique al usuario.
        // Si el email o la contraseña son incorrectos, esto lanzará una excepción.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        // 2. Si la autenticación fue exitosa, buscamos al usuario para generar el token.
        // Buscamos al usuario completo
        var user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado después de la autenticación"));

        // Generamos el token
        String token = jwtService.generateToken(user);

        // 4. Devolvemos la respuesta con el token.
        return new LoginResponse(token, user);
    }
}