// PasoEjercicioDTO.java
package com.entrenamiento.planverano.training;

public class PasoEjercicioDTO {
    private Long id;
    private int orden;
    private String nombreEjercicio;
    private String tipoMedida;
    private double cantidad;
    private int descansoDespuesSeg;
    private String gifUrl;

    public PasoEjercicioDTO(Long id, int orden, String nombreEjercicio,
                            String tipoMedida, double cantidad,
                            int descansoDespuesSeg, String gifUrl) {
        this.id = id;
        this.orden = orden;
        this.nombreEjercicio = nombreEjercicio;
        this.tipoMedida = tipoMedida;
        this.cantidad = cantidad;
        this.descansoDespuesSeg = descansoDespuesSeg;
        this.gifUrl = gifUrl;
    }
    // Getters...
    public Long getId() { return id; }
    public int getOrden() { return orden; }
    public String getNombreEjercicio() { return nombreEjercicio; }
    public String getTipoMedida() { return tipoMedida; }
    public double getCantidad() { return cantidad; }
    public int getDescansoDespuesSeg() { return descansoDespuesSeg; }
    public String getGifUrl() { return gifUrl; }
}
