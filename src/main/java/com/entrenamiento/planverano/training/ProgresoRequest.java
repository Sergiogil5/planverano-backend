package com.entrenamiento.planverano.training;

public record ProgresoRequest(
        Long sesionId, // El ID de la SesionDiaria que han completado
        String feedbackEmoji,
        String feedbackLabel,
        String feedbackTextoOpcional,
        String tiemposJson, // Los tiempos de cada ejercicio en formato JSON
        String rutaGpsJson // La ruta GPS en formato JSON
) {}