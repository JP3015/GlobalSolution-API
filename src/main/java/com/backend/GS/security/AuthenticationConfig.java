package com.backend.GS.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthenticationConfig{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http    
            .httpBasic()
            .and()
            .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/estacionamento").permitAll()
                .antMatchers(HttpMethod.GET, "/estacionamento/**").permitAll()
                .antMatchers(HttpMethod.POST, "/estacionamento").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/estacionamento/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/estacionamento/**").hasRole("ADMIN")
                
                
                .antMatchers(HttpMethod.GET, "/carro").permitAll()
                .antMatchers(HttpMethod.GET, "/carro/**").permitAll()
                .antMatchers(HttpMethod.POST, "/carro").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/carro/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/carro/**").permitAll()
                
                
                .anyRequest().denyAll()
            .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .headers().frameOptions().disable()
        ;

        return http.build();
    }

    @Bean
    public UserDetailsService users(){
        UserDetails user = User.builder()
            .username("admin")
            .password("$2a$12$RVc1Cze5T/Ea6BclNRVwQejWSlaXOfBow8KC3MU0eifsyjfOFWKE2")
            .roles("ADMIN")
        .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
    
}