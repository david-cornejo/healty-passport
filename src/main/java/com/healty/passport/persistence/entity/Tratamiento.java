package com.healty.passport.persistence.entity;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tratamiento")
public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Tratamiento")
    private Integer idTratamiento;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario", referencedColumnName = "ID_Usuario")
    private Usuario usuario;
    @Column(name = "Medicamento")
    private String medicamento;
    @Column(name = "Fecha_final")
    private Date fechaFinal;
    @Column(name = "Fecha_inicio")
    private Date fechaInicio;
    @Column(name = "Enfermedad")
    private String enfermedad;
    //@Column(name = "ID_Cita")
    //private Integer idCita;

    public Tratamiento() {
    }

    public Integer getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(Integer idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    //public Integer getIdCita() {
       // return idCita;
    //}

    //public void setIdCita(Integer idCita) {
     //  this.idCita = idCita;
    //}
}
