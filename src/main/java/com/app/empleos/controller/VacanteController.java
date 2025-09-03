package com.app.empleos.controller;

import com.app.empleos.config.enums.ModalidadEnum;
import com.app.empleos.entity.Vacante;
import com.app.empleos.service.VacanteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empresas/vacantes")
public class VacanteController {

    @Autowired
    VacanteService vacanteService;

    @GetMapping("/registrar")
    public String registrarForm(Model model){
        model.addAttribute("vacante", new Vacante());
        model.addAttribute("modalidades", ModalidadEnum.values());
        model.addAttribute("registroVacante",true);
        return "empresas";
    }

    @PostMapping("/registrar")
    public String registrarVacante(@Valid @ModelAttribute Vacante vacante, BindingResult bindingResult, Authentication authentication){
        if(bindingResult.hasErrors()){
            System.out.println("error en el valid");
            return "empresas";
        }
        vacanteService.registrarVacante(vacante, authentication);
        return "redirect:/empresas/index";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, Authentication authentication){
        model.addAttribute("vacante", vacanteService.obtenerPorIdYAgregarEmpresa(id, authentication));
        model.addAttribute("modalidades", ModalidadEnum.values());
        return "editarVacante";
    }

    @PostMapping("/editar")
    public String editarVacante(@ModelAttribute Vacante vacante){
        vacanteService.editarVacante(vacante);
        return "redirect:/empresas/index";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idVacante") Long id){
        vacanteService.eliminarVacante(id);
        return "redirect:/empresas/index";
    }
}
