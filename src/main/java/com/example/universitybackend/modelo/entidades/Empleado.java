package com.example.universitybackend.modelo.entidades;

import com.example.universitybackend.modelo.entidades.enumenadores.TipoEmpleado;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "empleados")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Empleado extends Persona{

    private BigDecimal sueldo;
    @Column(name = "tipo_empleado")
    @Enumerated(EnumType.STRING)
    private TipoEmpleado tipoEmpleado;

    @OneToOne(
            optional = true,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "pabellon_id",
            foreignKey = @ForeignKey(name = "FK_PABELLON_ID")
    )
    private Pabellon pabellon;

    public Empleado(Integer id, String nombre, String apellido, String dni, Direccion direccion, BigDecimal sueldo, TipoEmpleado tipoEmpleado) {
        super(id, nombre, apellido, dni, direccion);
        this.sueldo = sueldo;
        this.tipoEmpleado = tipoEmpleado;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\tEmpleado{"+
                "sueldo=" + sueldo +
                ", tipoEmpleado=" + tipoEmpleado +
                '}';
    }
}
