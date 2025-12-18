package com.api.gateway.cloud_gateway.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

    @GetMapping("/userServiceFallBack")
    public String userServiceFallBackMethod() {
        return "User Service is taking longer than expected. Please try again later.";
    }

    @GetMapping("/videoServiceFallBack")
    public String videoServiceFallBackMethod() {
        return "Video Service is taking longer than expected. Please try again later.";
    }

    @GetMapping("/courseServiceFallBack")
    public String courseServiceFallBackMethod(){
        return "Course service is taking longer than expected , Please try again later";
    }
    @GetMapping("/cartServiceFallBack")
    public String cartServiceFallBackMethod(){
        return "Cart service is taking longer than expected , Please try again later";
    }
}
