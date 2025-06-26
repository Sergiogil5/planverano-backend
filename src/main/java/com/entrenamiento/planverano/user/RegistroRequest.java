package com.entrenamiento.planverano.user;

public class RegistroRequest {
    private String codigoRegistro;
    private String email;
    private String password;
    private String nombreCompleto;
    private Categoria team; // Asegurémonos de que el nombre es 'team'

    // Getters y Setters
    public String getCodigoRegistro() { return codigoRegistro; }
    public void setCodigoRegistro(String codigoRegistro) { this.codigoRegistro = codigoRegistro; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public Categoria getTeam() { return team; }
    public void setTeam(Categoria team) { this.team = team; }
}