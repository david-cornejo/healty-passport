package com.healty.passport.web.controller.dto;

public class CitaDto {
    private String titulo;
    private String inicio;
    private String fin;

    private String nombreDoctor;
    private String ubicacionDoctor;
    // Constructores, getters y setters


    public String getNombreDoctor() {
        return nombreDoctor;
    }

    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor = nombreDoctor;
    }

    public String getUbicacionDoctor() {
        return ubicacionDoctor;
    }

    public void setUbicacionDoctor(String ubicacionDoctor) {
        this.ubicacionDoctor = ubicacionDoctor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }
}
