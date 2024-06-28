package com.elleined.resilience4j_caller_service.service.rt;

import com.elleined.resilience4j_caller_service.service.ExternalService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
@RequiredArgsConstructor
public class CallingRestTemplate implements ExternalService {
    private final RestTemplate restTemplate;

    @Value("${calling-service.base-url}")
    private String callingServiceBaseURL;

    @CircuitBreaker(name = "Default", fallbackMethod = "circuitBreakerFallback")
    public String circuitBreaker() {
        String endpoint = callingServiceBaseURL + "/" + "circuit-breaker";
        return restTemplate.getForObject(endpoint, String.class);
    }

    @Retry(name = "Default", fallbackMethod = "retryFallback")
    public String retry() {
        String endpoint = callingServiceBaseURL + "/" + "retry";
        return restTemplate.getForObject(endpoint, String.class);
    }

    @Retry(name = "Default", fallbackMethod = "retryFallback")
    @CircuitBreaker(name = "Default", fallbackMethod = "circuitBreakerFallback")
    public String circuitBreakerAndRetry() {
        String endpoint = callingServiceBaseURL + "/" + "circuit-breaker-and-retry";
        return restTemplate.getForObject(endpoint, String.class);
    }

    public String circuitBreakerFallback(Throwable throwable) {
        String message = "Circuit breaker fallback called";
        System.out.println(message);
        return message;
    }

    public String retryFallback(Throwable throwable) {
        String message = "Retry fallback called";
        System.out.println(message);
        return message;
    }
}
