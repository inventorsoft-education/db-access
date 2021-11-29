package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class CustomConfig {

    @Bean
    public Scanner createScanner() {
        return new Scanner(System.in);
    }
}

