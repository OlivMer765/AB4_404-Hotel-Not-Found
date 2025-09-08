package org.asco_devs.reservas_nf.service;

import org.asco_devs.reservas_nf.entity.Huesped;
import java.util.List;

public interface IHuespedService {
    public List<Huesped> listarHuespedes();
    public Huesped buscarHuespedPorId(Integer idHuesped);
    public void guardarHuespedes(Huesped huesped);
    public void eliminarHuesped(Huesped huesped);
}