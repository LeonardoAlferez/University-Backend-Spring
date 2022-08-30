package com.example.universitybackend.modelo.entidades;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "carreras")
public class Carrera implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false,unique = true, length = 80)
    private String nombre;
    @Column(name = "cantidad_materias")
    private Integer cantidadMaterias;
    @Column(name = "cantidad_anos")
    private Integer cantidadAnos;
    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @OneToMany(
            mappedBy = "carrera",
            fetch = FetchType.LAZY
    )
    private Set<Alumno> alumnos;

    @ManyToMany(
            mappedBy = "carreras",
            fetch = FetchType.LAZY
    )
    private Set<Profesor> profesores;

    public Carrera(Integer id, String nombre, Integer cantidadMaterias, Integer cantidadAnos) {
        this.id = id;
        this.nombre = nombre;
        this.cantidadMaterias = cantidadMaterias;
        this.cantidadAnos = cantidadAnos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carrera carrera = (Carrera) o;
        return id.equals(carrera.id) && nombre.equals(carrera.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @PrePersist
    private void antesDePersistir(){
        this.fechaAlta = LocalDateTime.now();
    }

    @PreUpdate
    private void antesDeUpdate(){
        this.fechaModificacion = LocalDateTime.now();
    }
}
