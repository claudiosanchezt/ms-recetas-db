package cl.duoc.api.service;

import cl.duoc.api.model.entities.Usuario;
import cl.duoc.api.model.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(int id) {
        return usuarioRepository.findById(id);
    }

    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(int id, Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            usuario.setId_usr(id);
            return usuarioRepository.save(usuario);
        } else {
            // Manejo de error si no se encuentra el usuario
            throw new IllegalArgumentException("El usuario con ID " + id + " no existe.");
        }
    }

    public void deleteUsuario(int id) {
        usuarioRepository.deleteById(id);
    }
}
