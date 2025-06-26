package com.entrenamiento.planverano.training;

public record PausaRequest(
        Long sesionDiariaId,
        int ultimoEjercicioIndex,
        String fase,
        int tiempoRestanteSeg,
        int duracionInicialSeg
) {}