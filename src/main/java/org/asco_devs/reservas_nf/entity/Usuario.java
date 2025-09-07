package org.asco_devs.reservas_nf.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 64)
    private String nombre;

    @Column(nullable = false, length = 64)
    private String apellido;

    @Column(nullable = false, unique = true, length = 128)
    private String correo;

    @Column(nullable = false, length = 8)
    private String telefono;

    @Column(length = 128)
    private String direccion;

    @Column(nullable = false, length = 100)
    private String contrasena;
}
