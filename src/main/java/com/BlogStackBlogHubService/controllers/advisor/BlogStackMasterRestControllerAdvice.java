package com.BlogStackBlogHubService.controllers.advisor;


import com.BlogStackBlogHubService.beans.response.ServiceResponseBean;
import com.BlogStackBlogHubService.exceptions.BlogStackCustomException;
import com.BlogStackBlogHubService.exceptions.BlogstackDataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BlogStackMasterRestControllerAdvice {
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    @ExceptionHandler(BlogStackCustomException.class)
    public ServiceResponseBean handleBlogStackCustomException(BlogStackCustomException blogStackCustomException) {
        return ServiceResponseBean.builder()
                .message(blogStackCustomException.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BlogstackDataNotFoundException.class)
    public ServiceResponseBean handleBlogStackDataNotFoundException(BlogstackDataNotFoundException blogStackDataNotFoundException) {
        return ServiceResponseBean.builder()
                .message(blogStackDataNotFoundException.getMessage())
                .build();
    }
}