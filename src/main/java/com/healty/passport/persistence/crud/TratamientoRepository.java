package com.healty.passport.persistence.crud;
import com.healty.passport.persistence.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import com.healty.passport.persistence.entity.Tratamiento;

import java.util.Optional;

public interface TratamientoRepository extends CrudRepository<Tratamiento, Integer> {
    Tratamiento findByUsuario(Optional<Usuario> usuario);
}
