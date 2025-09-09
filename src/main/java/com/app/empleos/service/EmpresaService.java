package com.app.empleos.service;

import com.app.empleos.entity.Empresa;
import com.app.empleos.entity.Vacante;
import com.app.empleos.repository.EmpresaRepository;
import com.app.empleos.repository.VacanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    public VacanteRepository vacanteRepository;

    @Autowired
    public EmpresaRepository empresaRepository;

    public List<Vacante> listarVacantes(){
        return vacanteRepository.findAll();
    }

    public String obtenerNombreEmpresa(Authentication authentication){
        return empresaRepository.findByemail(authentication.getName()).getNombre();
    }

    public Empresa obtenerPorEmail(String email){
        return empresaRepository.findByemail(email);
    }
}
