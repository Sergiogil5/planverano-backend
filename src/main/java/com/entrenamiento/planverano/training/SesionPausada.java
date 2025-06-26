package com.entrenamiento.planverano.training;

import com.entrenamiento.planverano.user.User;
import jakarta.persistence.*;

@Entity
public class SesionPausada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "sesion_diaria_id", nullable = false)
    private SesionDiaria sesionDiaria;

    private int ultimoEjercicioIndex;

    private String fase; // "EXERCISE" o "REST"

    private int tiempoRestanteSeg;
    private int duracionInicialSeg;

    // --- Getters y Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUsuario() { return usuario; }
    public void setUsuario(User usuario) { this.usuario = usuario; }
    public SesionDiaria getSesionDiaria() { return sesionDiaria; }
    public void setSesionDiaria(SesionDiaria sesionDiaria) { this.sesionDiaria = sesionDiaria; }
    public int getUltimoEjercicioIndex() { return ultimoEjercicioIndex; }
    public void setUltimoEjercicioIndex(int ultimoEjercicioIndex) { this.ultimoEjercicioIndex = ultimoEjercicioIndex; }
    public String getFase() { return fase; }
    public void setFase(String fase) { this.fase = fase; }
    public int getTiempoRestanteSeg() { return tiempoRestanteSeg; }
    public void setTiempoRestanteSeg(int tiempoRestanteSeg) { this.tiempoRestanteSeg = tiempoRestanteSeg; }
    public int getDuracionInicialSeg() { return duracionInicialSeg; }
    public void setDuracionInicialSeg(int duracionInicialSeg) { this.duracionInicialSeg = duracionInicialSeg; }
}