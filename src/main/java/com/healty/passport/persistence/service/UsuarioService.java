package com.healty.passport.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.healty.passport.persistence.crud.CuentaRepository;
import com.healty.passport.persistence.crud.DoctorRepository;
import com.healty.passport.persistence.crud.UsuarioRepository;
import com.healty.passport.persistence.entity.Cuenta;
import com.healty.passport.persistence.entity.Doctor;
import com.healty.passport.persistence.entity.Usuario;
import com.healty.passport.web.controller.dto.UsuarioDto;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final DoctorRepository doctorRepository;
    private final CuentaRepository cuentaRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, DoctorRepository doctorRepository, CuentaRepository cuentaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.doctorRepository = doctorRepository;
        this.cuentaRepository = cuentaRepository;
    }

    @Transactional
    public Usuario crearUsuarioConCuenta(UsuarioDto usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellidos(usuarioDTO.getApellidos());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setFechaDeNacimiento(usuarioDTO.getFechaDeNacimiento());
        usuario.setTipoUsuario(usuarioDTO.getTipoUsuario());

        if ("paciente".equals(usuarioDTO.getTipoUsuario())) {
            Doctor doctorAsociado = doctorRepository.findBynCedula(usuarioDTO.getCedulaProfesional());
            if (doctorAsociado != null) {
                usuario.setDoctor(doctorAsociado);
            } else {
                throw new RuntimeException("No se pudo encontrar al doctor con la cédula proporcionada.");
            }
        } else if ("doctor".equals(usuarioDTO.getTipoUsuario())) {
            Doctor doctor = new Doctor();
            doctor.setUsuario(usuario);
            doctor.setUbicacion(usuarioDTO.getUbicacion());
            doctor.setnCedula(usuarioDTO.getCedulaProfesional());
            usuario.setDoctor(doctor);
        } else {
            usuario = usuarioRepository.save(usuario);
        }

        Cuenta cuenta = new Cuenta();
        cuenta.setIdUsuario(usuario.getIdUsuario());
        cuenta.setCorreo(usuario.getCorreo());
        cuenta.setContraseña(usuarioDTO.getContraseña());
        cuenta.setUsuario(usuario);
        cuentaRepository.save(cuenta);

        return usuario;
    }

    // Otros métodos del servicio...
}
