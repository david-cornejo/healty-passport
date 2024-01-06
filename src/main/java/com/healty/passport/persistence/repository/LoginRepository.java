package com.healty.passport.persistence.repository;
import com.healty.passport.persistence.entity.Cuenta;
import com.healty.passport.persistence.crud.CuentaCrudRepository;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class LoginRepository {
    private final CuentaCrudRepository cuentaCrudRepository;

    public LoginRepository(CuentaCrudRepository cuentaCrudRepository) {
        this.cuentaCrudRepository = cuentaCrudRepository;
    }

    public Optional<Pair<String, Integer>> autenticar(String correo, String contraseña) {
        try {
            Cuenta cuenta = cuentaCrudRepository.findByCorreo(correo);
            if (cuenta != null && cuenta.getContraseña().equals(contraseña)) {
                String tipoUsuario = cuenta.getUsuario().getTipoUsuario();
                Integer idUsuario = cuenta.getUsuario().getIdUsuario();
                return Optional.of(Pair.of(tipoUsuario, idUsuario));
            }
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
