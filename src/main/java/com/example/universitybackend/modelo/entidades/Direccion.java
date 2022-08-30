package com.example.universitybackend.modelo.entidades;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class Direccion implements Serializable {

    private String calle;
    private String numero;
    private String zip;
    private String departamento;
    private String piso;
    private String localidad;


    public Direccion(String calle, String numero, String zip, String departamento, String piso, String localidad) {
        this.calle = calle;
        this.numero = numero;
        this.zip = zip;
        this.departamento = departamento;
        this.piso = piso;
        this.localidad = localidad;
    }
}
