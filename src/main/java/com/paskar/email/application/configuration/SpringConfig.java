package com.paskar.email.application.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration(proxyBeanMethods = false)
public class SpringConfig {
    @Bean
    public ObjectMapper mapper() {
        return new Jackson2ObjectMapperBuilder()
                .indentOutput(true)
                .propertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE)
                .build();
    }
}
