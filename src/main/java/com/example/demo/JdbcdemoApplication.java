package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
@RequiredArgsConstructor
public class JdbcdemoApplication implements CommandLineRunner {

    private final ConsoleReadWrite consoleReadWrite;

    public static void main(String[] args) {
        SpringApplication.run(JdbcdemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        consoleReadWrite.start();
    }

}
