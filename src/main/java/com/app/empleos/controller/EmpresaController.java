package com.app.empleos.controller;

import com.app.empleos.config.enums.ModalidadEnum;
import com.app.empleos.entity.Candidato;
import com.app.empleos.entity.Empresa;
import com.app.empleos.entity.Postulacion;
import com.app.empleos.entity.Vacante;
import com.app.empleos.service.CandidatoService;
import com.app.empleos.service.EmpresaService;
import com.app.empleos.service.PostulacionService;
import com.app.empleos.service.VacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    public EmpresaService empresaService;

    @Autowired
    public VacanteService vacanteService;

    @Autowired
    public PostulacionService postulacionService;

    @Autowired
    public CandidatoService candidatoService;

    @GetMapping("/index")
    public String index(@RequestParam(required = false) Boolean registrar,
                        @RequestParam(required = false) Boolean editar,
                        @RequestParam(required = false) Long id,
                        Model model,
                        Authentication authentication) {

        model.addAttribute("vacantes", empresaService.listarVacantes());
        model.addAttribute("nombreEmpresa", empresaService.obtenerNombreEmpresa(authentication));
        model.addAttribute("postulacionesTotales", vacanteService.obtenerNumeroPostulacionesTotales());

        if (Boolean.TRUE.equals(registrar)) {
            model.addAttribute("vacante", new Vacante());
            model.addAttribute("modalidades", ModalidadEnum.values());
            model.addAttribute("registroVacante", true);
            model.addAttribute("editarVacante", false);
        } else if (Boolean.TRUE.equals(editar) && id != null) {
            Vacante vacante = vacanteService.obtenerPorId(id).orElseThrow();
            model.addAttribute("vacante", vacante);
            model.addAttribute("modalidades", ModalidadEnum.values());
            model.addAttribute("registroVacante", false);
            model.addAttribute("editarVacante", true);
        } else {
            model.addAttribute("registroVacante", false);
            model.addAttribute("editarVacante", false);
        }

        return "empresas";
    }

    @GetMapping("/candidatos")
    public String mostrarCandidatos(@RequestParam(required = false) Long idVacante, Model model, Authentication authentication){

        Empresa empresa = empresaService.obtenerPorEmail(authentication.getName());

        List<Postulacion> postulaciones;

        if(idVacante != null){
            postulaciones = postulacionService.obtenerPostulacionesPorVacante(idVacante);
        }else {
            postulaciones = postulacionService.obtenerPostulacionesPorEmpresa(empresa.getIdUsuario());
        }

        List<Vacante> vacantes = vacanteService.obtenerPorEmpresa(empresa);

        model.addAttribute("postulaciones", postulaciones);
        model.addAttribute("vacantes", vacantes);

        return "candidatosEmpresa";
    }

    @GetMapping("/candidatos/{id}")
    public String mostrarDetalleCandidatos(@PathVariable(name = "id") Long id, Model model, Authentication authentication){
        Candidato candidato = candidatoService.obtenerPorId(id).orElseThrow();
        model.addAttribute("candidato", candidato);
        return "detalleCandidato";
    }
}
