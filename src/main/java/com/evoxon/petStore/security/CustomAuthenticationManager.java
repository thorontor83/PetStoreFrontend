package com.evoxon.petStore.security;

import com.auth0.jwt.algorithms.Algorithm;
import com.evoxon.petStore.domain.customer.CustomUserDetails;
import com.evoxon.petStore.domain.customer.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.Authentication;

import javax.persistence.EntityExistsException;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.evoxon.petStore.security.JWTTokenUtils.SECRET_KEY;

@Component
public class CustomAuthenticationManager extends UsernamePasswordAuthenticationFilter implements AuthenticationManager {

    @Autowired
    private CustomerService customerService;

    @Bean
    protected BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final UserDetails userDetail = customerService.loadUserByUsername(authentication.getName());
        if (!bCryptPasswordEncoder().matches(authentication.getCredentials().toString(), userDetail.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }
        return new UsernamePasswordAuthenticationToken(userDetail.getUsername(), userDetail.getPassword(), userDetail.getAuthorities());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsernameAndPasswordRequest usernameAndPasswordRequest = new ObjectMapper().readValue(request.getInputStream(), UsernameAndPasswordRequest.class);
            String username = usernameAndPasswordRequest.getUsername();
            String password = usernameAndPasswordRequest.getPassword();
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            return this.authenticate(authenticationToken);
        }
        catch (Exception exception){
            throw new AuthenticationException(exception.getLocalizedMessage()) {
                @Override
                public String getMessage() {
                    return super.getMessage();
                }
            };
        }
    }



    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        try{
            CustomUserDetails user = (CustomUserDetails) authResult.getPrincipal();
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
            String access_token = JWTTokenUtils.createAccessToken(request, user, algorithm);
            String refresh_token = JWTTokenUtils.createRefreshToken(request, user, algorithm);
            JWTTokenUtils.sendUserTokens(response, refresh_token, access_token);
        }catch (Exception exception){
            response.setStatus(500);
        }
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException{
        response.setStatus(401);
    }

    @Data
    static
    class UsernameAndPasswordRequest{
        private String username;
        private String password;
    }
}
