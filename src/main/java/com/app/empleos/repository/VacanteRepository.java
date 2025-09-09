package com.app.empleos.repository;

import com.app.empleos.entity.Empresa;
import com.app.empleos.entity.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacanteRepository extends JpaRepository<Vacante, Long> {
    List<Vacante> findByEmpresa(Empresa empresa);
}
