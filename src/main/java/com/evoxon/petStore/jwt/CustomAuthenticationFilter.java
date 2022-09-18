package com.evoxon.petStore.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import com.evoxon.petStore.domain.customer.CustomUserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.evoxon.petStore.jwt.TokenUtil.SECRET_KEY;

@AllArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsernameAndPasswordRequest usernameAndPasswordRequest = new ObjectMapper().readValue(request.getInputStream(), UsernameAndPasswordRequest.class);
            String username = usernameAndPasswordRequest.getUsername();
            String password = usernameAndPasswordRequest.getPassword();
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            return authenticationManager.authenticate(authenticationToken);
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
            String access_token = TokenUtil.createAccessToken(request, user, algorithm);
            String refresh_token = TokenUtil.createRefreshToken(request, user, algorithm);
            TokenUtil.sendUserTokens(response, refresh_token, access_token);
        }catch (Exception exception){
            response.setStatus(500);
            HandleResponseError.sendError(response,exception);
        }
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException{
        response.setStatus(401);
        HandleResponseError.sendError(response, exception);
    }

    @Data
    static
    class UsernameAndPasswordRequest{
        private String username;
        private String password;
    }


}
