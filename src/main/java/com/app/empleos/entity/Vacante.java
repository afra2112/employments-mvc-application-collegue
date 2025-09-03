package com.app.empleos.entity;

import com.app.empleos.config.enums.ModalidadEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "vacantes")
public class Vacante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVacante;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    private String titulo;

    private Long salario;

    @Enumerated(EnumType.STRING)
    private ModalidadEnum modalidad;
}
