package com.evoxon.petStore.security;

import com.evoxon.petStore.jwt.CustomAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.evoxon.petStore.jwt.MyCustomDsl.customDsl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class WebSecurityConfig  {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {


        return http
                .csrf().disable()
                .headers().frameOptions().disable().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/*").permitAll()
                                    .antMatchers("/api/v1/store/order/*").permitAll()
                                    .antMatchers("/api/v1/store/order*").permitAll()
                                    .antMatchers("/api/v1/pet*").permitAll()
                                    .antMatchers("/h2-console/*").permitAll()
                                    .antMatchers(HttpMethod.GET,"/api/v1/index.html").permitAll()
                                    .antMatchers(HttpMethod.GET,"/api/v1/login*").permitAll()
                                    .antMatchers(HttpMethod.POST,"/api/v1/login").permitAll()
                                    .antMatchers(HttpMethod.POST,"/api/v1/logout").permitAll()
                                    .antMatchers(HttpMethod.GET,"/api/v1/token/refresh**").permitAll()
                                    .antMatchers(HttpMethod.POST, "/api/v1/customer").permitAll()
                                    .antMatchers(HttpMethod.POST, "/api/v1/customer/create*").permitAll()
                                    .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
                                    .anyRequest().authenticated().and()
                .logout()
                .logoutSuccessUrl("/api/v1/index.html")
                .logoutUrl("/api/v1/logout").permitAll().and()
                .apply(customDsl()).and()
                .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }


}
