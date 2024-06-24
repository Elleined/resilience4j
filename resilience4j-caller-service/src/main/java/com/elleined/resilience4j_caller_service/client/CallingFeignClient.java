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

    @GetMapping
    @CircuitBreaker(name = "callingServiceCircuitBreaker", fallbackMethod = "circuitBreakerFallbackMethod")
    String circuitBreaker();

    @GetMapping
    @Retry(name = "callingServiceRetry", fallbackMethod = "retryFallbackMethod")
    String retry();

    @GetMapping
    @CircuitBreaker(name = "callingServiceCircuitBreaker", fallbackMethod = "circuitBreakerFallbackMethod")
    @Retry(name = "callingServiceRetry", fallbackMethod = "retryFallbackMethod")
    String circuitBreakerAndRetry();

    /**
     * Fall back method should have the same signature as of original method and just have added parameter of type Throwable
     * And execute code here for properly handling the fallback method.
     *
     * @param exception is used for the circuit breaker to call the fallback method
     * @return String
     */
    default String circuitBreakerFallbackMethod(Exception exception) {
        return "Circuit breaker fallback method called!";
    }

    default String retryFallbackMethod(Exception exception) {
        return "Retry fallback method called";
    }
}
