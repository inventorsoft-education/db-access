package com.example.jdbcdemo;

import com.example.jdbcdemo.component.ConsoleReadWrite;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class JdbcdemoApplication implements CommandLineRunner {

    private ConsoleReadWrite consoleReadWrite;

    public static void main(String[] args) {
        SpringApplication.run(JdbcdemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        consoleReadWrite.start();
    }

}
