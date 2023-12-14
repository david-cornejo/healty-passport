package com.healty.passport.persistence.crud;
import com.healty.passport.persistence.entity.Cuenta;
import org.springframework.data.repository.CrudRepository;

public interface CuentaCrudRepository extends CrudRepository<Cuenta, Integer> {
    Cuenta findByCorreo(String correo);
}
