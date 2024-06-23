package com.elleined.resilience4j_caller_service.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        value = "${calling-service.name}", // Just like when you are using the @Value to get value from application.properties
        url = "${calling-service.base-url}" // Just like when you are using the @Value to get value from application.properties
)
public interface CallingFeignClient {

    @GetMapping
    @CircuitBreaker(name = "simpleEndpointCircuitBreaker", fallbackMethod = "simpleEndpointFallbackMethod")
    String simpleEndpoint();

    /**
     * You but you can return anything that you want. For simplicity, we will return String for now.
     * And execute code here for properly handling the fallback method.
     *
     * @param exception is used for the circuit breaker to call the fallback method
     * @return String
     */
    default String simpleEndpointFallbackMethod(Exception exception) {
        return "Circuit breaker fallback method called!";
    }
}
