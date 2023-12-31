package com.healty.passport.persistence.crud;

import com.healty.passport.persistence.entity.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor, Integer> {
    Doctor findBynCedula(Integer nCedula);
}





