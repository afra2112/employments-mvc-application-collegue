package com.app.empleos.repository;

import com.app.empleos.entity.Candidato;
import com.app.empleos.entity.Empresa;
import com.app.empleos.entity.Postulacion;
import com.app.empleos.entity.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostulacionRepository extends JpaRepository<Postulacion, Long> {
    List<Postulacion> findByCandidato(Candidato candidato);

    boolean existsByCandidatoAndVacante(Candidato candidato, Vacante vacante);
}
