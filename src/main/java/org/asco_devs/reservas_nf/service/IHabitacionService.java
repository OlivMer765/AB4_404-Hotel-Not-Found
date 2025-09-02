package org.asco_devs.reservas_nf.service;

import org.springframework.stereotype.Service;
import org.asco_devs.reservas_nf.entity.Habitacion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IHabitacionService {
    public List<Habitacion> listarHabitaciones();
    public Habitacion buscarHabitacionporId(Integer codigo);
    public void guardarHabitacion(Habitacion habitacion);
    public void eliminarHabitacion(Habitacion habitacion);
}
