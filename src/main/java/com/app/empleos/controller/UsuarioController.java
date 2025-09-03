package com.app.empleos.controller;

import com.app.empleos.config.enums.UsuarioTipoEnum;
import com.app.empleos.entity.Candidato;
import com.app.empleos.entity.Empresa;
import com.app.empleos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registrarCandidato")
    public String mostrarRegistroCandidato(Model model){
        model.addAttribute("candidato", new Candidato());
        return "registroCandidato";
    }

    @GetMapping("/registrarEmpresa")
    public String mostrarRegistroEmpresa(Model model){
        model.addAttribute("empresa", new Empresa());
        return "registroEmpresa";
    }

    @PostMapping("/registrarCandidato")
    public String registrarCandidato(@Valid @ModelAttribute Candidato candidato, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("error" + bindingResult.getAllErrors());
            return "redirect:/usuarios/login";
        }
        candidato.setTipo(UsuarioTipoEnum.CANDIDATO);
        usuarioService.registrarCandidato(candidato);
        return "redirect:/usuarios/login";
    }

    @PostMapping("/registrarEmpresa")
    public String registrarEmpresa(@Valid @ModelAttribute Empresa empresa, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("error");
            return "redirect:/usuarios/login";
        }
        empresa.setTipo(UsuarioTipoEnum.EMPRESA);
        usuarioService.registrarEmpresa(empresa);
        return "redirect:/usuarios/login";
    }
}
