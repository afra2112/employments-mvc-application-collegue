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

import java.util.List;

@Controller
@RequestMapping("/empresas/vacantes")
public class VacanteController {

    @Autowired
    VacanteService vacanteService;

    @PostMapping("/registrar")
    public String registrarVacante(@Valid @ModelAttribute Vacante vacante, BindingResult bindingResult, Authentication authentication){
        if(bindingResult.hasErrors()){
            System.out.println("error en el valid");
            return "empresas";
        }
        vacanteService.registrarVacante(vacante, authentication);
        return "redirect:/empresas/index";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute Vacante vacante,
                         @RequestParam(required = false, name = "tiposVacantes") List<Long> tiposVacantesIds,
                         Authentication authentication) {
        vacanteService.editarVacante(vacante, tiposVacantesIds, authentication);
        return "redirect:/empresas/index";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idVacante") Long id){
        vacanteService.eliminarVacante(id);
        return "redirect:/empresas/index";
    }

    @GetMapping("/postulaciones/{id}")
    public String verPostulaciones(@PathVariable(name = "id") Long id, Model model){
        Vacante vacante = vacanteService.obtenerPorId(id).orElseThrow();
        model.addAttribute("vacante", vacante);
        return "postulacionesVacante";
    }
}
