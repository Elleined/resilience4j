package com.elleined.resilience4j_caller_service.controller.rt;

import com.elleined.resilience4j_caller_service.service.rt.CallingRestTemplate;
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
    public String circuitBreaker() {
        return callingRestTemplate.circuitBreaker();
    }

    @GetMapping("/retry")
    public String retry() {
        System.out.println("HI");
        return callingRestTemplate.retry();
    }

    @GetMapping("/circuit-breaker-and-retry")
    public String circuitBreakerAndRetry() {
        return callingRestTemplate.circuitBreakerAndRetry();
    }
}
