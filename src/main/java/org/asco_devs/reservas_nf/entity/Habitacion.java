package org.asco_devs.reservas_nf.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity(name = "Habitacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHabitacion;
    private String tipo;
    private String estado;
}
