package com.lelek.dbAccess;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@Slf4j
@SpringBootApplication
public class WebApplication {

    public static final File FILE = new File("src/main/resources/data_base.json");

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
