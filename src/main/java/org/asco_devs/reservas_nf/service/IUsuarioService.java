package org.asco_devs.reservas_nf.service;

import org.asco_devs.reservas_nf.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    public List<Usuario> listarUsuarios();
    public Usuario buscarUsuarios(String correo);
    public void guardarUsuarios(Usuario usuario);
    public void eliminarUsuario(Usuario usuario);
}
