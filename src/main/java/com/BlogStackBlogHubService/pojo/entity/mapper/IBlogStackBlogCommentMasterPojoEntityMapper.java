package com.BlogStackBlogHubService.pojo.entity.mapper;


import com.BlogStackBlogHubService.beans.request.BlogCommentMasterRequestBean;
import com.BlogStackBlogHubService.entities.BlogStackBlogCommentMaster;
import com.BlogStackBlogHubService.enums.CommentMasterStatusEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import java.time.LocalDateTime;
import java.util.function.BiFunction;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class, CommentMasterStatusEnum.class})
public interface IBlogStackBlogCommentMasterPojoEntityMapper {

    IBlogStackBlogCommentMasterPojoEntityMapper INSTANCE = Mappers.getMapper(IBlogStackBlogCommentMasterPojoEntityMapper.class);

    @Mappings({
            @Mapping(target = "bsbcmCommentId", source = "blogCommentMasterRequestBean.commentId"),
            @Mapping(target = "bsbcmComment", source = "blogCommentMasterRequestBean.comment"),
            @Mapping(target="bsbcmDownvote",source="blogCommentMasterRequestBean.downvote"),
            @Mapping(target = "bsbcmUpvote",source = "blogCommentMasterRequestBean.upvote"),
            @Mapping(target = "bsbcmStatus", expression = "java(CommentMasterStatusEnum.ACTIVE.getValue())"),
            @Mapping(target = "bsbcmCreatedBy", source = "blogCommentMasterRequestBean.createdBy"),
            @Mapping(target = "bsbcmCreatedDate", expression = "java(LocalDateTime.now())")
    })
    BlogStackBlogCommentMaster blogCommentMasterRequestToCommentMasterEntity(BlogCommentMasterRequestBean blogCommentMasterRequestBean);

    public static BiFunction<BlogCommentMasterRequestBean, BlogStackBlogCommentMaster, BlogStackBlogCommentMaster> updateCommentMaster = ((blogCommentMasterRequestBean, blogStackBlogCommentMaster) ->  {
        blogStackBlogCommentMaster.setBsbcmCommentId(blogCommentMasterRequestBean.getCommentId() != null ? blogCommentMasterRequestBean.getCommentId() : blogStackBlogCommentMaster.getBsbcmCommentId());
        blogStackBlogCommentMaster.setBsbcmComment(blogCommentMasterRequestBean.getComment() != null ? blogCommentMasterRequestBean.getComment() : blogStackBlogCommentMaster.getBsbcmComment());
        blogStackBlogCommentMaster.setBsbcmDownvote(blogCommentMasterRequestBean.getDownvote() != null ? blogCommentMasterRequestBean.getDownvote() : blogStackBlogCommentMaster.getBsbcmDownvote());
        blogStackBlogCommentMaster.setBsbcmUpvote(blogCommentMasterRequestBean.getUpvote() != null ? blogCommentMasterRequestBean.getUpvote() : blogStackBlogCommentMaster.getBsbcmUpvote());
        blogStackBlogCommentMaster.setBsbcmStatus(blogCommentMasterRequestBean.getStatus() != null ? blogCommentMasterRequestBean.getStatus() : blogStackBlogCommentMaster.getBsbcmStatus());
        blogStackBlogCommentMaster.setBsbcmModifiedBy(blogCommentMasterRequestBean.getModifiedBy());
        blogStackBlogCommentMaster.setBsbcmModifiedDate(LocalDateTime.now());
        return blogStackBlogCommentMaster;
    });

}
