package com.evoxon.petStore.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final JWTTokenUtils jwtTokenUtils;

    public JWTAuthorizationFilter(AuthenticationManager authManager, JWTTokenUtils jwtTokenUtils) {
        super(authManager);
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        // retrieve request authorization header
        final String authorizationHeader = req.getHeader("Authorization");

        // authorization header must be set and start with Bearer
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            // decode JWT token
            final JWTTokenPayload jwtTokenPayload = jwtTokenUtils.decodeToken(authorizationHeader);

            // if user e-mail has been retrieved correctly from the token and if user is not already authenticated
            if (jwtTokenPayload.getUsername() != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // authenticate user
                final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtTokenPayload.getUsername(),  Collections.singletonList(jwtTokenPayload.getCustomerRole()));

                // set authentication in security context holder
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } else {
                System.out.println("Valid token contains no user info");
            }
        }
        // no token specified
        else {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        // pass request down the chain, except for OPTIONS requests
        if (!"OPTIONS".equalsIgnoreCase(req.getMethod())) {
            chain.doFilter(req, res);
        }

    }
}
