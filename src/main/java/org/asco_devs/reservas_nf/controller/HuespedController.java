package org.asco_devs.reservas_nf.controller;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.asco_devs.reservas_nf.entity.Huesped;
import org.asco_devs.reservas_nf.service.IHuespedService;
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
public class HuespedController {

    @Autowired
    IHuespedService huespedService;
    private List<Huesped> huespedes;
    private Huesped huespedSeleccionado;
    private static Logger logger = LoggerFactory.getLogger(HuespedController.class);

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        this.huespedes = this.huespedService.listarHuespedes();
        this.huespedes.forEach(huespedes -> logger.info(huespedes.toString()));
    }

    public void agregarHuesped() {
        this.huespedSeleccionado = new Huesped();
    }

    public void guardarHuesped() {
        logger.info("Huesped a guardar: "+ this.huespedSeleccionado);
        if(this.getHuespedes()==null){
            this.huespedService.guardarHuespedes(this.huespedSeleccionado);
            this.huespedes.add(this.huespedSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Huesped Agregado."));
        } else {
            this.huespedService.guardarHuespedes(this.huespedSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Huesped Actualizado"));
        }
        PrimeFaces.current().executeScript("PF('ventanaModalHuesped').hide()");
        PrimeFaces.current().ajax().update("formulario-huesped:mensaje-emergente", "formulario-huesped:tabla-huesped");
        this.huespedSeleccionado = null;
    }

    public void eliminarHuesped() {
        logger.info("Huesped a eliminar: "+ this.huespedSeleccionado);
        this.huespedService.eliminarHuesped(this.huespedSeleccionado);
        this.huespedes.remove(this.huespedSeleccionado);
        this.huespedSeleccionado = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Huesped Eliminado"));
        PrimeFaces.current().ajax().update("formulario-huespedes:mensaje-emergente",
                "formulario-huespedes:tabla-huespedes");
    }
}
