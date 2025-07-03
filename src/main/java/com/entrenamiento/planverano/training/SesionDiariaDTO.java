// SesionDiariaDTO.java
package com.entrenamiento.planverano.training;

import java.util.List;

public class SesionDiariaDTO {
    private Long id;
    private int numeroSemana;
    private String titulo;
    private List<BloqueEjercicioDTO> bloques;

    public SesionDiariaDTO(Long id, int numeroSemana,
                           String titulo,
                           List<BloqueEjercicioDTO> bloques) {
        this.id = id;
        this.numeroSemana = numeroSemana;
        this.titulo = titulo;
        this.bloques = bloques;
    }
    // Getters...
    public Long getId() { return id; }
    public int getNumeroSemana() { return numeroSemana; }
    public String getTitulo() { return titulo; }
    public List<BloqueEjercicioDTO> getBloques() { return bloques; }
}
