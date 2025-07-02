package com.entrenamiento.planverano.training;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class SesionDiaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numeroSemana;
    private int numeroDia;

    @Enumerated(EnumType.STRING)
    private TipoSesion tipoSesion;

    private String titulo; // ej: "Día 1", "Actividad Libre", "Descanso Activo"
    private String descripcion; // ej: "Ciclismo, escalada, senderismo..."

    // Una sesión tiene una lista de bloques de ejercicios.
    // El "CascadeType.ALL" y "orphanRemoval=true" significa que si borramos una sesión,
    // también se borran todos sus bloques asociados.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "sesion_diaria_id") // Clave foránea en la tabla BloqueEjercicio
    private List<BloqueEjercicio> bloques;

    // Añade estos dos constructores debajo de los campos
    public SesionDiaria() {
        this.bloques = new ArrayList<>();
    }

    public SesionDiaria(int numeroSemana, int numeroDia, TipoSesion tipoSesion, String titulo, String descripcion) {
        this.numeroSemana = numeroSemana;
        this.numeroDia = numeroDia;
        this.tipoSesion = tipoSesion;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.bloques = new ArrayList<>();
    }
    // Getters y Setters (puedes generarlos con Alt+Insert o clic derecho > Generate)


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroSemana() {
        return numeroSemana;
    }

    public void setNumeroSemana(int numeroSemana) {
        this.numeroSemana = numeroSemana;
    }

    public int getNumeroDia() {
        return numeroDia;
    }

    public void setNumeroDia(int numeroDia) {
        this.numeroDia = numeroDia;
    }

    public TipoSesion getTipoSesion() {
        return tipoSesion;
    }

    public void setTipoSesion(TipoSesion tipoSesion) {
        this.tipoSesion = tipoSesion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<BloqueEjercicio> getBloques() {
        return bloques;
    }

    public void setBloques(List<BloqueEjercicio> bloques) {
        this.bloques = bloques;
    }
}