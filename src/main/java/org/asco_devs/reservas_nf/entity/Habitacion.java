package org.asco_devs.reservas_nf.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "Habitaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Habitacion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHabitacion")
    private Integer idHabitacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoHabitacion tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoHabitacion estado;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habitacion habitacion = (Habitacion) o;
        return idHabitacion != null && idHabitacion.equals(habitacion.idHabitacion);
    }

    @Override
    public int hashCode() {
        return idHabitacion != null ? idHabitacion.hashCode() : 0;
    }
}