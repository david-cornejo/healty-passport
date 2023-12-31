package com.healty.passport.persistence.crud;

import com.healty.passport.persistence.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    // Métodos adicionales si son necesarios
}
