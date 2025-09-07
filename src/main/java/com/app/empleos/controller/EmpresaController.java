package com.app.empleos.controller;

import com.app.empleos.config.enums.ModalidadEnum;
import com.app.empleos.entity.Vacante;
import com.app.empleos.service.EmpresaService;
import com.app.empleos.service.VacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    public EmpresaService empresaService;

    @Autowired
    public VacanteService vacanteService;

    @GetMapping("/index")
    public String index(@RequestParam(required = false) Boolean registrar, Model model, Authentication authentication){

        if(registrar == null){
            model.addAttribute("vacantes", empresaService.listarVacantes());
            model.addAttribute("nombreEmpresa", empresaService.obtenerNombreEmpresa(authentication));
            model.addAttribute("postulacionesTotales", vacanteService.obtenerNumeroPostulacionesTotales());
        }

        if(Boolean.TRUE.equals(registrar)){
            model.addAttribute("vacantes", empresaService.listarVacantes());
            model.addAttribute("nombreEmpresa", empresaService.obtenerNombreEmpresa(authentication));
            model.addAttribute("vacante", new Vacante());
            model.addAttribute("modalidades", ModalidadEnum.values());
            model.addAttribute("registroVacante",true);
            model.addAttribute("postulacionesTotales", vacanteService.obtenerNumeroPostulacionesTotales());
        }
        return "empresas";
    }
}
