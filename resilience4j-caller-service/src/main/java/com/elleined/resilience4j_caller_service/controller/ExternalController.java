package com.elleined.resilience4j_caller_service.controller;

import com.elleined.resilience4j_caller_service.service.ExternalService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class ExternalController {

    private final ExternalService externalService;

    // See application.properties for configuration of this circuit breaker
    @GetMapping("/circuit-breaker")
    public String circuitBreaker() {
        return externalService.circuitBreaker();
    }

    // See application.properties for configuration of this retry
    @GetMapping("/retry")
    public String retry() {
        return externalService.retry();
    }

    // See application.properties for configuration of this rate limiter
    @GetMapping("rate-limiter")
    public String rateLimiter() {
        return externalService.rateLimiter();
    }

    // See application.properties for configuration of this time limiter
    // Remember when you annotate a method with time limiter it should return either void or CompletableFuture
    @GetMapping("/time-limiter")
    public CompletableFuture<String> timeLimiter() {
        return externalService.timeLimiter();
    }

}

