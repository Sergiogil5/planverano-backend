package com.entrenamiento.planverano.user;

// Este record es nuestro "formulario de registro".
// Define exactamente los datos que esperamos recibir del frontend.
public record RegistroRequest(
        String codigoRegistro,
        String email,
        String password,
        Categoria categoria, // Puede ser null si se registra un entrenador
        String nombreCompleto
) {
}