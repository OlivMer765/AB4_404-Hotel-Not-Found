package org.asco_devs.reservas_nf.controller;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.asco_devs.reservas_nf.entity.Usuario;
import org.asco_devs.reservas_nf.service.UsuarioService;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Optional;

@Data
@Component
@ManagedBean
@SessionScoped
@RequiredArgsConstructor
public class AuthController implements Serializable {

    private final UsuarioService usuarioService;

    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String direccion;
    private String contrasena;

    private Usuario usuarioLogueado;

    public String registrar() {
        Usuario usuario = Usuario.builder()
                .nombre(nombre)
                .apellido(apellido)
                .correo(correo)
                .telefono(telefono)
                .direccion(direccion)
                .contrasena(contrasena)
                .build();
        usuarioService.registrar(usuario);
        limpiarCampos();
        return "login.xhtml?faces-redirect=true";
    }

    public String login() {
        Optional<Usuario> usuario = usuarioService.login(correo, contrasena);
        if (usuario.isPresent()) {
            usuarioLogueado = usuario.get();
            FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap().put("authController", this);
            limpiarCampos();
            return "index.xhtml?faces-redirect=true";
        }
        return "login.xhtml?error=true";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        usuarioLogueado = null;
        return "login.xhtml?faces-redirect=true";
    }

    public boolean isLoggedIn() {
        return usuarioLogueado != null;
    }

    private void limpiarCampos() {
        this.nombre = null;
        this.apellido = null;
        this.correo = null;
        this.telefono = null;
        this.direccion = null;
        this.contrasena = null;
    }
}
