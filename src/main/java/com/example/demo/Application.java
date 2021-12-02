package com.example.demo;

import com.example.demo.service.MainScreen;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
@RequiredArgsConstructor
public class Application implements CommandLineRunner {

    private final MainScreen mainScreen;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mainScreen.mainScreen();
    }
}
