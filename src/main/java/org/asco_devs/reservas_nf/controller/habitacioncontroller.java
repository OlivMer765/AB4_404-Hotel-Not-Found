package org.asco_devs.reservas_nf.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.asco_devs.reservas_nf.entity.Habitacion;
import org.asco_devs.reservas_nf.service.IHabitacionService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

@Data
public class habitacioncontroller {
    @Autowired
    IHabitacionService habitacionService;
    private List<Habitacion> habitaciones;
    private Habitacion habitacionSelecionado;
    private static Logger logger = LoggerFactory.getLogger(habitacioncontroller.class);

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    public void cargarDatos() {
        this.habitaciones = this.habitacionService.listarHabitaciones();
        this.habitaciones.forEach(habitacion -> logger.info(habitacion.toString()));
    }

    public void agregarHabitacion(){
        this.habitacionSelecionado = new Habitacion();
    }

    public void guardarHabitacion(){
        logger.info("Habitacion a guardar: "+this.habitacionSelecionado);
        //agregar
        if(this.habitacionSelecionado.getIdHabitacion() == null){
            this.habitacionService.guardarHabitacion(this.habitacionSelecionado);
            this.habitaciones.add(this.habitacionSelecionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Habitacion Agregado"));
        }
        //modificar
        else {
            this.habitacionService.guardarHabitacion(this.habitacionSelecionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Habitacion Actualizado"));
        }

        PrimeFaces.current().executeScript("PF('ventanaModalHabitaciones').hide()");

        PrimeFaces.current().ajax().update("formulario-habitaciones:mensaje-emergente", "formulario-habitaciones:tabla-habitaciones");

        this.habitacionSelecionado = null;
    }

    public void eliminarHabitacion() {
        logger.info("Habitacion a eliminar: "+ this.habitacionSelecionado);
        this.habitacionService.eliminarHabitacion(this.habitacionSelecionado);
        this.habitaciones.remove(this.habitacionSelecionado);
        this.habitacionSelecionado = null;
        //Confirmar accion
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Habitacion Eliminado"));
        PrimeFaces.current().ajax().update("formulario-habitaciones:mensaje-emergente",
                "formulario-habitaciones:tabla-habitaciones");
    }
}
