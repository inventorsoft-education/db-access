package com.academy.task.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "db")
public class ConnectionProperties {

    String driver;

    String URL;

    String user;

    String pass;

}