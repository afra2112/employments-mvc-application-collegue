package com.app.empleos.controller;

import com.app.empleos.entity.TipoVacante;
import com.app.empleos.repository.TipoVacanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-vacantes")
public class TipoVacanteRestController {

    @Autowired
    TipoVacanteRepository repository;

    @GetMapping
    public List<TipoVacante> obtenerTodas(){
        return repository.findAll();
    }
}
