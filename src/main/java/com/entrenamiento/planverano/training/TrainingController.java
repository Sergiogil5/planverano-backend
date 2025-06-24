package com.entrenamiento.planverano.training;

// --- SECCIÓN DE IMPORTACIONES ---
// Asegúrate de que todas estas líneas están al principio de tu archivo.
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
// --- FIN DE LA SECCIÓN DE IMPORTACIONES ---


@RestController
@RequestMapping("/api/training")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingService trainingService;

    // Endpoint para obtener las sesiones de una semana
    // Ejemplo de URL: GET /api/training/semana/1
    @GetMapping("/semana/{numeroSemana}")
    public ResponseEntity<List<SesionDiaria>> getSemana(@PathVariable int numeroSemana) {
        List<SesionDiaria> sesiones = trainingService.getSesionesPorSemana(numeroSemana);
        return ResponseEntity.ok(sesiones);
    }

    // Endpoint para obtener los detalles de una única sesión (útil para el futuro)
    // Ejemplo de URL: GET /api/training/sesion/5
    @GetMapping("/sesion/{id}")
    public ResponseEntity<SesionDiaria> getSesion(@PathVariable Long id) {
        SesionDiaria sesion = trainingService.getSesionPorId(id);
        return ResponseEntity.ok(sesion);
    }
}