package com.healty.passport.persistence.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "Cuenta")
public class Cuenta {

    @Id
    @Column(name = "ID_Usuario")
    private Integer idUsuario;

    @Column(name = "Correo")
    private String correo;

    @Column(name = "Contraseña")
    private String contraseña;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ID_Usuario")
    private Usuario usuario;

    public Cuenta() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
