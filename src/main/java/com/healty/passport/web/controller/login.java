package com.healty.passport.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.healty.passport.persistence.repository.LoginRepository;
import com.healty.passport.web.controller.dto.LoginDto;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class login {

    private final LoginRepository loginRepository;

    @Autowired
    public login(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @CrossOrigin(origins = "http://localhost")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        Optional<Pair<String, Integer>> autenticacion = loginRepository.autenticar(loginDto.getCorreo(), loginDto.getContraseña());

        if (autenticacion.isPresent()) {
            Pair<String, Integer> tipoUsuarioYId = autenticacion.get();
            return ResponseEntity.ok().body("{\"tipoUsuario\": \"" + tipoUsuarioYId.getFirst() +
                    "\", \"idUsuario\": \"" + tipoUsuarioYId.getSecond() + "\"}");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Credenciales inválidas\"}");
        }
    }
}
