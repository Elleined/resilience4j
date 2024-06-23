package com.elleined.resilience4j_caller_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Resilience4jCallerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Resilience4jCallerServiceApplication.class, args);
	}

}
