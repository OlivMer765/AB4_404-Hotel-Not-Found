package org.asco_devs.reservas_nf.service;

import org.springframework.stereotype.Repository;

@Repository
public interface IHuespedRepository  extends JpaRepository<Huesped, Integer> {
}
