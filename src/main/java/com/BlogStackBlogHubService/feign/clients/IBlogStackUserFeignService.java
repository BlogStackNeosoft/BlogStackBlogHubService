package com.BlogStackBlogHubService.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "userManagement" , url = "http://localhost:9095/v1.0/user")
public interface IBlogStackUserFeignService {
    @GetMapping(value = "/user/{user_Id}")
    public ResponseEntity<?> fetchUserByUserId(@PathVariable(value = "user_Id") String userId);

}
