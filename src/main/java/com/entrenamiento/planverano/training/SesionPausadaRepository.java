package com.entrenamiento.planverano.training;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SesionPausadaRepository extends JpaRepository<SesionPausada, Long> {
    Optional<SesionPausada> findByUsuarioId(Long usuarioId);
}