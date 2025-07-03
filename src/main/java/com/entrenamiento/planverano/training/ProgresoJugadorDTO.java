package com.entrenamiento.planverano.training;

import java.time.LocalDateTime;

// DTO para exponer solo los datos que necesitamos
public class ProgresoJugadorDTO {

    private Long id;
    private Long sesionDiariaId;
    private LocalDateTime fechaCompletado;
    private String feedbackEmoji;
    private String feedbackLabel;
    private String feedbackTextoOpcional;
    private String tiemposJson;
    private String rutaGpsJson;

    // Constructor con todos los campos
    public ProgresoJugadorDTO(
            Long id,
            Long sesionDiariaId,
            LocalDateTime fechaCompletado,
            String feedbackEmoji,
            String feedbackLabel,
            String feedbackTextoOpcional,
            String tiemposJson,
            String rutaGpsJson
    ) {
        this.id = id;
        this.sesionDiariaId = sesionDiariaId;
        this.fechaCompletado = fechaCompletado;
        this.feedbackEmoji = feedbackEmoji;
        this.feedbackLabel = feedbackLabel;
        this.feedbackTextoOpcional = feedbackTextoOpcional;
        this.tiemposJson = tiemposJson;
        this.rutaGpsJson = rutaGpsJson;
    }

    // Getters (sin setters; el DTO es inmutable desde el controlador)
    public Long getId() {
        return id;
    }

    public Long getSesionDiariaId() {
        return sesionDiariaId;
    }

    public LocalDateTime getFechaCompletado() {
        return fechaCompletado;
    }

    public String getFeedbackEmoji() {
        return feedbackEmoji;
    }

    public String getFeedbackLabel() {
        return feedbackLabel;
    }

    public String getFeedbackTextoOpcional() {
        return feedbackTextoOpcional;
    }

    public String getTiemposJson() {
        return tiemposJson;
    }

    public String getRutaGpsJson() {
        return rutaGpsJson;
    }
}
