package dev.demo.dnadetector.security;

import dev.demo.dnadetector.configuration.ApplicationProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    // Spring Security
    private final String LOGIN_URL = "/login";

    private final UserDetailsService userDetailsService;
    private final ApplicationProperties applicationProperties;

    public WebSecurity(
        @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
        final ApplicationProperties applicationProperties
    ) {
        this.userDetailsService = userDetailsService;
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        /*
          1. The use of cookies is disabled
          2. The CORS setting is activated with the default values
          3. The CSRF filter is deactivated
          4. It is indicated that the login does not require authentication
          5. It is indicated that the rest of the URLs are secured
        */
        httpSecurity
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .cors().and()
            .csrf().disable()
            .authorizeRequests().antMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
            .anyRequest().authenticated().and()
            .addFilter(new JWTAuthenticationFilter(authenticationManager(), applicationProperties))
            .addFilter(new JWTAuthorizationFilter(authenticationManager(), applicationProperties));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // The class that retrieves users and the algorithm to process passwords are defined
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}