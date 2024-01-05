package com.healty.passport.web.controller.dto;

public class DoctorDto {
    private String nombre;

    private String apellidos;
    private Integer cedulaProfesional;
    private String telefono;
    private String correo;
    private String ubicacion;
    // Getters y setters


    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCedulaProfesional() {
        return cedulaProfesional;
    }

    public void setCedulaProfesional(Integer cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
