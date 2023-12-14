package com.healty.passport.web.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.healty.passport.persistence.CuentaRepository;
import com.healty.passport.web.controller.dto.LoginDto;

@RestController
@RequestMapping("/api")
public class login {

    private final CuentaRepository cuentaRepository;

    @Autowired
    public login(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody LoginDto loginDto) {
        boolean isAuthenticated = cuentaRepository.autenticar(loginDto.getCorreo(), loginDto.getContraseña());

        if (isAuthenticated) {
            // Inicio de sesión exitoso
            return ResponseEntity.ok().body("Inicio de sesión exitoso");
        } else {
            // Inicio de sesión fallido
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}
