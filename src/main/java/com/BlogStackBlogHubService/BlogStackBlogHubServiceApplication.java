package com.BlogStackBlogHubService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BlogStackBlogHubServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogStackBlogHubServiceApplication.class, args);
	}

}
