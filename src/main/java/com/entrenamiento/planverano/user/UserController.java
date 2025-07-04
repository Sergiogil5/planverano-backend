package com.entrenamiento.planverano.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;


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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build(); // Devuelve un 204 No Content, que significa "Éxito, no hay nada que devolver"
    }

    @DeleteMapping("/api/users/{id}")
    @PreAuthorize("hasRole('ENTRENADOR')")
    public ResponseEntity<Void> eliminarJugador(@PathVariable Long id) {
        userService.eliminarJugadorPorId(id);
        return ResponseEntity.noContent().build();
    }


}