package com.BlogStackBlogHubService.services;

import com.BlogStackBlogHubService.beans.request.BlogCommentMasterRequestBean;
import com.BlogStackBlogHubService.beans.response.ServiceResponseBean;

import java.util.Optional;

public interface IBlogStackBlogCommentMasterService {

    Optional<ServiceResponseBean> addBlogComment(String answerId, BlogCommentMasterRequestBean blogCommentMasterRequestBean);
    Optional<ServiceResponseBean> fetchAllBlogComment(Integer page, Integer size);

    Optional<ServiceResponseBean> fetchAllBlogCommentsByBlogId(String answerId);

    Optional<ServiceResponseBean> fetchBlogCommentById(String commentId);

    Optional<ServiceResponseBean> updateBlogComment(BlogCommentMasterRequestBean blogCommentMasterRequestBean);

    Optional<ServiceResponseBean> deleteBlogComment(String blogId);

    Optional<ServiceResponseBean> deleteAllBlogCommentByBlogId(String blogId);
}
