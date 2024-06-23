package com.elleined.resilience4j_calling_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SimpleController {

    /**
     * This is just a simple endpoint normally you would have complex endpoint here.
     * But for simplicity we will just have a simple endpoint.
     * This method will we called by caller service via feign client.
     * @return String
     */
    @GetMapping
    public String simpleEndpoint() {
        return "Calling service successfully called!";
    }
}
