package com.entrenamiento.planverano.training;

import com.entrenamiento.planverano.user.User;
import com.entrenamiento.planverano.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import com.entrenamiento.planverano.training.ProgresoJugadorDTO;
import java.util.stream.Collectors;
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
    @Transactional // <-- ¡AÑADE ESTA ANOTACIÓN IMPORTANTE!
    public ProgresoJugador guardarProgreso(User usuario, ProgresoRequest request) {
        // 1. Buscamos las entidades relacionadas. Esto no cambia.
        User usuarioConectado = userRepository.findById(usuario.getId())
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado en la BD con ID: " + usuario.getId()));

        SesionDiaria sesion = sesionRepository.findById(request.sesionId())
                .orElseThrow(() -> new RuntimeException("Sesión no encontrada con ID: " + request.sesionId()));

        // 2. --- ¡LÓGICA CORREGIDA! ---
        // Creamos un objeto ProgresoJugador COMPLETAMENTE NUEVO.
        // Al no tener ID, JPA está OBLIGADO a hacer un INSERT.
        ProgresoJugador nuevoProgreso = new ProgresoJugador();

        nuevoProgreso.setUsuario(usuarioConectado);
        nuevoProgreso.setSesion(sesion);
        nuevoProgreso.setFechaCompletado(LocalDateTime.now());
        nuevoProgreso.setFeedbackEmoji(request.feedbackEmoji());
        nuevoProgreso.setFeedbackLabel(request.feedbackLabel());
        nuevoProgreso.setFeedbackTextoOpcional(request.feedbackTextoOpcional());
        nuevoProgreso.setTiemposJson(request.tiemposJson());
        nuevoProgreso.setRutaGpsJson(request.rutaGpsJson());
        nuevoProgreso.setActividadLibre(request.actividadLibre());
        nuevoProgreso.setTiempoLibre(request.tiempoLibre());


        // 3. Guardamos la nueva entidad.
        return progresoRepository.save(nuevoProgreso);
    }

    // Método para guardar una sesión pausada
    @Transactional
    public void guardarPausa(User usuario, PausaRequest request) {
        User usuarioConectado = userRepository.findById(usuario.getId())
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado al pausar. ID: " + usuario.getId()));
        pausaRepository.findByUsuarioId(usuarioConectado.getId()).ifPresent(pausaRepository::delete);

        SesionDiaria sesion = sesionRepository.findById(request.sesionDiariaId())
                .orElseThrow(() -> new RuntimeException("Sesion Diaria no encontrada al pausar"));

        SesionPausada nuevaPausa = new SesionPausada();
        nuevaPausa.setUsuario(usuarioConectado);
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
    @Transactional(readOnly = true)
    public List<ProgresoJugadorDTO> getProgresoPorJugadorDTO(Long jugadorId) {
        // Obtenemos entidades dentro de la transacción
        List<ProgresoJugador> entidades = progresoRepository.findByUsuarioId(jugadorId);

        // Mapear cada entidad a un DTO
        return entidades.stream()
                .map(p -> new ProgresoJugadorDTO(
                        p.getId(),
                        p.getSesion().getId(),
                        p.getSesion().getNumeroSemana(),   // ← extrae número de semana
                        p.getSesion().getTitulo(),         // ← extrae título de sesión
                        p.getFechaCompletado(),
                        p.getFeedbackEmoji(),
                        p.getFeedbackLabel(),
                        p.getFeedbackTextoOpcional(),
                        p.getTiemposJson(),
                        p.getRutaGpsJson(),
                        p.getActividadLibre(),     // ← aquí
                        p.getTiempoLibre()         // ← aquí
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProgresoJugadorAdminDTO> getProgresoPorJugadorAdminDTO(Long jugadorId) {
        return progresoRepository.findByUsuarioId(jugadorId).stream()
                .map(p -> {
                    // 1) Mapeo de bloques y sus pasos a DTOs
                    List<BloqueEjercicioDTO> bloquesDto = p.getSesion().getBloques().stream()
                            .map(b -> new BloqueEjercicioDTO(
                                    b.getId(),
                                    b.getOrden(),
                                    b.getRepeticionesBloque(),
                                    b.getDescansoEntreBloquesSeg(),
                                    b.getPasos().stream()
                                            .map(px -> new PasoEjercicioDTO(
                                                    px.getId(),
                                                    px.getOrden(),
                                                    px.getNombreEjercicio(),
                                                    px.getTipoMedida().name(),
                                                    px.getCantidad(),
                                                    px.getDescansoDespuesSeg(),
                                                    px.getGifUrl()
                                            ))
                                            .collect(Collectors.toList())
                            ))
                            .collect(Collectors.toList());

                    // 2) Crear DTO de sesión con sus bloques
                    SesionDiariaDTO sesionDto = new SesionDiariaDTO(
                            p.getSesion().getId(),
                            p.getSesion().getNumeroSemana(),
                            p.getSesion().getTitulo(),
                            bloquesDto
                    );

                    // 3) Retornar el DTO completo de progreso
                    return new ProgresoJugadorAdminDTO(
                            p.getId(),
                            sesionDto,
                            p.getFechaCompletado(),
                            p.getFeedbackEmoji(),
                            p.getFeedbackLabel(),
                            p.getFeedbackTextoOpcional(),
                            p.getTiemposJson(),
                            p.getRutaGpsJson(),
                            p.getActividadLibre(),     // ← aquí
                            p.getTiempoLibre()         // ← aquí
                    );
                })
                .collect(Collectors.toList());
    }


}