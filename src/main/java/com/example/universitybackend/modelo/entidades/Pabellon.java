package com.example.universitybackend.modelo.entidades;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "pabellones")
public class Pabellon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "metros_cuadrados")
    private Double mts2;
    @Column(name = "nombre_pabellon",nullable = false, unique = true)
    private String nombre;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "zip", column = @Column(name = "codigo_postal"))
    })
    private Direccion direccion;
    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;
    @Column(name = "fecha_modificacion")
    private  LocalDateTime fechaModificacion;

    @OneToMany(
            mappedBy = "pabellon",
            fetch = FetchType.LAZY
    )
    private Set<Aula> aulas;

    public Pabellon(Integer id, Double mts2, String nombre, Direccion direccion) {
        this.id = id;
        this.mts2 = mts2;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    @PrePersist
    private void antesDePersistir(){
        this.fechaAlta = LocalDateTime.now();
    }

    @PreUpdate
    private void antesDeUpdate(){
        this.fechaModificacion = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pabellon pabellon = (Pabellon) o;
        return id.equals(pabellon.id) && nombre.equals(pabellon.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
}
