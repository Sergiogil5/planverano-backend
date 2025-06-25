package com.entrenamiento.planverano.user;

// Ya no necesitamos la importación de @JsonProperty, la eliminamos.

public class RegistroRequest {
    private String codigoRegistro;
    private String email;
    private String password;
    private String nombreCompleto;

    // --- ¡CAMBIO 1! ---
    private Categoria team; // El campo ahora se llama 'team'

    // --- Getters ---
    public String getCodigoRegistro() { return codigoRegistro; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getNombreCompleto() { return nombreCompleto; }

    // --- ¡CAMBIO 2! ---
    public Categoria getTeam() { return team; } // Nuevo Getter para 'team'

    // --- Setters ---
    public void setCodigoRegistro(String codigoRegistro) { this.codigoRegistro = codigoRegistro; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    // --- ¡CAMBIO 3! ---
    public void setTeam(Categoria team) { this.team = team; } // Nuevo Setter para 'team'
}