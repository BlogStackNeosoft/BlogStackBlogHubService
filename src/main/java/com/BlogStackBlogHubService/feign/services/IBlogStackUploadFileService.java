package com.BlogStackBlogHubService.feign.services;

import com.BlogStackBlogHubService.configs.FeignSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@FeignClient(name = "BlogStackCommonsService" , url = "http://localhost:9091", configuration = FeignSupportConfig.class)
public interface IBlogStackUploadFileService {

    @PutMapping(value = "/v1.0/file-upload/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> uploadFile(@RequestPart(name = "file") MultipartFile file) throws IOException;

}