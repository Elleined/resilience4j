package com.elleined.resilience4j_caller_service.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        value = "${calling-service.name}", // Just like when you are using the @Value to get value from application.properties
        url = "${calling-service.base-url}" // Just like when you are using the @Value to get value from application.properties
)
public interface CallingFeignClient {

    @GetMapping("/circuit-breaker")
    @CircuitBreaker(name = "defaultCircuitBreaker", fallbackMethod = "defaultCircuitBreakerFallback")
    String circuitBreaker();

    @GetMapping("/retry")
    @Retry(name = "defaultRetry", fallbackMethod = "defaultRetryFallback")
    String retry();

    @GetMapping("/circuit-breaker-and-retry")
    @CircuitBreaker(name = "defaultCircuitBreaker", fallbackMethod = "defaultCircuitBreakerFallback")
    @Retry(name = "defaultRetry", fallbackMethod = "defaultRetryFallback")
    String circuitBreakerAndRetry();

    /**
     * Fall back method should have the same signature as of original method and just have added parameter of type Throwable
     * And execute code here for properly handling the fallback method.
     *
     * @param exception is used for the circuit breaker to call the fallback method
     * @return String
     */
    default String defaultCircuitBreakerFallback(Exception exception) {
        System.out.println("Circuit breaker fallback method called");
        return "Circuit breaker fallback method called!";
    }

    default String defaultRetryFallback(Exception exception) {
        System.out.println("Retry fallback method called");
        return "Retry fallback method called";
    }
}
