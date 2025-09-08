package org.asco_devs.reservas_nf.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Reserva")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReserva")
    private Integer idReserva;

    @Column(name = "fechaEntrada")
    private Date fechaEntrada;

    @Column(name = "fechaSalida")
    private Date fechaSalida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idHuesped", referencedColumnName = "idHuesped", nullable = false)
    private Huesped huesped;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idHabitacion", referencedColumnName = "idHabitacion", nullable = false)
    private Habitacion habitacion;
}