package com.app.empleos.entity;

import com.app.empleos.config.enums.EstadoEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "postulaciones")
public class Postulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_candidato")
    private Candidato candidato;

    @ManyToOne
    @JoinColumn(name = "id_vacante")
    private Vacante vacante;

    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    private EstadoEnum estado;
}
