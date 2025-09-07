package com.app.empleos.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "educaciones")
@Data
public class Educacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEducacion;

    private String nivel;

    private String institucion;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_hoja_de_vida")
    private HojaDeVida hojaDeVida;
}
