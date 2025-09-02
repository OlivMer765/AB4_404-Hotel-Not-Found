package org.asco_devs.reservas_nf.repository;

import org.asco_devs.reservas_nf.entity.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.asco_devs.reservas_nf.entity.Habitacion;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionRepository  extends JpaRepository<Habitacion, Integer> {
}
