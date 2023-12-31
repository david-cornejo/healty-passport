package com.healty.passport.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.healty.passport.web.controller.dto.UsuarioDto;
import com.healty.passport.persistence.service.UsuarioService;
import com.healty.passport.persistence.entity.Usuario;

import java.util.HashMap;
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

    // Otros endpoints...
}
