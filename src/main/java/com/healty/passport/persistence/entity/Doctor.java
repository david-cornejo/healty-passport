package com.healty.passport.persistence.entity;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Doctor")
public class Doctor{

    @Id
    @Column(name = "N_Cedula")
    private Integer nCedula;

    @Column(name = "ID_Usuario", insertable=false, updatable=false)
    private Integer idUsuario;

    @Column(name = "Ubicacion")
    private String ubicacion;

    @OneToOne
    @JoinColumn(name = "ID_Usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "doctor")
    private Set<Cita> citas;

    public Doctor() {
    }

    public Integer getnCedula() {
        return nCedula;
    }
    public void setnCedula(Integer nCedula) {
        this.nCedula = nCedula;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Cita> getCitas() {
        return citas;
    }

    public void setCitas(Set<Cita> citas) {
        this.citas = citas;
    }
}
