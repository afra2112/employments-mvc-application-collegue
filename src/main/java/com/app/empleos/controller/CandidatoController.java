package com.app.empleos.controller;

import com.app.empleos.service.VacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/candidatos")
public class CandidatoController {

    @Autowired
    VacanteService vacanteService;

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute(vacanteService.obtenerTodasLasVacantes());
        return "candidatos";
    }
}
