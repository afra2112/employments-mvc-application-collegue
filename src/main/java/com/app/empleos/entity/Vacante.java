package com.app.empleos.entity;

import com.app.empleos.config.enums.ModalidadEnum;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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

    @Lob
    @Column(columnDefinition = "TEXT")
    private String detalles;

    private LocalDateTime fechaCreacion;

    @Nullable
    private LocalDateTime fechaFin;

    private String jornada;

    @Enumerated(EnumType.STRING)
    private ModalidadEnum modalidad;

    @OneToMany(mappedBy = "vacante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Postulacion> postulaciones;

    @ManyToMany
    @JoinTable(
            name = "vacantes_tipo_Vacantes",
            joinColumns = @JoinColumn(name = "id_vacante"),
            inverseJoinColumns = @JoinColumn(name = "id_tipo_vacante")
    )
    private List<TipoVacante> tiposVacantes;
}
