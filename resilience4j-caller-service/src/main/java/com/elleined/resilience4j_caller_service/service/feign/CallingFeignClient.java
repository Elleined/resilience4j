package com.elleined.resilience4j_caller_service.service.feign;

import com.elleined.resilience4j_caller_service.service.ExternalService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        value = "${calling-service.name}", // Just like when you are using the @Value to get value from application.properties
        url = "${calling-service.base-url}", // Just like when you are using the @Value to get value from application.properties
        fallback = CallingFeignClientFallback.class
)
public interface CallingFeignClient extends ExternalService {

    @GetMapping("/circuit-breaker")
    @CircuitBreaker(name = "default")
    String circuitBreaker();

    @GetMapping("/retry")
    @Retry(name = "default")
    String retry();

    @GetMapping("/circuit-breaker-and-retry")
    @CircuitBreaker(name = "default")
    @Retry(name = "default")
    String circuitBreakerAndRetry();
}