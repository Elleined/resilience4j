package com.elleined.resilience4j_caller_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "${calling-service.name}", url = "${calling-service.base-url}")
public interface FeignCallingClient {

    @GetMapping
    String simpleEndpoint();
}
