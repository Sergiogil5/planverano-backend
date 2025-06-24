package com.entrenamiento.planverano.training;

import com.entrenamiento.planverano.user.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ProgresoJugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación con el usuario que hizo el entrenamiento
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User usuario;

    // Relación con la sesión que completó
    @ManyToOne
    @JoinColumn(name = "sesion_diaria_id", nullable = false)
    private SesionDiaria sesion;

    private LocalDateTime fechaCompletado;

    // Campo para el emoji, ej: "😩", "🙂", "🤩"
    private String feedbackEmoji;

    // Campo para la etiqueta del emoji, ej: "Muy cansado", "Bien", "¡Genial!"
    private String feedbackLabel;

    // ¡NUEVO CAMPO! Para el feedback opcional de texto
    @Column(length = 500) // 500 caracteres deberían ser suficientes
    private String feedbackTextoOpcional;

    // Para los tiempos, lo más sencillo es guardarlos como un texto JSON.
    // ej: "{'Flexiones': '45s', 'Sentadillas': '50s'}"
    @Column(length = 2000)
    private String tiemposJson;

    // Para la ruta GPS, igual, la guardamos como un texto largo con los puntos.
    // ej: "[{'lat': 40.1, 'lng': -3.2}, {'lat': 40.2, 'lng': -3.3}]"
    @Lob // Large Object, para textos muy largos
    private String rutaGpsJson;

    // Getters y Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public SesionDiaria getSesion() {
        return sesion;
    }

    public void setSesion(SesionDiaria sesion) {
        this.sesion = sesion;
    }

    public LocalDateTime getFechaCompletado() {
        return fechaCompletado;
    }

    public void setFechaCompletado(LocalDateTime fechaCompletado) {
        this.fechaCompletado = fechaCompletado;
    }

    public String getFeedbackEmoji() {
        return feedbackEmoji;
    }

    public void setFeedbackEmoji(String feedbackEmoji) {
        this.feedbackEmoji = feedbackEmoji;
    }

    public String getFeedbackLabel() {
        return feedbackLabel;
    }

    public void setFeedbackLabel(String feedbackLabel) {
        this.feedbackLabel = feedbackLabel;
    }

    public String getFeedbackTextoOpcional() {
        return feedbackTextoOpcional;
    }

    public void setFeedbackTextoOpcional(String feedbackTextoOpcional) {
        this.feedbackTextoOpcional = feedbackTextoOpcional;
    }

    public String getTiemposJson() {
        return tiemposJson;
    }

    public void setTiemposJson(String tiemposJson) {
        this.tiemposJson = tiemposJson;
    }

    public String getRutaGpsJson() {
        return rutaGpsJson;
    }

    public void setRutaGpsJson(String rutaGpsJson) {
        this.rutaGpsJson = rutaGpsJson;
    }
}