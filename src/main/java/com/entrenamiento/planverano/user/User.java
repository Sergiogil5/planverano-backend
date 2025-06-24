package com.entrenamiento.planverano.user;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;


@Entity
@Table(name = "users") // Le decimos que en la base de datos, la tabla se llamará "users"
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único y automático (1, 2, 3...)

    @Column(nullable = false, unique = true)
    private String email; // El correo no puede ser nulo y debe ser único

    @Column(nullable = false)
    private String password; // La contraseña (más adelante la haremos segura)

    @Column(nullable = false)
    private String nombreCompleto; // Nombre y apellidos que sacaremos del código

    @Column(nullable = false, unique = true)
    private String codigoRegistro; // El código único (GORA8392, ENTRENADOR_SERGIO_2024...)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol; // El rol será "JUGADOR" o "ENTRENADOR"

    @Enumerated(EnumType.STRING)
    private Categoria categoria; // La categoría: "INFANTIL", "CADETE", "JUVENIL". Puede ser nulo para el entrenador.

    // Getters y Setters (Métodos para acceder y modificar los datos)
    // IntelliJ puede generarlos por nosotros, pero por ahora, cópialos.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // OJO: getPassword() ahora va abajo
    public void setPassword(String password) { this.password = password; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public String getCodigoRegistro() { return codigoRegistro; }
    public void setCodigoRegistro(String codigoRegistro) { this.codigoRegistro = codigoRegistro; }
    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    // --- Métodos de UserDetails ---
    @Override
    public java.util.Collection<? extends org.springframework.security.core.GrantedAuthority> getAuthorities() {
        return java.util.List.of(new org.springframework.security.core.authority.SimpleGrantedAuthority(rol.name()));
    }

    @Override
    public String getUsername() {
        return email; // Nuestro "username" es el email
    }

    @Override
    public String getPassword() {
        return password; // Necesitamos devolver la contraseña encriptada
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // La cuenta nunca expira
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // La cuenta nunca se bloquea
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Las credenciales nunca expiran
    }

    @Override
    public boolean isEnabled() {
        return true; // La cuenta siempre está habilitada
    }
}