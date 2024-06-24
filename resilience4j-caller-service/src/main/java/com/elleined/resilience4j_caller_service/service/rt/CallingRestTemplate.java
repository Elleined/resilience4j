package com.elleined.resilience4j_caller_service.service.rt;

import com.elleined.resilience4j_caller_service.service.ExternalService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Component
@RequiredArgsConstructor
public class CallingRestTemplate implements ExternalService {
    private final RestTemplate restTemplate;

    @Value("${calling-service.base-url}")
    private String callingServiceBaseURL;

    public String circuitBreaker() {
        String endpoint = callingServiceBaseURL + "/" + "circuit-breaker";
        return restTemplate.getForObject(endpoint, String.class);
    }

    public String retry() {
        String endpoint = callingServiceBaseURL + "/" + "retry";
        return restTemplate.getForObject(endpoint, String.class);
    }

    @Retry(name = "default")
    public String circuitBreakerAndRetry() {
        String endpoint = callingServiceBaseURL + "/" + "circuit-breaker-and-retry";
        return restTemplate.getForObject(endpoint, String.class);
    }
}
