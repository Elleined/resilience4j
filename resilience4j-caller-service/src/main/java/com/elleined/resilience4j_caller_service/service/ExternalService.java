package com.elleined.resilience4j_caller_service.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;


@Slf4j
@Service
@RequiredArgsConstructor
public class ExternalService implements CustomService {
    private final RestTemplate restTemplate;

    @Value("${calling-service.base-url}")
    private String callingServiceBaseURL;

    @Override
    @CircuitBreaker(name = "default", fallbackMethod = "circuitBreakerFallback")
    public String circuitBreaker() {
        String endpoint = callingServiceBaseURL + "/" + "circuit-breaker";
        return restTemplate.getForObject(endpoint, String.class);
    }

    @Override
    @Retry(name = "default", fallbackMethod = "retryFallback")
    public String retry() {
        String endpoint = callingServiceBaseURL + "/" + "retry";
        return restTemplate.getForObject(endpoint, String.class);
    }

    @Override
    @RateLimiter(name = "default", fallbackMethod = "rateLimiterFallback")
    public String rateLimiter() {
        return "You successfully called this endpoint with rate limiter";
    }

    @Override
    @TimeLimiter(name = "default", fallbackMethod = "timeLimiterFallback")
    public CompletableFuture<String> timeLimiter() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000); // Remove this if you want this method to be called properly
            } catch (InterruptedException ignored) {}
            return "You successfully called the time limiter endpoint";
        });
    }
}
