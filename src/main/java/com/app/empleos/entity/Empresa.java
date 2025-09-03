package com.app.empleos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Empresa extends Usuario{

    @NotBlank
    private String nombre;

    @NotBlank
    private String departamento;

    @NotBlank
    private String ciudad;

    @OneToMany(mappedBy = "empresa")
    private List<Vacante> vacantes;
}
