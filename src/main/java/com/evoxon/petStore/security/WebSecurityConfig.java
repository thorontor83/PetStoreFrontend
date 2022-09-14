package com.evoxon.petStore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    private JWTTokenUtils jwtTokenUtils;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                /*CustomAuthenticationManager customAuthenticationFilter = new CustomAuthenticationManager();
                customAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");*/
        http

                .csrf().disable()
                .antMatcher("/api/v1/pet")
                .addFilter(new JWTAuthorizationFilter(new CustomAuthenticationManager(), jwtTokenUtils))
                .authorizeRequests()
                .antMatchers("/api/v1/customer").permitAll()
                .antMatchers("/api/v1/login*").permitAll()
                .anyRequest().permitAll().and()
                .formLogin()
                .loginPage("/api/v1/login.html")
                .and()
                .logout()
                .logoutUrl("/logout").and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }


}

