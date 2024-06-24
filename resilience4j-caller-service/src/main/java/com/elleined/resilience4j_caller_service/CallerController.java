package com.elleined.resilience4j_caller_service;

import com.elleined.resilience4j_caller_service.client.CallingFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class CallerController {

    private final CallingFeignClient callingFeignClient;

    @GetMapping("/circuit-breaker")
    public String circuitBreaker() {
        return callingFeignClient.circuitBreaker();
    }

    @GetMapping("/retry")
    public String retry() {
        return callingFeignClient.retry();
    }

    @GetMapping("/circuit-breaker-and-retry")
    public String circuitBreakerAndRetry() {
        return callingFeignClient.circuitBreakerAndRetry();
    }
}
