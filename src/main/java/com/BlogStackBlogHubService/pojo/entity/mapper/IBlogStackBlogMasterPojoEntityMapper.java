package com.BlogStackBlogHubService.pojo.entity.mapper;


import com.BlogStackBlogHubService.beans.request.BlogMasterRequestBean;
import com.BlogStackBlogHubService.entities.BlogStackBlogsMaster;
import com.BlogStackBlogHubService.enums.BlogMasterStatusEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.function.BiFunction;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class, BlogMasterStatusEnum.class})
public interface IBlogStackBlogMasterPojoEntityMapper {

    IBlogStackBlogMasterPojoEntityMapper INSTANCE = Mappers.getMapper(IBlogStackBlogMasterPojoEntityMapper.class);


    @Mappings({
            @Mapping(target = "bsbBlogId", source = "blogsMasterRequestBean.blogId"),
            @Mapping(target = "bsbBlogName", source = "blogsMasterRequestBean.blogName"),
            @Mapping(target = "bsbBlogContent", source = "blogsMasterRequestBean.blogContent"),
            @Mapping(target = "bsbBlogPicture", source = "blogsMasterRequestBean.blogPicture"),
            @Mapping(target = "bsbStatus", expression = "java(BlogMasterStatusEnum.ACTIVE.getValue())"),
            @Mapping(target = "bsbCreatedBy", source = "blogsMasterRequestBean.createdBy"),
            @Mapping(target = "bsbCreatedDate", expression = "java(LocalDateTime.now())")
    })
    BlogStackBlogsMaster blogMasterRequestToBlogMasterEntity(BlogMasterRequestBean blogsMasterRequestBean);

    public static BiFunction<BlogMasterRequestBean, BlogStackBlogsMaster, BlogStackBlogsMaster> updateBlogMaster = (blogMasterRequestBean, blogStackBlogMaster) -> {
        blogStackBlogMaster.setBsbBlogId(blogMasterRequestBean.getBlogId());
        blogStackBlogMaster.setBsbBlogName(blogMasterRequestBean.getBlogName());
        blogStackBlogMaster.setBsbBlogContent(blogMasterRequestBean.getBlogContent());
        blogStackBlogMaster.setBsbBlogPicture(blogMasterRequestBean.getBlogPicture());
        blogStackBlogMaster.setBsbModifiedBy(blogMasterRequestBean.getModifiedBy());
        blogStackBlogMaster.setBsbModifiedDate(LocalDateTime.now());
        return blogStackBlogMaster;
    };


    }
