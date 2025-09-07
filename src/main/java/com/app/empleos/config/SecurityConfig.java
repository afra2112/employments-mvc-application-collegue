package com.app.empleos.config;

import com.app.empleos.implement.UserDetailServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    public UserDetailServiceImplement userDetailServiceImplement;

    @Autowired
    public CustomSuccessHandler successHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .authorizeHttpRequests(auth -> {
                   auth.requestMatchers("/usuarios/**").permitAll();
                   auth.requestMatchers("/empresas/**").hasAuthority("EMPRESA");
                   auth.requestMatchers("/candidatos/**").hasAuthority("CANDIDATO");
                   auth.anyRequest().authenticated();
                })
                .formLogin(login -> {
                    login.loginPage("/usuarios/login");
                    login.loginProcessingUrl("/login");
                    login.successHandler(successHandler);
                    login.failureUrl("/usuarios/login?error=true");
                })
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/usuarios/login?logout=true")
                        .permitAll()
                )
                .exceptionHandling(ex -> {
                    ex.accessDeniedPage("/usuarios/403");
                })
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(userDetailServiceImplement)
                .passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
