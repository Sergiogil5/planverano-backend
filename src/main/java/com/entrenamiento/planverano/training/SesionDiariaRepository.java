package com.entrenamiento.planverano.training;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface SesionDiariaRepository extends JpaRepository<SesionDiaria, Long> {
    List<SesionDiaria> findByNumeroSemanaOrderByNumeroDiaAsc(int numeroSemana);
}