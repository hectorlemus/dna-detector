package dev.demo.dnadetector.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "config")
public class ApplicationProperties {

    private String jwtIssuer;
    private String jwtSecretKey;
    private Long jwtExpirationDate;

    public String getJwtIssuer() {
        return jwtIssuer;
    }

    public void setJwtIssuer(String jwtIssuer) {
        this.jwtIssuer = jwtIssuer;
    }

    public String getJwtSecretKey() {
        return jwtSecretKey;
    }

    public void setJwtSecretKey(String jwtSecretKey) {
        this.jwtSecretKey = jwtSecretKey;
    }

    public Long getJwtExpirationDate() {
        return jwtExpirationDate;
    }

    public void setJwtExpirationDate(Long jwtExpirationDate) {
        this.jwtExpirationDate = jwtExpirationDate;
    }

}