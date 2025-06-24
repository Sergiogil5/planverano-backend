package com.entrenamiento.planverano.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor // Lombok crea el constructor por nosotros
public class UserController {

    private final UserService userService; // Inyectamos el nuevo servicio

    // Este endpoint ya lo teníamos, devuelve el usuario logueado
    @GetMapping("/me")
    public ResponseEntity<User> getAuthenticatedUser(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    // ¡NUEVO ENDPOINT! Devuelve una lista de todos los jugadores
    @GetMapping
    public ResponseEntity<List<User>> getAllPlayers() {
        List<User> players = userService.findAllPlayers();
        return ResponseEntity.ok(players);
    }
}