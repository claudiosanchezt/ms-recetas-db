package cl.duoc.api.controller;

import cl.duoc.api.model.dto.UsuarioDTO;
import cl.duoc.api.model.entities.Usuario;
import cl.duoc.api.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioDTO> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        return usuarios.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id_usr}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable("id_usr") int id_usr) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id_usr);
        return usuario.map(value -> ResponseEntity.ok(convertToDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public UsuarioDTO createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = convertToEntity(usuarioDTO);
        Usuario createdUsuario = usuarioService.createUsuario(usuario);
        return convertToDTO(createdUsuario);
    }

    @PutMapping("/{id_usr}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable("id_usr") int id_usr, @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = convertToEntity(usuarioDTO);
        try {
            Usuario updatedUsuario = usuarioService.updateUsuario(id_usr, usuario);
            return ResponseEntity.ok(convertToDTO(updatedUsuario));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id_usr}")
    public ResponseEntity<String> deleteUsuario(@PathVariable("id_usr") int id_usr) {
        try {
            usuarioService.deleteUsuario(id_usr);
            return ResponseEntity.ok("Usuario eliminado exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        BeanUtils.copyProperties(usuario, usuarioDTO);
        return usuarioDTO;
    }

    private Usuario convertToEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        return usuario;
    }
}