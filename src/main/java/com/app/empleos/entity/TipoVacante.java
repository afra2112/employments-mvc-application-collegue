package com.app.empleos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tipo_vacante")
@Data
public class TipoVacante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoVacante;

    private String nombreTipoVacante;

    @ManyToMany(mappedBy = "tiposVacantes")
    @JsonIgnore
    private List<Vacante> vacantes;
}