package com.entrenamiento.planverano.training;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class BloqueEjercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int orden; // Para saber en qué orden van los bloques (1, 2, 3...)
    private int repeticionesBloque; // Cuántas veces se repite el circuito (ej: 3)
    private int descansoEntreBloquesSeg; // Descanso después de cada vuelta del circuito

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bloque_ejercicio_id")
    private List<PasoEjercicio> pasos;

    // Añade estos dos constructores
    public BloqueEjercicio() {
        this.pasos = new ArrayList<>();
    }

    public BloqueEjercicio(int orden, int repeticionesBloque, int descansoEntreBloquesSeg, List<PasoEjercicio> pasos) {
        this.orden = orden;
        this.repeticionesBloque = repeticionesBloque;
        this.descansoEntreBloquesSeg = descansoEntreBloquesSeg;
        this.pasos = pasos;
    }
    // Getters y Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getRepeticionesBloque() {
        return repeticionesBloque;
    }

    public void setRepeticionesBloque(int repeticionesBloque) {
        this.repeticionesBloque = repeticionesBloque;
    }

    public int getDescansoEntreBloquesSeg() {
        return descansoEntreBloquesSeg;
    }

    public void setDescansoEntreBloquesSeg(int descansoEntreBloquesSeg) {
        this.descansoEntreBloquesSeg = descansoEntreBloquesSeg;
    }

    public List<PasoEjercicio> getPasos() {
        return pasos;
    }

    public void setPasos(List<PasoEjercicio> pasos) {
        this.pasos = pasos;
    }
}