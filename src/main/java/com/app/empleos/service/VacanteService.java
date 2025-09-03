package com.app.empleos.service;

import com.app.empleos.entity.Vacante;
import com.app.empleos.repository.EmpresaRepository;
import com.app.empleos.repository.UsuarioRepository;
import com.app.empleos.repository.VacanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacanteService {

    @Autowired
    VacanteRepository vacanteRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    public void registrarVacante(Vacante vacante, Authentication authentication){
        String email = authentication.getName();
        vacante.setEmpresa(empresaRepository.findByemail(email));
        vacanteRepository.save(vacante);
    }

    public Vacante obtenerPorIdYAgregarEmpresa(Long id, Authentication authentication){
        String email = authentication.getName();
        Vacante vacante = vacanteRepository.findById(id).orElseThrow();
        System.out.println(email);
        vacante.setEmpresa(empresaRepository.findByemail(email));
        return vacante;
    }

    public void editarVacante(Vacante vacante){
        vacanteRepository.save(vacante);
    }

    public void eliminarVacante(Long id){vacanteRepository.deleteById(id);}

    public List<Vacante> obtenerTodasLasVacantes(){return vacanteRepository.findAll();}
}
