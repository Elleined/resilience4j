package com.elleined.resilience4j_caller_service;

import com.elleined.resilience4j_caller_service.client.MessageErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class Resilience4jCallerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Resilience4jCallerServiceApplication.class, args);
	}

	@Bean
	public ErrorDecoder errorDecoder() {
		return new MessageErrorDecoder();
	}
}
