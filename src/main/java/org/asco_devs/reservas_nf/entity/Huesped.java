package org.asco_devs.reservas_nf.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "Huespedes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Huesped implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHuesped")
    private Integer idHuesped;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "correo")
    private String correo;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "contrasena")
    private String contrasena;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Huesped huesped = (Huesped) o;
        return idHuesped != null && idHuesped.equals(huesped.idHuesped);
    }

    @Override
    public int hashCode() {
        return idHuesped != null ? idHuesped.hashCode() : 0;
    }
}