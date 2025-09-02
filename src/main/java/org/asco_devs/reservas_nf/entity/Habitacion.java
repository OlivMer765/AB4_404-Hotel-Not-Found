package org.asco_devs.reservas_nf.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity(name = "Habitaciones")
@Data

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdHabitacion")
    private Integer idHabitacion;
    private String tipo;
    private String estado;
}
