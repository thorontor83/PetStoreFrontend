package com.evoxon.petStore.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.evoxon.petStore.domain.customer.CustomUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TokenUtil {

    public static final String SECRET_KEY = "028428C4E57370EE52DBBF5E5260719CA4AEC2073F19654D6C4F79BF41084394";


    public static String createRefreshToken(HttpServletRequest request, CustomUserDetails user, Algorithm algorithm) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withClaim("customerId",user.getCustomerId())
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .sign(algorithm);
    }

    public static String createAccessToken(HttpServletRequest request, CustomUserDetails user, Algorithm algorithm) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withClaim("customerId",user.getCustomerId())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }

    public static void sendUserTokens(HttpServletResponse response, String refresh_token, String access_token) throws IOException {
        Map<String, String> tokens = new HashMap<String, String>();
        tokens.put("message", "Login Successful");
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }


}
