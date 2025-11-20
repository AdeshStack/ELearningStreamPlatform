package com.course.service.course_service.external;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service") // Eureka service id
public interface UserClient {

    @GetMapping("/api/user/{userId}") // adjust path to your user-service API
    UserDto getUserById(@PathVariable("userId") Long id);

    record UserDto(Long id, String name, String email) {}
}

