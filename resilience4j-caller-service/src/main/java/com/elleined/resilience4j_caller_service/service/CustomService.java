package com.elleined.resilience4j_caller_service.service;

import java.util.concurrent.CompletableFuture;

public interface CustomService {
    String circuitBreaker();
    String retry();
    String rateLimiter();
    CompletableFuture<String> timeLimiter();

    default String circuitBreakerFallback(Throwable throwable) {
        String message = "Circuit breaker fallback called";
        System.out.println(message);
        return message;
    }

    default String retryFallback(Throwable throwable) {
        String message = "Retry fallback called";
        System.out.println(message);
        return message;
    }

    default String rateLimiterFallback(Throwable throwable) {
        String message = "Rate limiter fallback called";
        System.out.println(message);
        return message;
    }

    default CompletableFuture<String> timeLimiterFallback(Throwable throwable) {
        String message = "Time limiter fallback called";
        System.out.println(message);
        return CompletableFuture.supplyAsync(() -> message);
    }
}
