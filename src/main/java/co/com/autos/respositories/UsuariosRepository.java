package co.com.autos.respositories;

import co.com.autos.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByNombreUsuario(String userName);
}
