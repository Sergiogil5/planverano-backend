package com.entrenamiento.planverano.user;

// Lista de importaciones. IntelliJ debería añadirlas automáticamente, pero si no, aquí están.
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/registro")
public class RegistroController {

    private final RegistroService registroService;

    public RegistroController(RegistroService registroService) {
        this.registroService = registroService;
    }

    // ==================================================================
    // ==   ESTE ES EL MÉTODO QUE YA TENÍAS PARA VALIDAR EL CÓDIGO     ==
    // ==================================================================
    @GetMapping("/validar")
    public ResponseEntity<?> validarCodigo(@RequestParam String codigo) {
        Optional<RegistroService.DatosCodigo> datosCodigoOpt = registroService.validarCodigo(codigo);

        if (datosCodigoOpt.isPresent()) {
            return ResponseEntity.ok(datosCodigoOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // ==================================================================
    // ==   AQUÍ ES DONDE TIENES QUE AÑADIR EL NUEVO MÉTODO POST      ==
    // ==================================================================
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody RegistroRequest request) {
        try {
            User newUser = registroService.registrarUsuario(request);
            // Si todo va bien, devolvemos el usuario creado y un estado 201 Created.
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            // Si el servicio lanzó un error (código inválido, email duplicado, etc.),
            // lo capturamos y devolvemos un mensaje de error con estado 400 Bad Request.
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}