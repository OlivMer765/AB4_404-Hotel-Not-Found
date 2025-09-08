package org.asco_devs.reservas_nf.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.asco_devs.reservas_nf.entity.Habitacion;
import org.asco_devs.reservas_nf.entity.Huesped;
import org.asco_devs.reservas_nf.entity.Reserva;
import org.asco_devs.reservas_nf.service.IHabitacionService;
import org.asco_devs.reservas_nf.service.IHuespedService;
import org.asco_devs.reservas_nf.service.IReservaService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Date;
import java.util.Objects; // Asegúrate de importar Objects

@Component
@ViewScoped
@Data
public class ReservaController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private IReservaService reservaService;

    @Autowired
    private IHuespedService huespedService;

    @Autowired
    private IHabitacionService habitacionService;

    private List<Reserva> reservas;
    private Reserva reservaSelecionada;

    private List<Huesped> huespedesDisponibles;
    private List<Habitacion> habitacionesDisponibles;


    private Integer selectedHuespedId;
    private Integer selectedHabitacionId;

    private static Logger logger = LoggerFactory.getLogger(ReservaController.class);

    @PostConstruct
    public void init(){
        cargarDatos();
        cargarCatalogos();
    }

    public void cargarDatos(){
        this.reservas = this.reservaService.listarReservas();
        this.reservas.forEach(reserva -> {
            if (reserva.getHuesped() != null) {
                reserva.getHuesped().getNombre();
                reserva.getHuesped().getApellido();
            }
            if (reserva.getHabitacion() != null) {
                reserva.getHabitacion().getTipo();
                reserva.getHabitacion().getEstado();
            }
            logger.info("Reserva: " + reserva.getIdReserva() +
                    ", Huesped: " + (reserva.getHuesped() != null ? reserva.getHuesped().getNombre() + " " + reserva.getHuesped().getApellido() : "N/A") +
                    ", Habitacion: " + (reserva.getHabitacion() != null ? reserva.getHabitacion().getTipo() : "N/A"));
        });
    }

    public void cargarCatalogos() {
        this.huespedesDisponibles = this.huespedService.listarHuespedes();
        this.habitacionesDisponibles = this.habitacionService.listarHabitaciones();
    }

    public void agregarReserva(){
        this.reservaSelecionada = new Reserva();
        this.reservaSelecionada.setFechaEntrada(new Date());
        this.reservaSelecionada.setFechaSalida(new Date());
        this.selectedHuespedId = null;
        this.selectedHabitacionId = null;
    }

    // Método setter personalizado para reservaSelecionada para manejar la edición
    public void setReservaSelecionada(Reserva reservaSelecionada) {
        this.reservaSelecionada = reservaSelecionada;
        if (reservaSelecionada != null) {
            this.selectedHuespedId = reservaSelecionada.getHuesped() != null ? reservaSelecionada.getHuesped().getIdHuesped() : null;
            this.selectedHabitacionId = reservaSelecionada.getHabitacion() != null ? reservaSelecionada.getHabitacion().getIdHabitacion() : null;
        } else {
            this.selectedHuespedId = null;
            this.selectedHabitacionId = null;
        }
    }

    public void guardarReserva(){
        if (selectedHuespedId != null) {
            this.reservaSelecionada.setHuesped(huespedService.buscarHuespedPorId(selectedHuespedId));
        } else {
            this.reservaSelecionada.setHuesped(null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar un huésped."));
            PrimeFaces.current().ajax().update("formulario-reservas:mensaje-emergente");
            return;
        }

        if (selectedHabitacionId != null) {
            this.reservaSelecionada.setHabitacion(habitacionService.buscarHabitacionPorId(selectedHabitacionId));
        } else {
            this.reservaSelecionada.setHabitacion(null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe seleccionar una habitación."));
            PrimeFaces.current().ajax().update("formulario-reservas:mensaje-emergente");
            return;
        }

        if (this.reservaSelecionada.getIdReserva() == null) {
            this.reservaService.guardarReserva(this.reservaSelecionada);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Reserva agregada correctamente."));
        }else {
            this.reservaService.guardarReserva(this.reservaSelecionada);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Reserva actualizada correctamente."));
        }

        PrimeFaces.current().executeScript("PF('ventanaModalReserva').hide()");
        // Importante: Actualizar ambos growl y la tabla.
        PrimeFaces.current().ajax().update("formulario-reservas:mensaje-emergente", "formulario-reservas:tabla-reservas");
        this.reservaSelecionada = null;
        this.selectedHuespedId = null;
        this.selectedHabitacionId = null;
        cargarDatos();
    }

    public void eliminarReserva(){
        logger.info("Reserva a eliminar: "+ this.reservaSelecionada);
        this.reservaService.eliminarReserva(this.reservaSelecionada);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Reserva eliminada correctamente."));
        PrimeFaces.current().ajax().update("formulario-reservas:mensaje-emergente", "formulario-reservas:tabla-reservas");
        this.reservaSelecionada = null;
        this.selectedHuespedId = null;
        this.selectedHabitacionId = null;
        cargarDatos();
    }
}