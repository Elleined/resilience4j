package com.elleined.resilience4j_caller_service.client;

import org.springframework.stereotype.Component;

@Component
public class CallingFeignClientFallback implements CallingFeignClient {

    @Override
    public String circuitBreaker() {
        System.out.println("Circuit breaker fallback method invoked");
        // Execute code here to handle the failed external service operation
        // Or try to throw an exception here and let the @Controller Advice to handle it
        return "Circuit breaker fallback method invoked";
    }

    @Override
    public String retry() {
        System.out.println("Retry fallback method invoked");
        // Execute code here to handle the failed external service operation
        // Or try to throw an exception here and let the @Controller Advice to handle it
        return "Retry fallback method invoked";
    }

    @Override
    public String circuitBreakerAndRetry() {
        System.out.println("Circuit breaker and retry fallback method invoked");
        // Execute code here to handle the failed external service operation
        // Or try to throw an exception here and let the @Controller Advice to handle it
        return "Circuit breaker and retry fallback method invoked";
    }
}
