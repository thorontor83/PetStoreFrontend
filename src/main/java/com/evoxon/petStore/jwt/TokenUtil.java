package com.evoxon.petStore.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.evoxon.petStore.domain.customer.CustomUserDetails;
import com.evoxon.petStore.domain.customer.Customer;
import com.evoxon.petStore.domain.customer.CustomerRole;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
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

    public static Customer decodeAuthHeader(String authHeader){
        String authHeaderClean = authHeader.substring(7);
        String[] chunks = authHeaderClean.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));
        return TokenUtil.toMap(payload);
    }

    public static Customer toMap(String input){
        ObjectMapper mapper = new ObjectMapper();
        TokenInfo tokenInfoData = new TokenInfo();
        try {
            tokenInfoData = mapper.readerFor(TokenInfo.class).readValue(input);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Object tokenUtil;
        return TokenUtil.toCustomer(tokenInfoData);
    }

    public static Customer toCustomer(TokenInfo tokenInfoToTransform){
        Customer customer = new Customer();
        customer.setUsername(tokenInfoToTransform.getSub());
        customer.setId(tokenInfoToTransform.getCustomerId());
        customer.setCustomerRole(CustomerRole.valueOf(tokenInfoToTransform.getRoles()[0]));
        return customer;
    }

    public static Customer getDataFromAccessToken(HttpHeaders httpHeaders) {
        return TokenUtil.decodeAuthHeader(Objects.requireNonNull(httpHeaders.getFirst(HttpHeaders.AUTHORIZATION)));
    }

    public static Customer getDataFromRefreshToken(HttpHeaders httpHeaders) {
        return TokenUtil.decodeAuthHeader(Objects.requireNonNull(httpHeaders.getFirst(HttpHeaders.AUTHORIZATION)));

    }
}
