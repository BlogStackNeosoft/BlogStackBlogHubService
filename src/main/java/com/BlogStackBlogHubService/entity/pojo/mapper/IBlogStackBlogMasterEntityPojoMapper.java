package com.BlogStackBlogHubService.entity.pojo.mapper;



import com.BlogStackBlogHubService.beans.response.BlogMasterResponseBean;
import com.BlogStackBlogHubService.entities.BlogStackBlogsMaster;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface IBlogStackBlogMasterEntityPojoMapper {

    public static Function<Optional<BlogStackBlogsMaster>, BlogMasterResponseBean> mapBlogMasterEntityPojoMapping = blogStackBlogMaster-> BlogMasterResponseBean.builder()
            .blogId(blogStackBlogMaster.isEmpty()?    null  :  blogStackBlogMaster.get().getBsbBlogId())
            .blogName(blogStackBlogMaster.isEmpty()?    null  :blogStackBlogMaster.get().getBsbBlogName())
            .blogContent(blogStackBlogMaster.isEmpty()?    null  :blogStackBlogMaster.get().getBsbBlogContent())
            .blogPicture(blogStackBlogMaster.get().getBlogStackBlogCommentMasterSet()==null ?    null  :blogStackBlogMaster.get().getBsbBlogPicture())
            .blogCommentMasterResponseBeanSet(blogStackBlogMaster.get().getBlogStackBlogCommentMasterSet()==null? null : IBlogStackBlogCommentMasterEntityPojoMapper.mapCommentMasterEntityListToPojoListMapping.apply(blogStackBlogMaster.get().getBlogStackBlogCommentMasterSet()))
            .status(blogStackBlogMaster.isEmpty()?    null  :blogStackBlogMaster.get().getBsbStatus())
            .addedOn(blogStackBlogMaster.isEmpty()?    null  :blogStackBlogMaster.get().getBsbCreatedDate())
            .build();

    public static Function<List<BlogStackBlogsMaster>, List<BlogMasterResponseBean>> mapBlogMasterEntityListToPojoListMapping = blogStackBlogsMasterList -> blogStackBlogsMasterList.stream()
            .map(blogStackBlogMaster->{
                return BlogMasterResponseBean.builder()
                        .blogId(blogStackBlogMaster.getBsbBlogId())
                        .blogName(blogStackBlogMaster.getBsbBlogName())
                        .blogContent(blogStackBlogMaster.getBsbBlogContent())
                        .blogCommentMasterResponseBeanSet(IBlogStackBlogCommentMasterEntityPojoMapper.mapCommentMasterEntityListToPojoListMapping.apply(blogStackBlogMaster.getBlogStackBlogCommentMasterSet()))
                        .blogPicture(blogStackBlogMaster.getBsbBlogPicture())
                        .status(blogStackBlogMaster.getBsbStatus())
                        .addedOn(blogStackBlogMaster.getBsbCreatedDate())
                        .build();
            }).toList();
}
