package dev.demo.dnadetector;

import dev.demo.dnadetector.configuration.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class DnaDetectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DnaDetectorApplication.class, args);
	}

}
