package com.healty.passport.web.controller;

import com.healty.passport.persistence.service.DoctorService;
import com.healty.passport.web.controller.dto.CitaDto;
import com.healty.passport.web.controller.dto.DoctorDto;
import com.healty.passport.web.controller.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctores")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
    @CrossOrigin(origins = "http://localhost")//configuracion del server del front
    @GetMapping("/{idUsuario}/pacientes")
    public ResponseEntity<List<UsuarioDto>> obtenerPacientes(@PathVariable Integer idUsuario) {
        List<UsuarioDto> pacientes = doctorService.obtenerPacientesPorDoctorIdUsuario(idUsuario);
        return ResponseEntity.ok(pacientes);
    }
    @CrossOrigin(origins = "http://localhost")//configuracion del server del front
    @GetMapping("/{idUsuario}/citas")
    public ResponseEntity<List<CitaDto>> obtenerCitas(@PathVariable Integer idUsuario) {
        List<CitaDto> citas = doctorService.obtenerCitasPorDoctorIdUsuario(idUsuario);
        return ResponseEntity.ok(citas);
    }

}


