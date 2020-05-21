package com.hust.hydroelectric_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

@SpringBootApplication
@EnableScheduling
public class HydroelectricBackendApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(HydroelectricBackendApplication.class, args);
    }

}
