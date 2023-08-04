package com.BlogStackBlogHubService.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "blogstack-block-comment_master", schema = "master_data")
public class BlogStackBlogCommentMaster implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bsbcm_seq_id")
    private long bsbcmSeqId;

    @Column(name = "bsbcm_comment_id")
    private String bsbcmCommentId;

    @Column(name = "bsbcm_comment")
    private String bsbcmComment;

    @Column(name = "bsbcm_upvote")
    private Long bsbcmUpvote;

    @Column(name = "bsbcm_downvote")
    private Long bsbcmDownvote;

    @Column(name = "bsbcm_status")
    private String bsbcmStatus;

    @CreatedBy
    @Column(name = "bsbcm_created_by")
    private String bsbcmCreatedBy;

    @CreatedDate
    @Column(name = "bsbcm_created_date")
    private LocalDateTime bsbcmCreatedDate;

    @LastModifiedBy
    @Column(name = "bsbcm_modified_by")
    private String bsbcmModifiedBy;

    @LastModifiedDate
    @Column(name = "bsbcm_modified_date")
    private LocalDateTime bsbcmModifiedDate;
}
