package com.app.empleos.repository;

import com.app.empleos.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    public Empresa findByemail(String email);
}
