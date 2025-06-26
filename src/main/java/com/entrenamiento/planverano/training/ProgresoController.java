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

    // Endpoint para que el usuario autenticado pida TODO su progreso guardado
    @GetMapping("/mis-progresos")
    public ResponseEntity<List<ProgresoJugador>> getMisProgresos(Authentication authentication) {
        User usuarioActual = (User) authentication.getPrincipal();
        return ResponseEntity.ok(progresoService.getProgresoPorJugador(usuarioActual.getId()));
    }

    // Endpoint para que el usuario autenticado pida su sesión pausada, si existe
    @GetMapping("/mi-pausa")
    public ResponseEntity<SesionPausada> getMiPausa(Authentication authentication) {
        User usuarioActual = (User) authentication.getPrincipal();
        return progresoService.getPausa(usuarioActual)
                .map(ResponseEntity::ok) // Si existe, la devuelve con un 200 OK
                .orElse(ResponseEntity.noContent().build()); // Si no existe, devuelve un 204 No Content
    }
}