package com.example.universitybackend.modelo.entidades;

import com.example.universitybackend.modelo.entidades.enumenadores.Pizarron;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Aulas")
public class Aula implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "numero_aula", nullable = false)
    private Integer nroAula;
    @Column(name = "medidas_metros")
    private String medidas;
    @Column(name = "cantidad_pupitres")
    private Integer pupitres;
    @Column(name = "tipo_pizarron")
    @Enumerated(EnumType.STRING)
    private Pizarron pizarron;
    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @ManyToOne(
            optional = true,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(
            name = "pabellon_id",
            foreignKey = @ForeignKey(name = "FK_PABELLON_ID")
    )
    private Pabellon pabellon;

    public Aula(Integer id, Integer nroAula, String medidas, Integer pupitres, Pizarron pizarron) {
        this.id = id;
        this.nroAula = nroAula;
        this.medidas = medidas;
        this.pupitres = pupitres;
        this.pizarron = pizarron;
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
        Aula aula = (Aula) o;
        return id.equals(aula.id) && nroAula.equals(aula.nroAula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nroAula);
    }
}
