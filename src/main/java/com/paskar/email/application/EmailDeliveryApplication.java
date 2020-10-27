package com.paskar.email.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(proxyBeanMethods = false)
@EnableScheduling
public class EmailDeliveryApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmailDeliveryApplication.class, args);
    }
}
