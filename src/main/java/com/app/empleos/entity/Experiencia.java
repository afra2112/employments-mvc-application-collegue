package com.app.empleos.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "experiencias")
@Data
public class Experiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExperiencia;

    private String empresa;

    private String cargo;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_hoja_de_vida")
    private HojaDeVida hojaDeVida;
}
