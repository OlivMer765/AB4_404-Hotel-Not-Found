package org.asco_devs.reservas_nf.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity(name = "Huesped")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class Huesped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHuesped;
    private String apellido;
    private String correo;
    private String telefono;
    private String direccion;
    private String contraseña;
}
