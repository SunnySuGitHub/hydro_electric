package com.hust.hydroelectric_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HydroelectricBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HydroelectricBackendApplication.class, args);
    }

}
