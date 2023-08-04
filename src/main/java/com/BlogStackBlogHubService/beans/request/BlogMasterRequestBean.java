package com.BlogStackBlogHubService.beans.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlogMasterRequestBean {
    @JsonProperty(value = "blog_id")
    private String blogId;
    @JsonProperty(value = "blog_name")
    private String blogName;
    @JsonProperty(value = "blog_picture")
    private String blogPicture;
    @JsonProperty(value = "blog_content")
    private String blogContent;
    @JsonIgnore
    private String createdBy;
    @JsonIgnore
    private String modifiedBy;

}
