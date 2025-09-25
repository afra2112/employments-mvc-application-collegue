package com.app.empleos.controller;

import com.app.empleos.config.enums.EstadoEnum;
import com.app.empleos.entity.*;
import com.app.empleos.service.CandidatoService;
import com.app.empleos.service.ConsultasMulticriterioVacantes;
import com.app.empleos.service.VacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/candidatos")
public class CandidatoController {

    @Autowired
    VacanteService vacanteService;

    @Autowired
    CandidatoService candidatoService;

    @Autowired
    ConsultasMulticriterioVacantes consultasMulticriterioVacantes;

    @GetMapping("/index")
    public String index(
            @RequestParam(required = false, name = "nombreEmpresa") String nombreEmpresa,
            @RequestParam(required = false, name = "tituloVacante") String tituloVacante,
            @RequestParam(required = false, name = "modalidad") String modalidad,
            @RequestParam(required = false, name = "ordenarPor") String ordernarPor,
            Model model,
            Authentication authentication){

        System.out.println("RESULTADO: " + nombreEmpresa + tituloVacante + modalidad + ordernarPor);

        List<Vacante> vacantes = consultasMulticriterioVacantes.filtrarVacantes(nombreEmpresa, tituloVacante, modalidad, ordernarPor);
        model.addAttribute("nombreCandidato",candidatoService.obtenerNombrePorEmail(authentication.getName()));
        model.addAttribute("vacantes", vacantes);
        model.addAttribute("idCandidato", candidatoService.obtenerPorEmail(authentication.getName()).getIdUsuario());
        return "candidatos";
    }

    @GetMapping("/mis-postulaciones")
    public String mostrarPostulaciones(Model model, Authentication authentication){
        Candidato candidato = candidatoService.obtenerPorEmail(authentication.getName());
        model.addAttribute("postulaciones", candidatoService.obtenerPostulacionesPorCandidato(candidato));
        return "misPostulaciones";
    }

    @GetMapping("/ver-detalle")
    public String verDetalleVacante(@RequestParam(value = "id") Long id, Principal principal, Model model){
        Vacante vacante = vacanteService.obtenerPorId(id).orElseThrow();
        String email = principal.getName();
        model.addAttribute("idCandidato", candidatoService.obtenerPorEmail(email).getIdUsuario());
        model.addAttribute("vacante", vacante);
        return "detalleVacante";
    }

    @GetMapping("/mis-datos")
    public String mostrarDatosCandidato(Authentication authentication, Model model){
        model.addAttribute("candidato", candidatoService.obtenerPorEmail(authentication.getName()));
        return "misDatos";
    }

    @GetMapping("/mis-datos/eliminar-educacion")
    public String eliminarEducacion(@RequestParam(value = "id") Long idEducacion){
        candidatoService.eliminarEducacion(idEducacion);
        return "redirect:/candidatos/mis-datos";
    }

    @GetMapping("/mis-datos/eliminar-experiencia")
    public String eliminarExperiencia(@RequestParam(value = "id") Long idExperiencia){
        candidatoService.eliminarExperiencia(idExperiencia);
        return "redirect:/candidatos/mis-datos";
    }

    @GetMapping("/mis-datos/eliminar-habilidad")
    public String eliminarHabilidad(@RequestParam(value = "id") Long idHabilidad, Authentication authentication){
        candidatoService.eliminarHabilidad(idHabilidad, authentication.getName());
        return "redirect:/candidatos/mis-datos";
    }

    @PostMapping("/mis-datos/registrar-educacion")
    public String registrarEducacion(
            @RequestParam String nivel,
            @RequestParam String institucion,
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin,
            @RequestParam String nombre,
            Authentication authentication
            ){
        Educacion educacion = new Educacion();
        educacion.setNivel(nivel);
        educacion.setInstitucion(institucion);
        educacion.setFechaFin(fechaFin);
        educacion.setFechaInicio(fechaInicio);
        educacion.setNombre(nombre);
        educacion.setHojaDeVida(candidatoService.obtenerPorEmail(authentication.getName()).getHojaDeVida());
        candidatoService.registrarEducacion(educacion);
        return "redirect:/candidatos/mis-datos";
    }

    @PostMapping("/mis-datos/registrar-experiencia")
    public String registrarExperiencia(
            @RequestParam String cargo,
            @RequestParam String empresa,
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin,
            @RequestParam String descripcion,
            Authentication authentication
    ){
        Experiencia experiencia = new Experiencia();
        experiencia.setCargo(cargo);
        experiencia.setEmpresa(empresa);
        experiencia.setFechaFin(fechaFin);
        experiencia.setFechaInicio(fechaInicio);
        experiencia.setDescripcion(descripcion);
        experiencia.setHojaDeVida(candidatoService.obtenerPorEmail(authentication.getName()).getHojaDeVida());
        candidatoService.registrarExperiencia(experiencia);
        return "redirect:/candidatos/mis-datos";
    }

    @PostMapping("/mis-datos/registrar-habilidad")
    public String registrarHabilidad(@RequestParam Long idHabilidad, Authentication authentication){
        candidatoService.registrarHabilidad(idHabilidad, authentication.getName());
        return "redirect:/candidatos/mis-datos";
    }

    @PostMapping("/mis-datos/editar-datos")
    public String editarDatos(@ModelAttribute Candidato candidato){
        candidatoService.editarCandidato(candidato);
        return "redirect:/candidatos/mis-datos";
    }

    @PostMapping("/postularme")
    public String hacerPostulacion(@RequestParam Long idVacante, @RequestParam Long idCandidato, RedirectAttributes redirectAttributes){
        Vacante vacante = vacanteService.obtenerPorId(idVacante).orElseThrow();
        Candidato candidato = candidatoService.obtenerPorId(idCandidato).orElseThrow();

        if (!candidatoService.existePostulacionPorVacanteYCandidato(candidato, vacante)){
            Postulacion postulacion = new Postulacion();
            postulacion.setCandidato(candidato);
            postulacion.setVacante(vacante);
            postulacion.setFecha(LocalDate.now());
            postulacion.setEstado(EstadoEnum.APLICADO);
            candidatoService.hacerPostulacion(postulacion);
            redirectAttributes.addFlashAttribute("aplicado", true);
            return "redirect:/candidatos/index";
        }
        redirectAttributes.addFlashAttribute("yaExiste", true);
        return "redirect:/candidatos/ver-detalle?id="+ idVacante;
    }


}
