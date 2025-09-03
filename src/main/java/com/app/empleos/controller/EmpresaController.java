package com.app.empleos.controller;

import com.app.empleos.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    public EmpresaService empresaService;

    @GetMapping("/index")
    public String index(Model model, Authentication authentication){
        model.addAttribute("vacantes", empresaService.listarVacantes());
        model.addAttribute("nombreEmpresa", empresaService.obtenerNombreEmpresa(authentication));
        return "empresas";
    }
}
