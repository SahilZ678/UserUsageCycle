package com.usmobileassessment.dailyusageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DailyUsageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailyUsageServiceApplication.class, args);
	}

}
