package dev.demo.dnadetector.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.demo.dnadetector.configuration.ApplicationProperties;
import dev.demo.dnadetector.entity.Account;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    // JWT
    private final String ISSUER_INFO;
    private final String SUPER_SECRET_KEY;
    private final Long TOKEN_EXPIRATION_TIME;

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(
        final AuthenticationManager authenticationManager,
        final ApplicationProperties applicationProperties
    ) {
        this.authenticationManager = authenticationManager;
        this.ISSUER_INFO = applicationProperties.getJwtIssuer();
        this.SUPER_SECRET_KEY = applicationProperties.getJwtSecretKey();
        this.TOKEN_EXPIRATION_TIME = applicationProperties.getJwtExpirationDate();
    }

    @Override
    public Authentication attemptAuthentication(
        final HttpServletRequest request,
        final HttpServletResponse response
    ) throws AuthenticationException {
        try {
            Account credentials = new ObjectMapper().readValue(request.getInputStream(), Account.class);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getUsername(), credentials.getPassword(), new ArrayList<>()
            );

            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            logger.error("Attempt authentication failed");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final AuthenticationException authException
    ) throws IOException {
        JSONObject message = createMessage("message", authException.getLocalizedMessage());
        addMessageToResponse(response, HttpStatus.UNAUTHORIZED, message);
    }

    @Override
    protected void successfulAuthentication(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final FilterChain chain,
        final Authentication auth
    ) throws IOException {
        try {
            String token = buildToken(auth);
            JSONObject message = createMessage("token", token);
            addMessageToResponse(response, HttpStatus.OK, message);
        } catch (Exception ex) {
            logger.error("JWT: " + ex.getMessage());
            ex.printStackTrace();
            addMessageToResponse(response, HttpStatus.INTERNAL_SERVER_ERROR, createMessage("message", "Try again later"));
        }
    }

    private void addMessageToResponse(
        final HttpServletResponse response,
        final HttpStatus httpStatus,
        final JSONObject message
    ) throws IOException {
        response.setStatus(httpStatus.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().print(message);
        response.getWriter().flush();
    }

    private String buildToken(final Authentication auth) {
        long timeMillis = System.currentTimeMillis();
        Date issuedAt = new Date();
        Date expiration = new Date(timeMillis + TOKEN_EXPIRATION_TIME);
        String username = ((User) auth.getPrincipal()).getUsername();

        return Jwts.builder()
            .setIssuedAt(issuedAt)
            .setIssuer(ISSUER_INFO)
            .setSubject(username)
            .setExpiration(expiration)
            .signWith(SignatureAlgorithm.HS512, SUPER_SECRET_KEY).compact();
    }

    private JSONObject createMessage(final String key, final String value) {
        JSONObject message = new JSONObject();
        message.put(key, value);
        return message;
    }

}