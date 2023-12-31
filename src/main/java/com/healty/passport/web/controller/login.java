package com.healty.passport.web.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.healty.passport.persistence.repository.LoginRepository;
import com.healty.passport.web.controller.dto.LoginDto;

@RestController
@RequestMapping("/api")
public class login {

    private final LoginRepository loginRepository;

    @Autowired
    public login(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }


    @CrossOrigin(origins = "http://localhost")//configuracion del server del front
    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody LoginDto loginDto) {
        boolean isAuthenticated = loginRepository.autenticar(loginDto.getCorreo(), loginDto.getContraseña());

        if (isAuthenticated) {
            // Inicio de sesión exitoso
            return ResponseEntity.ok().body("{\"mensaje\": \"Inicio de sesión exitoso\"}");
        } else {
            // Inicio de sesión fallido
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Credenciales inválidas\"}");
        }
    }
}
