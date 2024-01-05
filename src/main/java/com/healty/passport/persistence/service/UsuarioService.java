package com.healty.passport.persistence.service;

import com.healty.passport.persistence.crud.*;
import com.healty.passport.persistence.entity.*;
import com.healty.passport.web.controller.dto.CitaDto;
import com.healty.passport.web.controller.dto.DoctorDto;
import com.healty.passport.web.controller.dto.TratamientoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.healty.passport.web.controller.dto.UsuarioDto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final DoctorRepository doctorRepository;
    private final CuentaRepository cuentaRepository;
    private final CitaRepository citaRepository;
    private final TratamientoRepository tratamientoRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, DoctorRepository doctorRepository,
                          CuentaRepository cuentaRepository, CitaRepository citaRepository,
                          TratamientoRepository tratamientoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.doctorRepository = doctorRepository;
        this.cuentaRepository = cuentaRepository;
        this.citaRepository = citaRepository;
        this.tratamientoRepository = tratamientoRepository;
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
        }

        Cuenta cuenta = new Cuenta();
        cuenta.setIdUsuario(usuario.getIdUsuario());
        cuenta.setCorreo(usuario.getCorreo());
        cuenta.setContraseña(usuarioDTO.getContraseña());
        cuenta.setUsuario(usuario);
        cuentaRepository.save(cuenta);
        System.out.println(usuario.getDoctor().getnCedula());
        return usuario;
    }
    public UsuarioDto obtenerUsuarioPorId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        // Convertir Usuario a UsuarioDto...
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNombre(usuario.getNombre());
        usuarioDto.setApellidos(usuario.getApellidos());
        usuarioDto.setFechaDeNacimiento(usuario.getFechaDeNacimiento());
        usuarioDto.setTelefono(usuario.getTelefono());
        usuarioDto.setCorreo(usuario.getCorreo());
        usuarioDto.setContraseña(cuenta.getContraseña());
        // Setear los otros campos...
        return usuarioDto;
    }

    @Transactional
    public Usuario actualizarUsuario(Integer idUsuario, UsuarioDto usuarioDto) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Cuenta cuenta = cuentaRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        // Actualizar datos
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellidos(usuarioDto.getApellidos());
        usuario.setFechaDeNacimiento(usuarioDto.getFechaDeNacimiento());
        usuario.setTelefono(usuarioDto.getTelefono());
        usuario.setCorreo(usuarioDto.getCorreo());
        cuenta.setCorreo(usuarioDto.getCorreo());
        cuenta.setContraseña(usuarioDto.getContraseña());
        // ... actualizar otros campos
        //cuentaRepository.save(cuenta);
        //usuarioRepository.save(usuario);

        return usuario;
    }

    public List<CitaDto> obtenerCitasPorUsuarioId(Integer idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        List<Cita> citasDelUsuario = citaRepository.findByUsuario(usuario);

        return citasDelUsuario.stream().map(this::convertirACitaDto).collect(Collectors.toList());
    }

    private CitaDto convertirACitaDto(Cita cita) {
        // Implementa la conversión de Cita a CitaDto
        CitaDto citaDto = new CitaDto();
        citaDto.setTitulo(" " + cita.getDoctor().getUsuario().getNombre() + " " + cita.getDoctor().getUsuario().getApellidos());
        citaDto.setInicio(formatFecha(cita.getFecha(), "yyyy-MM-dd hh:mm:ss"));
        citaDto.setNombreDoctor(cita.getDoctor().getUsuario().getNombre());
        citaDto.setUbicacionDoctor(cita.getDoctor().getUbicacion());
        return citaDto;
    }

    private String formatFecha(Date fecha, String formato) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        //sdf.setTimeZone(TimeZone.getTimeZone("CST")); // Asegúrate de que "CST" es la zona horaria correcta para tu ubicación
        return sdf.format(fecha);
    }

    private Date calcularFechaFin(Date inicio, int duracionMinutos) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inicio);
        calendar.add(Calendar.MINUTE, duracionMinutos);
        return calendar.getTime();
    }
    private static final int DURACION_CITA = 60; // Duración predeterminada de la cita en minutos

    public List<DoctorDto> obtenerDetallesDoctorPorUsuarioId(Integer idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        List<Cita> citasDelUsuario = citaRepository.findByUsuario(usuario);

        return citasDelUsuario.stream().map(this::convertirADoctorDto).collect(Collectors.toList());
    }

    private DoctorDto convertirADoctorDto(Cita cita) {
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setNombre(cita.getDoctor().getUsuario().getNombre());
        doctorDto.setApellidos(cita.getDoctor().getUsuario().getApellidos());
        doctorDto.setCedulaProfesional(cita.getDoctor().getnCedula());
        doctorDto.setTelefono(cita.getDoctor().getUsuario().getTelefono());
        doctorDto.setCorreo(cita.getDoctor().getUsuario().getCorreo());
        doctorDto.setUbicacion(cita.getDoctor().getUbicacion());
        // ... otros campos que necesites
        return doctorDto;
    }
    public TratamientoDto obtenerTratamientoPorUsuarioId(Integer idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        Tratamiento tratamiento = tratamientoRepository.findByUsuario(usuario);

        // Convertir el tratamiento a DTO
        TratamientoDto tratamientoDto = new TratamientoDto();
        tratamientoDto.setMedicamento(tratamiento.getMedicamento());
        tratamientoDto.setFechaInicio(formatFecha(tratamiento.getFechaInicio(), "yyyy-MM-dd"));
        tratamientoDto.setFechaFinal(formatFecha(tratamiento.getFechaFinal(), "yyyy-MM-dd"));
        tratamientoDto.setEnfermedad(tratamiento.getEnfermedad());

        return tratamientoDto;
    }

    // Otros métodos del servicio...
}
