package com.app.empleos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

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

    @OneToMany(mappedBy = "candidato")
    private List<Postulacion> postulaciones;

    @ManyToMany
    @JoinTable(
            name = "candidatos_habilidades",
            joinColumns = @JoinColumn(name = "id_habilidad"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario")
    )
    private List<Habilidad> habilidades;
}
