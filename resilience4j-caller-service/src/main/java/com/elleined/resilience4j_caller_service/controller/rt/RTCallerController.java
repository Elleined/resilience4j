package com.elleined.resilience4j_caller_service.controller.rt;

import com.elleined.resilience4j_caller_service.service.feign.CallingFeignClient;
import com.elleined.resilience4j_caller_service.service.rt.CallingRestTemplate;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rt")
@RequiredArgsConstructor
@Slf4j
public class RTCallerController {

    private final CallingRestTemplate callingRestTemplate;

    @GetMapping("/circuit-breaker")
    @CircuitBreaker(name = "default", fallbackMethod = "circuitBreakerFallback")
    public String circuitBreaker() {
        return callingRestTemplate.circuitBreaker();
    }

    @GetMapping("/retry")
    @Retry(name = "default", fallbackMethod = "retryFallback")
    public String retry() {
        return callingRestTemplate.retry();
    }

    @GetMapping("/circuit-breaker-and-retry")
    @CircuitBreaker(name = "default", fallbackMethod = "circuitBreakerFallback")
    public String circuitBreakerAndRetry() {
        return callingRestTemplate.circuitBreakerAndRetry();
    }

    private String circuitBreakerFallback(Exception exception) {
        String message = "Circuit breaker fallback called";
        System.out.println(message);
        return message;
    }

    private String retryFallback(Exception exception) {
        String message = "Retry fallback called";
        System.out.println(message);
        return message;
    }
}
