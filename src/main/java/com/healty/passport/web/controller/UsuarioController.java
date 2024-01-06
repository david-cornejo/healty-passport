package com.healty.passport.web.controller;
import com.healty.passport.persistence.entity.Tratamiento;
import com.healty.passport.web.controller.dto.DoctorDto;
import com.healty.passport.web.controller.dto.TratamientoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.healty.passport.web.controller.dto.UsuarioDto;
import com.healty.passport.web.controller.dto.HistorialDto;
import com.healty.passport.web.controller.dto.CitaDto;
import com.healty.passport.persistence.service.UsuarioService;
import com.healty.passport.persistence.entity.Usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @CrossOrigin(origins = "http://localhost")//configuracion del server del front
    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioDto usuarioDTO) {
        try {
            Usuario usuarioCreado = usuarioService.crearUsuarioConCuenta(usuarioDTO);
            // Crear un objeto de respuesta
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Usuario creado con éxito");
            response.put("idUsuario", usuarioCreado.getIdUsuario());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error al crear el usuario");
            response.put("error", e.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    @CrossOrigin(origins = "http://localhost")//configuracion del server del front
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuarioPorId(@PathVariable Integer id) {
        try {
            UsuarioDto usuarioDto = usuarioService.obtenerUsuarioPorId(id);
            return ResponseEntity.ok(usuarioDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al obtener el usuario\"}");
        }
    }
    @CrossOrigin(origins = "http://localhost")//configuracion del server del front
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioDto usuarioDto) {
        Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, usuarioDto);
        return ResponseEntity.ok(usuarioActualizado);
    }
    @CrossOrigin(origins = "http://localhost")//configuracion del server del front
    @GetMapping("/{idUsuario}/citas")
    public ResponseEntity<List<CitaDto>> obtenerCitasDelUsuario(@PathVariable Integer idUsuario) {
        List<CitaDto> citas = usuarioService.obtenerCitasPorUsuarioId(idUsuario);
        return ResponseEntity.ok(citas);
    }

    @CrossOrigin(origins = "http://localhost")//configuracion del server del front
    @GetMapping("/detalles/{idUsuario}")
    public ResponseEntity<List<DoctorDto>> obtenerDoctorDeUsuario(@PathVariable Integer idUsuario) {
        List<DoctorDto> doctor = usuarioService.obtenerDetallesDoctorPorUsuarioId(idUsuario);
        return ResponseEntity.ok(doctor);
    }
    @CrossOrigin(origins = "http://localhost")//configuracion del server del front
    @GetMapping("/tratamiento/{idUsuario}")
    public ResponseEntity<TratamientoDto> obtenerTratamientoPorUsuarioId(@PathVariable Integer idUsuario) {
        TratamientoDto tratamiento = usuarioService.obtenerTratamientoPorUsuarioId(idUsuario);
        return ResponseEntity.ok(tratamiento);
    }
    @CrossOrigin(origins = "http://localhost")//configuracion del server del front
    @PutMapping("/{idUsuario}/editTratamiento")
    public ResponseEntity<Tratamiento> editarTratamiento(@PathVariable Integer idUsuario, @RequestBody TratamientoDto tratamientoDto) {

        Tratamiento tratamientoActualizado = usuarioService.actualizarTratamiento(idUsuario, tratamientoDto);
        return ResponseEntity.ok(tratamientoActualizado);
    }
    @CrossOrigin(origins = "http://localhost")//configuracion del server del front
    @GetMapping("/historial/{idUsuario}")
    public ResponseEntity<HistorialDto> obtenerHistorialporUsuarioId(@PathVariable Integer idUsuario) {
        HistorialDto historial = usuarioService.obtenerHistorialporUsuarioId(idUsuario);
        return ResponseEntity.ok(historial);
    }
    // Otros endpoints...
}
