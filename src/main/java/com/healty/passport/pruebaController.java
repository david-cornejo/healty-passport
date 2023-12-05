package com.healty.passport;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludar")
public class pruebaController {
    @GetMapping("/hola")
    public String saludo(){
        return "esto es una prueba del controlador";
    }
}
