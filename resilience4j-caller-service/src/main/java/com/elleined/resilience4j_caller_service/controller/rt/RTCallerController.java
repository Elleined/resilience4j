package com.elleined.resilience4j_caller_service.controller.rt;

import com.elleined.resilience4j_caller_service.service.rt.CallingRestTemplate;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/rt")
@RequiredArgsConstructor
@Slf4j
public class RTCallerController {

    private final CallingRestTemplate callingRestTemplate;

    // See application.properties for configuration of this circuit breaker
    @GetMapping("/circuit-breaker")
    public String circuitBreaker() {
        return callingRestTemplate.circuitBreaker();
    }

    // See application.properties for configuration of this retry
    @GetMapping("/retry")
    public String retry() {
        System.out.println("HI");
        return callingRestTemplate.retry();
    }

    @GetMapping("/circuit-breaker-and-retry")
    public String circuitBreakerAndRetry() {
        return callingRestTemplate.circuitBreakerAndRetry();
    }

    // See application.properties for configuration of this rate limiter
    @GetMapping("rate-limiter")
    @RateLimiter(name = "default", fallbackMethod = "rateLimiterFallback")
    public String rateLimiter() {
        return "You successfully called this endpoint with rate limiter";
    }

    public String rateLimiterFallback(Throwable throwable) {
        String message = "Rate limiter fallback called";
        System.out.println(message);
        return message;
    }

    // See application.properties for configuration of this time limiter
    // Remember when you annotate a method with time limiter it should return either void or CompletableFuture
    @GetMapping("time-limiter")
    @TimeLimiter(name = "default", fallbackMethod = "timeLimiterFallback")
    public CompletableFuture<String> timeLimiter() throws InterruptedException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(5000); // Remove this if you want this method to be called properly
            } catch (InterruptedException ignored) {}
            return "You successfully called the time limiter endpoint";
        });
    }

    public CompletableFuture<String> timeLimiterFallback(Throwable throwable) {
        String message = "Time limiter fallback called";
        System.out.println(message);
        return CompletableFuture.supplyAsync(() -> message);
    }
}

