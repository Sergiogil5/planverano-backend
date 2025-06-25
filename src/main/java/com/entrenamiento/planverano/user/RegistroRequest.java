package com.entrenamiento.planverano.user;

// ¡Hemos cambiado de 'record' a 'class' para más flexibilidad!
public class RegistroRequest {
    private String codigoRegistro;
    private String email;
    private String password;
    private Categoria categoria;
    private String nombreCompleto; // Ahora es un campo normal, puede ser null

    // Getters
    public String getCodigoRegistro() { return codigoRegistro; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Categoria getCategoria() { return categoria; }
    public String getNombreCompleto() { return nombreCompleto; }

    // Setters (útiles para el framework)
    public void setCodigoRegistro(String codigoRegistro) { this.codigoRegistro = codigoRegistro; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
}