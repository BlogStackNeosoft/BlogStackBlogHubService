package com.BlogStackBlogHubService.controllers;

import com.BlogStackBlogHubService.beans.request.BlogMasterRequestBean;
import com.BlogStackBlogHubService.beans.response.ServiceResponseBean;
import com.BlogStackBlogHubService.services.IBlogStackBlogsService;
import com.BlogStackBlogHubService.services.IBlogStackS3ImageUploadService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("${bloghub-service-version}/blog")
public class BlogStackBlogMasterController {

    @Autowired
    private IBlogStackBlogsService blogStackBlogsService;
    @Autowired
    private IBlogStackS3ImageUploadService blogStackS3ImageUploadService;

    @PostMapping("/")
    public ResponseEntity<Optional<ServiceResponseBean>> addQuestion(@Valid @RequestBody BlogMasterRequestBean blogMasterRequestBean){
        return ResponseEntity.ok(this.blogStackBlogsService.addBlog(blogMasterRequestBean));
    }

    @GetMapping("/")
    public ResponseEntity<Optional<ServiceResponseBean>> fetchAllQuestion(@RequestParam(defaultValue = "0") Integer page,
                                              @RequestParam(defaultValue = "10") Integer size){
        return ResponseEntity.ok(this.blogStackBlogsService.fetchAllBlogs(page, size));
    }
    @GetMapping("/{blog_id}")
    public ResponseEntity<Optional<ServiceResponseBean>> fetchQuestionById(@PathVariable(value = "blog_id") @NotBlank(message = "Question Id can not be empty.") String blogId){
        return ResponseEntity.ok(this.blogStackBlogsService.fetchBlogById(blogId));
    }

    @PutMapping("/")
    public ResponseEntity<Optional<ServiceResponseBean>> updateQuestion(@Valid @RequestBody BlogMasterRequestBean blogMasterRequestBean){
        return ResponseEntity.ok(this.blogStackBlogsService.updateBlog(blogMasterRequestBean));
    }

    @DeleteMapping("/{blog_id}")
    public ResponseEntity<Optional<ServiceResponseBean>> deleteQuestion(@PathVariable(value = "blog_id") @NotBlank(message = "Question Id can not be empty.") String blogId){
        return ResponseEntity.ok(this.blogStackBlogsService.deleteBlog(blogId));
    }

    @PutMapping("/{blog-id}")
    public ResponseEntity<ServiceResponseBean> uploadPicture(@PathVariable(value = "blog-id")String blogId , @RequestParam(value = "blog-image")MultipartFile blogImage) throws IOException {
       return ResponseEntity.ok( blogStackS3ImageUploadService.uploadBlogPhoto(blogId ,blogImage));
    }
}
