package com.entrenamiento.planverano.training;

import jakarta.persistence.*;

@Entity
public class PasoEjercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int orden; // Orden del paso dentro del bloque
    private String nombreEjercicio; // ej: "Flexiones", "Sprint 20m"

    @Enumerated(EnumType.STRING)
    private TipoMedida tipoMedida; // REPETICIONES, TIEMPO_MINUTOS, etc.

    private double cantidad; // ej: 15 (reps), 2.5 (minutos)
    private int descansoDespuesSeg; // ej: 30
    private String gifUrl;

    // Añade estos dos constructores
    public PasoEjercicio() {} // Deja el constructor vacío

    public PasoEjercicio(int orden, String nombreEjercicio, TipoMedida tipoMedida, double cantidad, int descansoDespuesSeg, String gifUrl) {
        this.orden = orden;
        this.nombreEjercicio = nombreEjercicio;
        this.tipoMedida = tipoMedida;
        this.cantidad = cantidad;
        this.descansoDespuesSeg = descansoDespuesSeg;
        this.gifUrl = gifUrl;
    }

    // Getters y Setters


    public String getGifUrl() {
        return gifUrl;
    }

    public void setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
    }

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

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public TipoMedida getTipoMedida() {
        return tipoMedida;
    }

    public void setTipoMedida(TipoMedida tipoMedida) {
        this.tipoMedida = tipoMedida;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public int getDescansoDespuesSeg() {
        return descansoDespuesSeg;
    }

    public void setDescansoDespuesSeg(int descansoDespuesSeg) {
        this.descansoDespuesSeg = descansoDespuesSeg;
    }
}