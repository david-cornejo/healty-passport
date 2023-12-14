package com.healty.passport.persistence;
import com.healty.passport.persistence.entity.Cuenta;
import com.healty.passport.persistence.crud.CuentaCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CuentaRepository {
    private final CuentaCrudRepository cuentaCrudRepository;
    public CuentaRepository(CuentaCrudRepository cuentaCrudRepository) {
        this.cuentaCrudRepository = cuentaCrudRepository;
    }
    public boolean autenticar(String correo, String contraseña) {
        try {
            Cuenta cuenta = cuentaCrudRepository.findByCorreo(correo);

            if (cuenta != null) {
                return cuenta.getContraseña().equals(contraseña);
            }
            return false;
        } catch (Exception e) {
            // Aquí puedes loguear la excepción para obtener más detalles
            e.printStackTrace();
            return false;
        }
    }
}
