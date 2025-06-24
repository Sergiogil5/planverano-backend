package com.entrenamiento.planverano.training;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // Magia de Lombok para crear el constructor
public class TrainingService {

    private final SesionDiariaRepository sesionDiariaRepository;

    // Método para obtener todas las sesiones de una semana específica
    public List<SesionDiaria> getSesionesPorSemana(int numeroSemana) {
        return sesionDiariaRepository.findByNumeroSemanaOrderByNumeroDiaAsc(numeroSemana);
    }

    // Método para obtener una sesión específica por su ID
    public SesionDiaria getSesionPorId(Long id) {
        return sesionDiariaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sesión no encontrada con ID: " + id));
    }
}