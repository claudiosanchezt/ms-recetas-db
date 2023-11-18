package cl.duoc.api.model.repositories;

import cl.duoc.api.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.api.model.entities.Receta;

import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta, Integer>{
    List<Receta> findByNombre(String nombre);
    
}
