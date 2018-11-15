package com.sender.email.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

@Configuration
@EnableSwagger2
public class ApplicationConfig {
    @Autowired
    Environment environment;

    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Email Sender API",
                "Controllers' description of EmailSender API",
                "1.0.1",
                "Terms of service",
                new Contact("Oleksiy Lanchynetskyi", "https://scripty.com", "alex3003000@gmail.com"),
                "Some License   ",
                "License URL");
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("url"));
        dataSource.setUsername(environment.getProperty("dbuser"));
        dataSource.setPassword(environment.getProperty("dbpassword"));
        dataSource.setDriverClassName(environment.getProperty("driver"));
        return dataSource;
    }

    @Bean
    public ThreadPoolTaskScheduler getThread(){
        ThreadPoolTaskScheduler thread = new ThreadPoolTaskScheduler();
        thread.setPoolSize(3);
        thread.setThreadNamePrefix("Task Scheduler");
        return thread;
    }

}


