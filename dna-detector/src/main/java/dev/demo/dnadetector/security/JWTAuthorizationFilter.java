package dev.demo.dnadetector.security;

import dev.demo.dnadetector.configuration.ApplicationProperties;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    // Spring Security
    private final String HEADER_AUTHORIZATION_KEY = "Authorization";
    private final String TOKEN_BEARER_PREFIX = "Bearer ";

    // JWT
    private final String SUPER_SECRET_KEY;

    public JWTAuthorizationFilter(
        final AuthenticationManager authManager,
        final ApplicationProperties applicationProperties
    ) {
        super(authManager);
        this.SUPER_SECRET_KEY = applicationProperties.getJwtSecretKey();
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest req,
        HttpServletResponse res,
        FilterChain chain
    ) throws IOException, ServletException {
        try {
            validateHeader(req);

            UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(req, res);

        } catch (Exception ex) {
            logger.error("Authorization filter error: " + ex.getMessage());
            chain.doFilter(req, res);
        }
    }

    private void validateHeader(final HttpServletRequest req) throws Exception {
        String header = req.getHeader(HEADER_AUTHORIZATION_KEY);
        if (header == null || !header.startsWith(TOKEN_BEARER_PREFIX)) {
            throw new Exception();
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_AUTHORIZATION_KEY);
        return token != null ? getUsernamePasswordAuthenticationToken(getTokenUser(token)) : null;
    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(final String user) {
        return user != null ?
            new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>()) : null;
    }

    private String getTokenUser(final String token) {
        return Jwts.parser()
            .setSigningKey(SUPER_SECRET_KEY)
            .parseClaimsJws(token.replace(TOKEN_BEARER_PREFIX, ""))
            .getBody()
            .getSubject();
    }
}