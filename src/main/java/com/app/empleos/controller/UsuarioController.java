package com.app.empleos.controller;

import com.app.empleos.config.enums.UsuarioTipoEnum;
import com.app.empleos.entity.Candidato;
import com.app.empleos.entity.Empresa;
import com.app.empleos.service.HojaDeVidaService;
import com.app.empleos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    HojaDeVidaService hojaDeVidaService;

    @GetMapping("/403")
    public String error403(){
        return "403";
    }

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
    public String registrarCandidato(@Valid @ModelAttribute Candidato candidato, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(usuarioService.existeCandidatoPorEmailODocumento(candidato.getEmail(), candidato.getDocumento())){
            redirectAttributes.addFlashAttribute("yaExiste", true);
            return "redirect:/usuarios/registrarCandidato";
        }
        if(bindingResult.hasErrors()){
            System.out.println("error" + bindingResult.getAllErrors());
            return "redirect:/usuarios/login";
        }
        candidato.setTipo(UsuarioTipoEnum.CANDIDATO);
        usuarioService.registrarCandidato(candidato);
        hojaDeVidaService.crearHojaDeVidaYAsociarConCandidato(candidato.getIdUsuario());
        redirectAttributes.addFlashAttribute("exitoso", true);
        return "redirect:/usuarios/login";
    }

    @PostMapping("/registrarEmpresa")
    public String registrarEmpresa(@Valid @ModelAttribute Empresa empresa, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(usuarioService.existeEmpresaPorEmail(empresa.getEmail())){
            redirectAttributes.addFlashAttribute("yaExiste", true);
            return "redirect:/usuarios/registrarEmpresa";
        }
        if(bindingResult.hasErrors()){
            System.out.println("error");
            return "redirect:/usuarios/login";
        }
        empresa.setTipo(UsuarioTipoEnum.EMPRESA);
        usuarioService.registrarEmpresa(empresa);
        redirectAttributes.addFlashAttribute("exitoso", true);
        return "redirect:/usuarios/login";
    }
}
