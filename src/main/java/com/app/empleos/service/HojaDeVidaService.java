package com.app.empleos.service;

import com.app.empleos.entity.HojaDeVida;
import com.app.empleos.repository.CandidatoRepository;
import com.app.empleos.repository.HojaDeVidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HojaDeVidaService {

    @Autowired
    HojaDeVidaRepository hojaDeVidaRepository;

    @Autowired
    CandidatoRepository candidatoRepository;

    public void crearHojaDeVidaYAsociarConCandidato(Long idCandidato){
        HojaDeVida hojaDeVida = new HojaDeVida();
        hojaDeVida.setCandidato(candidatoRepository.findById(idCandidato).orElseThrow());
        hojaDeVidaRepository.save(hojaDeVida);
    }
}
