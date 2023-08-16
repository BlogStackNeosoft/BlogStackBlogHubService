package com.BlogStackBlogHubService.repositories;


import com.BlogStackBlogHubService.entities.BlogStackBlogsMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBlogStackBlogsMasterRepository extends JpaRepository<BlogStackBlogsMaster, Long> {
    Optional<BlogStackBlogsMaster> findByBsbBlogId(String blogId);

}
