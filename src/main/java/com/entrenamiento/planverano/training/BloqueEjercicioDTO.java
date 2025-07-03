// BloqueEjercicioDTO.java
package com.entrenamiento.planverano.training;

import java.util.List;

public class BloqueEjercicioDTO {
    private Long id;
    private int orden;
    private int repeticionesBloque;
    private int descansoEntreBloquesSeg;
    private List<PasoEjercicioDTO> pasos;

    public BloqueEjercicioDTO(Long id, int orden, int repeticionesBloque,
                              int descansoEntreBloquesSeg,
                              List<PasoEjercicioDTO> pasos) {
        this.id = id;
        this.orden = orden;
        this.repeticionesBloque = repeticionesBloque;
        this.descansoEntreBloquesSeg = descansoEntreBloquesSeg;
        this.pasos = pasos;
    }
    // Getters...
    public Long getId() { return id; }
    public int getOrden() { return orden; }
    public int getRepeticionesBloque() { return repeticionesBloque; }
    public int getDescansoEntreBloquesSeg() { return descansoEntreBloquesSeg; }
    public List<PasoEjercicioDTO> getPasos() { return pasos; }
}
