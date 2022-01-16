package com.orange.auto.complete;

import com.orange.auto.complete.suggestion.poperties.SuggestionProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableConfigurationProperties(SuggestionProperties.class)
public class CitySuggestionServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(CitySuggestionServiceApplication.class, args);
    }

}
