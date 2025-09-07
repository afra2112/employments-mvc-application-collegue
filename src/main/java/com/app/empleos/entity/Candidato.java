package com.app.empleos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Candidato extends Usuario{

    @NotBlank
    private String documento;

    @NotBlank
    private String nombres;

    @NotBlank
    private String apellidos;

    @NotBlank
    private String telefono;

    @NotBlank
    private String ciudad;

    @NotNull
    private LocalDate fechaNacimiento;

    @OneToMany(mappedBy = "candidato")
    private List<Postulacion> postulaciones;

    @OneToOne(mappedBy = "candidato")
    private HojaDeVida hojaDeVida;
}
