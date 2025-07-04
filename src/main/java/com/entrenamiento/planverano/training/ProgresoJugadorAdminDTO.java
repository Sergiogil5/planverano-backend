// ProgresoJugadorAdminDTO.java
package com.entrenamiento.planverano.training;

import java.time.LocalDateTime;

public class ProgresoJugadorAdminDTO {
    private Long id;
    private SesionDiariaDTO sesion;
    private LocalDateTime fechaCompletado;
    private String feedbackEmoji;
    private String feedbackLabel;
    private String feedbackTextoOpcional;
    private String tiemposJson;
    private String rutaGpsJson;
    private String actividadLibre;
    private String tiempoLibre;


    public ProgresoJugadorAdminDTO(Long id,
                                   SesionDiariaDTO sesion,
                                   LocalDateTime fechaCompletado,
                                   String feedbackEmoji,
                                   String feedbackLabel,
                                   String feedbackTextoOpcional,
                                   String tiemposJson,
                                   String rutaGpsJson,
                                   String actividadLibre,
                                   String tiempoLibre
        ) {
        this.id = id;
        this.sesion = sesion;
        this.fechaCompletado = fechaCompletado;
        this.feedbackEmoji = feedbackEmoji;
        this.feedbackLabel = feedbackLabel;
        this.feedbackTextoOpcional = feedbackTextoOpcional;
        this.tiemposJson = tiemposJson;
        this.rutaGpsJson = rutaGpsJson;
        this.actividadLibre = actividadLibre;
        this.tiempoLibre = tiempoLibre;
    }
    // Getters...
    public Long getId() { return id; }
    public SesionDiariaDTO getSesion() { return sesion; }
    public LocalDateTime getFechaCompletado() { return fechaCompletado; }
    public String getFeedbackEmoji() { return feedbackEmoji; }
    public String getFeedbackLabel() { return feedbackLabel; }
    public String getFeedbackTextoOpcional() { return feedbackTextoOpcional; }
    public String getTiemposJson() { return tiemposJson; }
    public String getRutaGpsJson() { return rutaGpsJson; }
    public String getActividadLibre() { return actividadLibre; }
    public String getTiempoLibre() { return tiempoLibre; }

}
