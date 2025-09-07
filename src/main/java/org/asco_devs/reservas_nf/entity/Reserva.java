package org.asco_devs.reservas_nf.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity(name = "Reserva")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReserva;
    private Date fechaEntrada;
    private Date fechaSalida;
    private Integer idHuesped;
    private Integer idHabitacion;
}
