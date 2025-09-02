package org.asco_devs.reservas_nf.service;

import org.asco_devs.reservas_nf.entity.Huesped;
import java.util.List;

public interface IHuespedesService {
    public List<Huesped> listarHuespedes();
    public Huesped buscarHuespedes(Integer idHuesped);
    public void guardarhuespedes(Huesped huesped);
    public void eliminarhuespedes(Huesped huesped);
}