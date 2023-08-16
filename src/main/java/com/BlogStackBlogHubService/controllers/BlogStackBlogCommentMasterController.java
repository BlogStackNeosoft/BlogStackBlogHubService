package com.BlogStackBlogHubService.controllers;

import com.BlogStackBlogHubService.beans.request.BlogCommentMasterRequestBean;
import com.BlogStackBlogHubService.beans.request.BlogMasterRequestBean;
import com.BlogStackBlogHubService.beans.response.ServiceResponseBean;
import com.BlogStackBlogHubService.services.IBlogStackBlogCommentMasterService;
import com.amazonaws.Response;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("${bloghub-service-version}/blog-comments")
public class BlogStackBlogCommentMasterController {

    @Autowired
    private IBlogStackBlogCommentMasterService blogStackBlogCommentMasterService;


    @GetMapping("/")
    public ResponseEntity<?> getAllComments(@RequestParam(defaultValue = "0") Integer page,
                                            @RequestParam(defaultValue = "10") Integer size){
        return ResponseEntity.ok(blogStackBlogCommentMasterService.fetchAllBlogComment(page, size));
    }
    @GetMapping("/{comment_id}")
    public ResponseEntity<Optional<ServiceResponseBean>> fetchCommentById(@PathVariable(value = "comment_id") @NotBlank(message = "Question Id can not be empty.") String commentId){
        return ResponseEntity.ok(this.blogStackBlogCommentMasterService.fetchBlogCommentById(commentId));
    }
    @PutMapping("/")
    public ResponseEntity<Optional<ServiceResponseBean>> updateComment(@Valid @RequestBody BlogCommentMasterRequestBean blogCommentMasterRequestBean){
        return ResponseEntity.ok(this.blogStackBlogCommentMasterService.updateBlogComment(blogCommentMasterRequestBean));
    }

    @PostMapping("/{blog-id}")
    public ResponseEntity<?> addComment(@PathVariable(value = "blog-id") String blogId, @Valid @RequestBody BlogCommentMasterRequestBean blogCommentMasterRequestBean) {
        return ResponseEntity.ok(this.blogStackBlogCommentMasterService.addBlogComment(blogId, blogCommentMasterRequestBean));
    }

//    @DeleteMapping("/{comment_id}")
//    public ResponseEntity<?> deleteComment(@PathVariable(value = "comment_id") String commentId){
//        return ResponseEntity.ok(this.blogStackBlogCommentMasterService.);
//    }






}
