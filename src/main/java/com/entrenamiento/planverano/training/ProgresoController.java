package com.entrenamiento.planverano.training;

import com.entrenamiento.planverano.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progreso")
@RequiredArgsConstructor
public class ProgresoController {

    private final ProgresoService progresoService;

    // Endpoint para que un jugador guarde su progreso
    @PostMapping
    public ResponseEntity<ProgresoJugador> guardarProgreso(
            @RequestBody ProgresoRequest request,
            Authentication authentication) {

        // Obtenemos el usuario autenticado gracias al token JWT
        User usuarioActual = (User) authentication.getPrincipal();

        ProgresoJugador progresoGuardado = progresoService.guardarProgreso(usuarioActual, request);
        return new ResponseEntity<>(progresoGuardado, HttpStatus.CREATED);
    }

    // Endpoint para que un ENTRENADOR vea el progreso de un jugador específico
    // NOTA: Más adelante aseguraremos que solo los entrenadores puedan llamar a este endpoint.
    @GetMapping("/jugador/{jugadorId}")
    public ResponseEntity<List<ProgresoJugador>> getProgresoDeJugador(@PathVariable Long jugadorId) {
        List<ProgresoJugador> progresos = progresoService.getProgresoPorJugador(jugadorId);
        return ResponseEntity.ok(progresos);
    }
}