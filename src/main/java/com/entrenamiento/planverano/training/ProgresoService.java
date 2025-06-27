package com.entrenamiento.planverano.training;

import com.entrenamiento.planverano.user.User;
import com.entrenamiento.planverano.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional; // Asegúrate de que esta importación está

@Service
@RequiredArgsConstructor
public class ProgresoService {

    // --- ¡AQUÍ ESTÁ LA CLAVE! ---
    // El constructor que crea Lombok necesita estos 3 repositorios.
    private final ProgresoJugadorRepository progresoRepository;
    private final SesionDiariaRepository sesionRepository;
    private final SesionPausadaRepository pausaRepository;
    private final UserRepository userRepository;



    // Método para guardar un nuevo progreso completo
    public ProgresoJugador guardarProgreso(User usuario, ProgresoRequest request) {
        // --- ¡LÍNEA CLAVE DE LA SOLUCIÓN! ---
        // Antes de hacer nada, obtenemos la versión "viva" y "conectada" del usuario
        // desde la base de datos, usando el ID del usuario que viene del token.
        User usuarioConectado = userRepository.findById(usuario.getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado en la BD, ¡esto no debería pasar!"));

        // El resto del código es igual, pero ahora usamos 'usuarioConectado'
        SesionDiaria sesion = sesionRepository.findById(request.sesionId())
                .orElseThrow(() -> new RuntimeException("Sesión no encontrada con ID: " + request.sesionId()));

        ProgresoJugador nuevoProgreso = new ProgresoJugador();
        nuevoProgreso.setUsuario(usuarioConectado); // <-- ¡USAMOS LA VERSIÓN CONECTADA!
        nuevoProgreso.setSesion(sesion);
        nuevoProgreso.setFechaCompletado(LocalDateTime.now());
        nuevoProgreso.setFeedbackEmoji(request.feedbackEmoji());
        nuevoProgreso.setFeedbackLabel(request.feedbackLabel());
        nuevoProgreso.setFeedbackTextoOpcional(request.feedbackTextoOpcional());
        nuevoProgreso.setTiemposJson(request.tiemposJson());
        nuevoProgreso.setRutaGpsJson(request.rutaGpsJson());

        return progresoRepository.save(nuevoProgreso);
    }

    // Método para guardar una sesión pausada
    public void guardarPausa(User usuario, PausaRequest request) {
        pausaRepository.findByUsuarioId(usuario.getId()).ifPresent(pausaRepository::delete);

        SesionDiaria sesion = sesionRepository.findById(request.sesionDiariaId())
                .orElseThrow(() -> new RuntimeException("Sesion Diaria no encontrada"));

        SesionPausada nuevaPausa = new SesionPausada();
        nuevaPausa.setUsuario(usuario);
        nuevaPausa.setSesionDiaria(sesion);
        nuevaPausa.setUltimoEjercicioIndex(request.ultimoEjercicioIndex());
        nuevaPausa.setFase(request.fase());
        nuevaPausa.setTiempoRestanteSeg(request.tiempoRestanteSeg());
        nuevaPausa.setDuracionInicialSeg(request.duracionInicialSeg());

        pausaRepository.save(nuevaPausa);
    }

    // Método para obtener una sesión pausada
    public Optional<SesionPausada> getPausa(User usuario) {
        return pausaRepository.findByUsuarioId(usuario.getId());
    }

    // Método para que un entrenador (o el propio usuario) vea su progreso
    public List<ProgresoJugador> getProgresoPorJugador(Long usuarioId) {
        return progresoRepository.findByUsuarioId(usuarioId);
    }
}