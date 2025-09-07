package org.asco_devs.reservas_nf.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.asco_devs.reservas_nf.entity.Usuario;
import org.asco_devs.reservas_nf.service.IUsuarioService;
import org.primefaces.PrimeFaces;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.faces.view.ViewScoped;
import lombok.*;
import java.util.List;

@ViewScoped
@Data
@Component
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    private List<Usuario> usuarios;
    private Usuario usuarioSeleccionado;

    private static Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        this.usuarios = this.usuarioService.listarUsuarios();
        this.usuarios.forEach(usuario -> logger.info(usuario.toString()));
    }

    public void agregarUsuario() {
        this.usuarioSeleccionado = new Usuario();
    }

    public void guardarUsuario() {
        logger.info("Usuario a guardar: " + this.usuarioSeleccionado);

        if (this.usuarioSeleccionado.getId() == null) {
            // Nuevo usuario
            this.usuarioService.guardarUsuarios(this.usuarioSeleccionado);
            this.usuarios.add(this.usuarioSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario agregado."));
        } else {
            this.usuarioService.guardarUsuarios(this.usuarioSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario actualizado."));
        }

        PrimeFaces.current().executeScript("PF('ventanaModalUsuario').hide()");
        PrimeFaces.current().ajax().update("formulario-usuario:mensaje-emergente", "formulario-usuario:tabla-usuario");
        this.usuarioSeleccionado = null;
    }

    public void eliminarUsuario() {
        logger.info("Usuario a eliminar: " + this.usuarioSeleccionado);
        this.usuarioService.eliminarUsuario(this.usuarioSeleccionado);
        this.usuarios.remove(this.usuarioSeleccionado);
        this.usuarioSeleccionado = null;

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario eliminado."));
        PrimeFaces.current().ajax().update("formulario-usuario:mensaje-emergente",
                "formulario-usuario:tabla-usuario");
    }
}
