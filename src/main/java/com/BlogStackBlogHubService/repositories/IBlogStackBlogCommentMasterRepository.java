package com.BlogStackBlogHubService.repositories;


import com.BlogStackBlogHubService.entities.BlogStackBlogCommentMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IBlogStackBlogCommentMasterRepository extends  JpaRepository<BlogStackBlogCommentMaster,Long> {
     Optional<BlogStackBlogCommentMaster> findByBsbcmCommentId(String commentId);

     //List<BlogStackBlogCommentMaster> findBlogStackBlogCommentMastersByBlogStackBlogsMastersBsbBlogId(String blogId);

}
