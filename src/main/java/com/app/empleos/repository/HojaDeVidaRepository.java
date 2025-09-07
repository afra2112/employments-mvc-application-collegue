package com.app.empleos.repository;

import com.app.empleos.entity.HojaDeVida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HojaDeVidaRepository extends JpaRepository<HojaDeVida, Long> {
}
