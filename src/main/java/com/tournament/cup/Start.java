package com.tournament.cup;

import com.tournament.cup.settings.FirstClass;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@AllArgsConstructor
public class Start implements CommandLineRunner {

    private final FirstClass firstClass;

    public static void main(String[] args) {
        SpringApplication.run(Start.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        firstClass.mainScreen();
    }

}
