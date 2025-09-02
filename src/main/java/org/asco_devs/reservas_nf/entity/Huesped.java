package org.asco_devs.reservas_nf.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Huespedes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Huesped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHuesped;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String direccion;
    private String contrasena;
}
