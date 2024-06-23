package com.elleined.resilience4j_caller_service;

import com.elleined.resilience4j_caller_service.client.FeignCallingClient;
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

    private final FeignCallingClient feignCallingClient;

    @GetMapping
    public String callingMethod() {
        String result = feignCallingClient.simpleEndpoint();
        return "Caller service successfully called the method in calling service which has result of: " + result;
    }
}
