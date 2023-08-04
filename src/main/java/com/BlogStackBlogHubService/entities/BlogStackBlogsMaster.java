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
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "blogstack_blog", schema = "master_data")
public class BlogStackBlogsMaster implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bsb_seq_id")
    private Long bsbSeqId;
    @Column(name = "bsb_blog_id", unique = true)
    private String bsbBlogId;
    @Column(name = "bsb_blog_name")
    private String bsbBlogName;
    @Column(name = "bsb_blog_picture")
    private String bsbBlogPicture;
    @Column(name = "bsb_blog_content")
    private String bsbBlogContent;
    @Column(name = "bsb_status")
    private String bsbStatus;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "bsbcm_blog_id", referencedColumnName = "bsb_blog_id")
    private Set<BlogStackBlogCommentMaster> blogStackBlogCommentMasterSet;
    @CreatedBy
    @Column(name = "bsb_created_by")
    private String bsbCreatedBy;
    @CreatedDate
    @Column(name = "bsb_created_date")
    private LocalDateTime bsbCreatedDate;
    @LastModifiedBy
    @Column(name = "bsb_modified_by")
    private String bsbModifiedBy;
    @LastModifiedDate
    @Column(name = "bsb_modified_date")
    private LocalDateTime bsbModifiedDate;
}