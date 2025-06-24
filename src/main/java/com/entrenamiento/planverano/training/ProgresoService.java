package com.entrenamiento.planverano.training;

import com.entrenamiento.planverano.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgresoService {

    private final ProgresoJugadorRepository progresoRepository;
    private final SesionDiariaRepository sesionRepository;

    // Método para guardar un nuevo progreso
    public ProgresoJugador guardarProgreso(User usuario, ProgresoRequest request) {
        // 1. Buscamos la sesión de entrenamiento que el usuario dice haber completado
        SesionDiaria sesion = sesionRepository.findById(request.sesionId())
                .orElseThrow(() -> new RuntimeException("Sesión no encontrada con ID: " + request.sesionId()));

        // 2. Creamos la nueva ficha de progreso
        ProgresoJugador nuevoProgreso = new ProgresoJugador();
        nuevoProgreso.setUsuario(usuario); // El usuario que está autenticado
        nuevoProgreso.setSesion(sesion);
        nuevoProgreso.setFechaCompletado(LocalDateTime.now()); // La fecha y hora actuales

        // 3. Asignamos los datos del feedback
        nuevoProgreso.setFeedbackEmoji(request.feedbackEmoji());
        nuevoProgreso.setFeedbackLabel(request.feedbackLabel());
        nuevoProgreso.setFeedbackTextoOpcional(request.feedbackTextoOpcional());

        // 4. Asignamos los datos de rendimiento (los JSON)
        nuevoProgreso.setTiemposJson(request.tiemposJson());
        nuevoProgreso.setRutaGpsJson(request.rutaGpsJson());

        // 5. Guardamos en la base de datos y devolvemos el objeto guardado
        return progresoRepository.save(nuevoProgreso);
    }

    // Método para que un entrenador vea el progreso de un jugador
    public List<ProgresoJugador> getProgresoPorJugador(Long usuarioId) {
        return progresoRepository.findByUsuarioId(usuarioId);
    }
}