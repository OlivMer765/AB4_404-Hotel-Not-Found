package org.asco_devs.reservas_nf.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.asco_devs.reservas_nf.entity.Reserva;
import org.asco_devs.reservas_nf.service.IReservaService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ViewScoped
@Data
public class ReservaController {
    @Autowired
    private IReservaService reservaService;

    private List<Reserva> reservas;
    private Reserva reservaSelecionada;
    private static Logger logger = LoggerFactory.getLogger(ReservaController.class);
    @PostConstruct
    public void init(){cargarDatos();}

    public void cargarDatos(){
        this.reservas = this.reservaService.listarReservas();
        this.reservas.forEach(reserva -> logger.info(reserva.toString()));
    }

    public void agregarReserva(){this.reservaSelecionada = new Reserva();}

    public void guardarReserva(){
        if (this.getReservas()==null) {
            this.reservaService.guardarReserva(this.reservaSelecionada);
            this.reservas.add(this.reservaSelecionada);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Reserva agregada"));
        }else {
            this.reservaService.guardarReserva(this.reservaSelecionada);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Reserva actualizada"));
        }
        PrimeFaces.current().executeScript("PF('ventanaModalReserva').hide()");
        PrimeFaces.current().ajax().update("formulario-reservas:mensaje-emergente", "formulario-reservas:tabla-reservas");
        this.reservaSelecionada = null;
    }

    public void eliminarReserva(){
        logger.info("Reserva a eliminar: "+ this.reservaSelecionada);
        this.reservaService.eliminarReserva(this.reservaSelecionada);
        this.reservas.remove(this.reservaSelecionada);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Reserva eliminada"));
        PrimeFaces.current().ajax().update("formulario-reservas:mensaje-emergente", "formulario-reservas:tabla-reservas");
        this.reservaSelecionada = null;
    }
}
