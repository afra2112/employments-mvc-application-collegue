package com.app.empleos.repository;

import com.app.empleos.entity.TipoVacante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoVacanteRepository extends JpaRepository<TipoVacante, Long> {
}
