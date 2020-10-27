package com.paskar.email.application.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }

    @Bean
    public JdbcTemplateAutoConfiguration getJdbcTemplate() {
        return new JdbcTemplateAutoConfiguration();
    }
}
