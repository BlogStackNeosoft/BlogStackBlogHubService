package com.BlogStackBlogHubService.repositories;


import com.BlogStackBlogHubService.entities.BlogStackBlogCommentMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBlogStackBlogCommentMasterRepository extends  JpaRepository<BlogStackBlogCommentMaster,Long> {
}
