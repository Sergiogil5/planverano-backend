package com.entrenamiento.planverano.training;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/training")
@RequiredArgsConstructor // <-- Esta anotación ya crea el constructor por nosotros, ¡es magia!
public class TrainingController {

    // Definimos los servicios que necesitamos como 'final'
    private final TrainingService trainingService;
    private final PlanDataLoader planDataLoader;

    // NO necesitamos escribir el constructor a mano gracias a @RequiredArgsConstructor

    // --- ENDPOINT DE SEMANA CORREGIDO Y ÚNICO ---
    @GetMapping("/semana/{numeroSemana}")
    public ResponseEntity<TrainingWeekDTO> getSemana(@PathVariable int numeroSemana) {
        // 1. Obtenemos la lista de sesiones para esa semana
        List<SesionDiaria> sesiones = trainingService.getSesionesPorSemana(numeroSemana);

        // 2. Obtenemos el título de la semana desde el DataLoader
        String tituloSemana = planDataLoader.getTituloSemana(numeroSemana);

        // 3. Creamos el objeto de respuesta (DTO) con toda la información
        TrainingWeekDTO responseDto = new TrainingWeekDTO(numeroSemana, tituloSemana, sesiones);

        // 4. Devolvemos el DTO completo
        return ResponseEntity.ok(responseDto);
    }

    // Endpoint para obtener los detalles de una única sesión (útil para el futuro)
    // Ejemplo de URL: GET /api/training/sesion/5
        @GetMapping("/sesion/{id}")
    public ResponseEntity<SesionDiaria> getSesion(@PathVariable Long id) {
        SesionDiaria sesion = trainingService.getSesionPorId(id);
        return ResponseEntity.ok(sesion);
    }
}



