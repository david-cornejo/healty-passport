package com.healty.passport.persistence.crud;

import com.healty.passport.persistence.entity.Cuenta;
import org.springframework.data.repository.CrudRepository;

public interface CuentaRepository extends CrudRepository<Cuenta, Integer> {
    // Métodos adicionales si son necesarios
}

