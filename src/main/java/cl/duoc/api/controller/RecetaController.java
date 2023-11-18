package cl.duoc.api.controller;

import cl.duoc.api.model.entities.Usuario;
import cl.duoc.api.model.entities.Receta;
import cl.duoc.api.service.RecetaService;
import cl.duoc.api.service.UsuarioService;
import cl.duoc.api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.duoc.api.model.dto.RecetaDTO;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recetas")
@Log4j2
public class RecetaController {

    @Autowired
    RecetaService recetaService;
    @Autowired
    JwtUtil jwtUtil;

    @GetMapping
    public List<Receta> listarTodasRecetas() {
        return recetaService.listarTodasRecetas();
    }

    @GetMapping(path = "/{id_receta}")
    public ResponseEntity<Receta> buscarRecetaPorId(@PathVariable("id_receta") int idReceta) {
        Optional<Receta> receta = recetaService.buscarRecetaPorId(idReceta);
        if (receta.isPresent()) {
            return ResponseEntity.ok(receta.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public String crearReceta(@RequestBody RecetaDTO recetaDTO) {
        log.info("Creando la receta en la base de datos");
        return recetaService.crearReceta(recetaDTO);
    }
    @GetMapping(path = "/nombre/{nombre}")
    public List<Receta> buscarRecetasPorNombre(@PathVariable("nombre") String nombre) {
        return recetaService.buscarRecetasPorNombre(nombre);
    }
    @PutMapping(path = "/{id_receta}")
    public ResponseEntity<String> modificarReceta(@PathVariable("id_receta") int idReceta, @RequestBody RecetaDTO recetaDTO) {
        boolean resultado = recetaService.modificarReceta(idReceta, recetaDTO);
        if (resultado) {
            return ResponseEntity.ok("Receta modificada exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
