package com.app.empleos.service;

import com.app.empleos.entity.Postulacion;
import com.app.empleos.entity.TipoVacante;
import com.app.empleos.entity.Vacante;
import com.app.empleos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VacanteService {

    @Autowired
    VacanteRepository vacanteRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    TipoVacanteRepository tipoVacanteRepository;

    @Autowired
    PostulacionRepository postulacionRepository;

    public Long obtenerNumeroPostulacionesTotales(){
        return postulacionRepository.count();
    }

    public void registrarVacante(Vacante vacante, Authentication authentication){
        String email = authentication.getName();
        vacante.setEmpresa(empresaRepository.findByemail(email));
        vacante.setFechaCreacion(LocalDateTime.now());
        vacante.getTiposVacantes().forEach(tipoVacante -> System.out.println(tipoVacante.getNombreTipoVacante()));
        vacanteRepository.save(vacante);
    }

    public Vacante obtenerPorIdYAgregarEmpresa(Long id, Authentication authentication){
        String email = authentication.getName();
        Vacante vacante = vacanteRepository.findById(id).orElseThrow();
        System.out.println(email);
        vacante.setEmpresa(empresaRepository.findByemail(email));
        return vacante;
    }

    public List<TipoVacante> obtenerCategorias() {
        return tipoVacanteRepository.findAll();
    }

    public void editarVacante(Vacante vacante){
        vacanteRepository.save(vacante);
    }

    public void eliminarVacante(Long id){vacanteRepository.deleteById(id);}

    public List<Vacante> obtenerTodasLasVacantes(){return vacanteRepository.findAll();}

    public Optional<Vacante> obtenerPorId(Long id){return vacanteRepository.findById(id);}
}
