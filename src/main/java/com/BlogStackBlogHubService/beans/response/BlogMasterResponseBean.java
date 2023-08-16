package com.BlogStackBlogHubService.beans.response;

import com.BlogStackBlogHubService.commons.BlogStackCommonConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlogMasterResponseBean {
    @JsonProperty(value = "blog_id")
    private String blogId;
    @JsonProperty(value = "blog_name")
    private String blogName;
    @JsonProperty(value = "blog_picture")
    private String blogPicture;
    @JsonProperty(value = "blog_content")
    private String blogContent;
    private String status;
    @JsonProperty(value = "user")
    private UserResponseBean userResponseBean;
    @JsonProperty(value = "comments")
    private Set<BlogCommentMasterResponseBean> blogCommentMasterResponseBeanSet;
    @JsonProperty(value = "added_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BlogStackCommonConstants.DATE_FORMAT)
    private LocalDateTime addedOn;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BlogStackCommonConstants.DATE_FORMAT)
    private LocalDateTime modifiedDate;
}