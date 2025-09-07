package org.asco_devs.reservas_nf.service;

import lombok.RequiredArgsConstructor;
import org.asco_devs.reservas_nf.entity.Huesped;
import org.asco_devs.reservas_nf.entity.Usuario;
import org.asco_devs.reservas_nf.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario registrar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> login(String correo, String contrasena) {
        return usuarioRepository.findByCorreo(correo)
                .filter(u -> u.getContrasena().equals(contrasena));
    }

    public Optional<Usuario> findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    public List<Usuario> listarUsuarios() {

        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarUsuarios(String correo) {
        Optional<Usuario> usuario = usuarioRepository.findByCorreo(correo);
        return usuario.orElse(null);
    }

    @Override
    public void guardarUsuarios(Usuario usuario) {

        usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {

        usuarioRepository.delete(usuario);
    }
}