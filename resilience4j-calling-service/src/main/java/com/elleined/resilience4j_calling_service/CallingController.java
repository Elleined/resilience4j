package com.elleined.resilience4j_calling_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CallingController {

    /**
     * This is just a simple endpoint normally you would have complex endpoint here.
     * But for simplicity we will just have a simple endpoint.
     * This method will we called by caller service via feign client.
     * @return String
     */
    @GetMapping("/circuit-breaker")
    public String circuitBreaker() {
        return "If you see this message meaning the call is success!";
    }

    @GetMapping("/retry")
    public String retry() {
        return "If you see this message meaning the call is success!";
    }

    @GetMapping("/circuit-breaker-and-retry")
    public String circuitBreakerAndRetry() {
        return "If you see this message meaning the call is success!";
    }
}
