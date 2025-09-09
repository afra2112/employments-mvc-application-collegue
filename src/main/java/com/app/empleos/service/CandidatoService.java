package com.app.empleos.service;

import com.app.empleos.entity.*;
import com.app.empleos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidatoService {

    @Autowired
    EducacionRepository educacionRepository;

    @Autowired
    ExperienciaRepository experienciaRepository;

    @Autowired
    HabilidadRepository habilidadRepository;

    @Autowired
    CandidatoRepository candidatoRepository;

    @Autowired
    PostulacionRepository postulacionRepository;

    @Autowired
    HojaDeVidaRepository hojaDeVidaRepository;

    public String obtenerNombrePorEmail(String email){
        return candidatoRepository.findByemail(email).getNombres();
    }

    public Optional<Candidato> obtenerPorId(Long id){
        return candidatoRepository.findById(id);
    }

    public void hacerPostulacion(Postulacion postulacion){
        postulacionRepository.save(postulacion);
    }
    public Candidato obtenerPorEmail(String email){
        return candidatoRepository.findByemail(email);
    }

    public List<Postulacion> obtenerPostulacionesPorCandidato(Candidato candidato){
        return postulacionRepository.findByCandidato(candidato);
    }

    public void registrarEducacion(Educacion educacion){
        educacionRepository.save(educacion);
    }

    public void registrarExperiencia(Experiencia experiencia){
        experienciaRepository.save(experiencia);
    }

    public void registrarHabilidad(Long idHabilidad, String email){
        Candidato candidato = candidatoRepository.findByemail(email);
        HojaDeVida hv = candidato.getHojaDeVida();
        Habilidad habilidad = habilidadRepository.findById(idHabilidad).orElseThrow();

        if(!hv.getHabilidades().contains(habilidad)){
            hv.getHabilidades().add(habilidad);
            hojaDeVidaRepository.save(hv);
        }
    }

    public boolean existePostulacionPorVacanteYCandidato(Candidato candidato, Vacante vacante){
        return postulacionRepository.existsByCandidatoAndVacante(candidato, vacante);
    }

    public void eliminarEducacion(Long id){
        Educacion educacion = educacionRepository.findById(id).orElseThrow();
        educacionRepository.delete(educacion);
    }

    public void eliminarExperiencia(Long id){
        Experiencia experiencia = experienciaRepository.findById(id).orElseThrow();
        experienciaRepository.delete(experiencia);
    }

    public void eliminarHabilidad(Long idHabilidad, String email){
        Candidato candidato = candidatoRepository.findByemail(email);
        HojaDeVida hv = candidato.getHojaDeVida();
        Habilidad habilidad = habilidadRepository.findById(idHabilidad).orElseThrow();

        hv.getHabilidades().remove(habilidad);
        hojaDeVidaRepository.save(hv);
    }

    public void editarCandidato(Candidato candidato){
        candidatoRepository.save(candidato);
    }

}
