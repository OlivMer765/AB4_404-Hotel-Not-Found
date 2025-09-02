package org.asco_devs.reservas_nf.service;

import org.asco_devs.reservas_nf.entity.Habitacion;
import org.asco_devs.reservas_nf.repository.HabitacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class HabitacionService implements IHabitacionService {
    @Autowired
    private HabitacionRepository habitacionRepository;

    public List<Habitacion> listarHabitaciones() {
        List<Habitacion> habitaciones = habitacionRepository.findAll();
        return habitaciones;
    }

    public Habitacion buscarHabitacionporId (Integer codigo){
            Habitacion habitacion = habitacionRepository.findById(codigo).orElse(null);return habitacion;
    }
        public void guardarHabitacion (Habitacion habitacion){
            habitacionRepository.save(habitacion);
    }

        public void eliminarHabitacion (Habitacion habitacion){
            habitacionRepository.delete(habitacion);
    }
}
