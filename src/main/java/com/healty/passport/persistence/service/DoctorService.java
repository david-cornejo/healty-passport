package com.healty.passport.persistence.service;

import com.healty.passport.persistence.crud.CitaRepository;
import com.healty.passport.persistence.crud.DoctorRepository;
import com.healty.passport.persistence.crud.UsuarioRepository;
import com.healty.passport.persistence.entity.Cita;
import com.healty.passport.persistence.entity.Doctor;
import com.healty.passport.persistence.entity.Usuario;
import com.healty.passport.web.controller.dto.CitaDto;
import com.healty.passport.web.controller.dto.DoctorDto;
import com.healty.passport.web.controller.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final CitaRepository citaRepository;

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, CitaRepository citaRepository, UsuarioRepository usuarioRepository) {
        this.doctorRepository = doctorRepository;
        this.citaRepository = citaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDto> obtenerPacientesPorDoctorIdUsuario(Integer idUsuario) {
        Doctor doctor = doctorRepository.findByIdUsuario(idUsuario);
        List<Cita> citas = citaRepository.findByDoctor(doctor);

        return citas.stream()
                .map(Cita::getUsuario) // Usando el método getUsuario() de la entidad Cita
                .distinct()
                .map(this::convertirAPacienteDto)
                .collect(Collectors.toList());
    }

    private UsuarioDto convertirAPacienteDto(Usuario paciente) {
        // Implementación para convertir Usuario (paciente) a UsuarioDto
        UsuarioDto pacienteDto = new UsuarioDto();
        // Setear propiedades...
        pacienteDto.setNombre(paciente.getNombre());
        pacienteDto.setApellidos(paciente.getApellidos());
        pacienteDto.setId(paciente.getIdUsuario());
        // ... Setear las demás propiedades según sean necesarias
        return pacienteDto;
    }
    public List<CitaDto> obtenerCitasPorDoctorIdUsuario(Integer idUsuario) {
        Doctor doctor = doctorRepository.findByIdUsuario(idUsuario);
        List<Cita> citas = citaRepository.findByDoctor(doctor);

        return citas.stream().map(this::convertirACitaDto).collect(Collectors.toList());
    }

    private CitaDto convertirACitaDto(Cita cita) {
        CitaDto citaDto = new CitaDto();
        citaDto.setTitulo("Cita con " + cita.getUsuario().getNombre() + " " + cita.getUsuario().getApellidos());
        citaDto.setInicio(formatFecha(cita.getFecha(), "yyyy-MM-dd hh:mm:ss"));
        citaDto.setFin(formatFecha(calcularFechaFin(cita.getFecha(), DURACION_CITA), "yyyy-MM-dd hh:mm:ss"));
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


}
