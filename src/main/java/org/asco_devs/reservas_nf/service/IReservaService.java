package org.asco_devs.reservas_nf.service;

import org.asco_devs.reservas_nf.entity.Reserva;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IReservaService {
    public List<Reserva> listarReservas();
    public Reserva buscarReservaPorId(Integer id);
    public void guardarReserva(Reserva reserva);
    public void eliminarReserva(Reserva reserva);
}
