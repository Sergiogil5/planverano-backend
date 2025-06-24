package com.entrenamiento.planverano;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaMundoController {

    @GetMapping("/")
    public String saludar() {
        return "¡Hola Mundo! ¡Bienvenido a tu app de entrenamiento!";
    }
}