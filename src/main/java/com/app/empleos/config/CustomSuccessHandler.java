package com.app.empleos.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String rol = authentication.getAuthorities().iterator().next().getAuthority();

        if(rol.equals("EMPRESA")){
            response.sendRedirect("/empresas/index");
        }else{
            System.out.println("DETECTO CANDIDATO");
            response.sendRedirect("/candidatos/index");
        }
    }
}
