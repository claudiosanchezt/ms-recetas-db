package cl.duoc.api.model.repositories;

import cl.duoc.api.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    List<Usuario> findByNombre(String nombre);
}
