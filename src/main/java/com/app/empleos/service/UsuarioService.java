package com.app.empleos.service;

import com.app.empleos.entity.Candidato;
import com.app.empleos.entity.Empresa;
import com.app.empleos.repository.CandidatoRepository;
import com.app.empleos.repository.EmpresaRepository;
import com.app.empleos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CandidatoRepository candidatoRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void registrarCandidato(Candidato candidato){
        candidato.setPassword(passwordEncoder.encode(candidato.getPassword()));
        candidatoRepository.save(candidato);
    }

    public void registrarEmpresa(Empresa empresa){
        empresa.setPassword(passwordEncoder.encode(empresa.getPassword()));
        empresaRepository.save(empresa);
    }

    public boolean existeCandidatoPorEmailODocumento(String email, String documento){
        return candidatoRepository.existsByEmailOrDocumento(email, documento);
    }

    public boolean existeEmpresaPorEmail(String email){
        return empresaRepository.existsByEmail(email);
    }
}
