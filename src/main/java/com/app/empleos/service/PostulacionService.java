package com.app.empleos.service;

import com.app.empleos.entity.Postulacion;
import com.app.empleos.repository.PostulacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostulacionService {

    @Autowired
    PostulacionRepository postulacionRepository;

    public List<Postulacion> obtenerPostulacionesPorEmpresa(Long idEmpresa){
        return postulacionRepository.findByVacante_Empresa_IdUsuario(idEmpresa);
    }

    public List<Postulacion> obtenerPostulacionesPorVacante(Long idVacante){
        return postulacionRepository.findByVacante_IdVacante(idVacante);
    }
}
