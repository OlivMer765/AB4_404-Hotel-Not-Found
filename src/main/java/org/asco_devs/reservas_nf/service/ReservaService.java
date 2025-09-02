package org.asco_devs.reservas_nf.service;

import org.asco_devs.reservas_nf.entity.Reserva;
import org.asco_devs.reservas_nf.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService implements IReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public List<Reserva> listarReservas() {
        List<Reserva> reserva = reservaRepository.findAll();
        return reserva;
    }

    @Override
    public Reserva buscarReservaPorId(Integer id) {
        Reserva reserva = reservaRepository.findById(id).orElse(null);
        return reserva;
    }

    @Override
    public void guardarReserva(Reserva reserva) {
        reservaRepository.save(reserva);
    }

    @Override
    public void eliminarReserva(Reserva reserva) {
        reservaRepository.delete(reserva);
    }
}