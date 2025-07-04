package com.entrenamiento.planverano.training;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgresoJugadorRepository extends JpaRepository<ProgresoJugador, Long> {

    // Método para buscar todos los progresos de un usuario específico
    List<ProgresoJugador> findByUsuarioId(Long usuarioId);

    // Método para buscar todos los progresos de una sesión específica (útil para el admin)
    List<ProgresoJugador> findBySesionId(Long sesionId);

    void deleteByUsuarioId(Long usuarioId);
}