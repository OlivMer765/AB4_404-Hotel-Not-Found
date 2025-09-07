package org.asco_devs.reservas_nf.controller;

import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
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

    //Crear admin por defecto al iniciar
    @PostConstruct
    public void initAdmin() {
        Optional<Usuario> adminExistente = usuarioService.findByCorreo("admin@correo.com");
        if (adminExistente.isEmpty()) {
            Usuario admin = Usuario.builder()
                    .nombre("Admin")
                    .apellido("Default")
                    .correo("admin@correo.com")
                    .telefono("00000000")
                    .direccion("N/A")
                    .contrasena("admin123")
                    .rol("ADMIN")
                    .build();
            usuarioService.registrar(admin);
        }
    }

    public String registrar() {
        Usuario usuario = Usuario.builder()
                .nombre(nombre)
                .apellido(apellido)
                .correo(correo)
                .telefono(telefono)
                .direccion(direccion)
                .contrasena(contrasena)
                .rol("USER")
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

            // Redirigir según rol
            if ("ADMIN".equals(usuarioLogueado.getRol())) {
                return "admin.xhtml?faces-redirect=true";
            } else {
                return "index.xhtml?faces-redirect=true";
            }
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