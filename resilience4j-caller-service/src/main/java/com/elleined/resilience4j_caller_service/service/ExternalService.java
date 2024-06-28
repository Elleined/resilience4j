package com.elleined.resilience4j_caller_service.service;

public interface ExternalService {
    String circuitBreaker();
    String retry();
    String circuitBreakerAndRetry();
}
