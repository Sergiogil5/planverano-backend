package com.entrenamiento.planverano.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<User> getAuthenticatedUser(Authentication authentication) {
        // Spring Security, gracias a nuestro filtro, nos pasa el objeto Authentication.
        // Dentro de él está nuestro objeto User.
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }
}