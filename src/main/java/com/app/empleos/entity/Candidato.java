package com.app.empleos.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Candidato extends Usuario{

    @NotBlank
    private String documento;

    @NotBlank
    private String nombres;

    @NotBlank
    private String apellidos;
}
