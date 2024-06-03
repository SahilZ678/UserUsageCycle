package com.usmobileassessment.cycleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CycleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CycleServiceApplication.class, args);
    }

}
