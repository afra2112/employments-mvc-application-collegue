package com.app.empleos.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "hojas_de_vida")
public class HojaDeVida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHojaDeVida;

    @OneToMany(mappedBy = "hojaDeVida")
    private List<Educacion> educacion;

    @OneToMany(mappedBy = "hojaDeVida")
    private List<Experiencia> experiencia;

    @ManyToMany
    @JoinTable(
            name = "hojas_de_vida_habilidades",
            joinColumns = @JoinColumn(name = "id_hoja_de_vida"),
            inverseJoinColumns = @JoinColumn(name = "id_habilidad")
    )
    private List<Habilidad> habilidades;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Candidato candidato;
}
