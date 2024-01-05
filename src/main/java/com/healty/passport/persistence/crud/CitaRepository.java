package com.healty.passport.persistence.crud;

import com.healty.passport.persistence.entity.Cita;
import com.healty.passport.persistence.entity.Doctor;
import com.healty.passport.persistence.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CitaRepository extends CrudRepository<Cita, Integer> {
    List<Cita> findByDoctor(Doctor doctor);
    List<Cita> findByUsuario(Optional<Usuario> usuario);
}
