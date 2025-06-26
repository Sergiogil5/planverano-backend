package com.entrenamiento.planverano.user;

public class RegistroRequest {
    private String codigoRegistro;
    private String email;
    private String password;
    private String nombreCompleto;
    private Categoria team; // Asegurémonos de que el nombre es 'team'

    // Getters y Setters
    public String getCodigoRegistro() { return codigoRegistro; }
    public void setCodigoRegistro(String c) { this.codigoRegistro = c; }
    public String getEmail() { return email; }
    public void setEmail(String e) { this.email = e; }
    public String getPassword() { return password; }
    public void setPassword(String p) { this.password = p; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String n) { this.nombreCompleto = n; }
    public Categoria getTeam() { return team; }
    public void setTeam(Categoria t) { this.team = t; }
}