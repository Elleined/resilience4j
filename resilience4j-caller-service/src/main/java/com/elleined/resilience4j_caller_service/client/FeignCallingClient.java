package com.elleined.resilience4j_caller_service.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "${calling-service.name}", // Just like when you are using the @Value to get value from application.properties
        url = "${calling-service.base-url}" // Just like when you are using the @Value to get value from application.properties
)
public interface FeignCallingClient {

    @GetMapping
    @CircuitBreaker(name = "callingServiceCircuitBreaker", fallbackMethod = "simpleEndpointFallbackMethod")
    String simpleEndpoint();

    /**
     * You but you can return anything that you want. For simplicity, we will return String for now.
     * And execute code here for properly handling the fallback method.
     *
     * @param throwable is used for the circuit breaker to call the fallback method
     * @return String
     */
    default String simpleEndpointFallbackMethod(Throwable throwable) {
        return "Circuit breaker fallback method called!";
    }
}
