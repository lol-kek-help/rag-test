package com.example.gigachatdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class GigachatDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GigachatDemoApplication.class, args);
    }
}
