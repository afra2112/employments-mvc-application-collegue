package com.app.empleos.controller;

import com.app.empleos.entity.Habilidad;
import com.app.empleos.entity.TipoVacante;
import com.app.empleos.repository.HabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/habilidades")
public class HabilidadRestController {

    @Autowired
    HabilidadRepository habilidadRepository;

    @GetMapping
    public List<Habilidad> obtenerTodas(){
        return habilidadRepository.findAll();
    }
}
