package com.sender.email.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.sql.DataSource;

@Configuration
public class MyConfig {
    @Autowired
    Environment environment;

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


