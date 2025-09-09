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

    List<Postulacion> findByVacante_Empresa_IdUsuario(Long idUsuario); //el idusuario es el id de la empresa xdd

    boolean existsByCandidatoAndVacante(Candidato candidato, Vacante vacante);

    List<Postulacion> findByVacante_IdVacante(Long idVacante);
}
