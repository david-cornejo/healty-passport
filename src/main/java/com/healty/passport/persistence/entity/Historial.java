package com.healty.passport.persistence.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "historial")
public class Historial {

    @Id
    @Column(name = "ID_Usuario")
    private Integer idUsuario;

    @Column(name = "Descripcion_Historial", columnDefinition = "TEXT")
    private String descripcionHistorial;

    @OneToOne
    @JoinColumn(name = "ID_Usuario")
    private Usuario usuario;

    public Historial() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDescripcionHistorial() {
        return descripcionHistorial;
    }

    public void setDescripcionHistorial(String descripcionHistorial) {
        this.descripcionHistorial = descripcionHistorial;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
