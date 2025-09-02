package org.asco_devs.reservas_nf.service;

import org.asco_devs.reservas_nf.repository.HuespedRepository;
import org.asco_devs.reservas_nf.entity.Huesped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class HuespedesService implements IHuespedesService {

    @Autowired
    private HuespedRepository huespedRepository;

    @Override
    public List<Huesped> listarHuespedes() {
        return huespedRepository.findAll();
    }

    @Override
    public Huesped buscarHuespedes(Integer idHuesped) {
        Optional<Huesped> huesped = huespedRepository.findById(idHuesped);
        return huesped.orElse(null);
    }

    @Override
    public void guardarhuespedes(Huesped huesped) {
        huespedRepository.save(huesped);
    }

    @Override
    public void eliminarhuespedes(Huesped huesped) {
        huespedRepository.delete(huesped);
    }
}