// Nuevo archivo: TrainingWeekDTO.java
package com.entrenamiento.planverano.training;

import java.util.List;

public class TrainingWeekDTO {
    private int weekNumber;
    private String title;
    private List<SesionDiaria> days;

    // Constructor, Getters y Setters...
    public TrainingWeekDTO(int wn, String t, List<SesionDiaria> d) { this.weekNumber = wn; this.title = t; this.days = d; }
    public int getWeekNumber() { return weekNumber; }
    public String getTitle() { return title; }
    public List<SesionDiaria> getDays() { return days; }
}