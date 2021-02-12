package com.giassi.expenses.rest.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giassi.expenses.rest.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private String secretKey;

    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/login");
        secretKey = getSecretKey();
    }

    public static String getSecretKey() {
        String key = null;
        try (InputStream input = AuthenticationFilter.class.getClassLoader().getResourceAsStream("./application.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            key = prop.getProperty("expenses.jwt.secret.key");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return key;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User creds = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException("Could not read request" + e);
        }
    }

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authentication) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        String username = user.getUsername();

        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + 864_000_000))
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
                .compact();

        logger.info("username = " + username + " token = " + token);

        response.addHeader("Authorization", "Bearer " + token);
    }

}
