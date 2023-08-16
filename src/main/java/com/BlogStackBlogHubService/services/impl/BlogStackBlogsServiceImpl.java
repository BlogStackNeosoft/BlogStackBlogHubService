package com.BlogStackBlogHubService.services.impl;

import com.BlogStackBlogHubService.beans.request.BlogMasterRequestBean;
import com.BlogStackBlogHubService.beans.response.BlogMasterResponseBean;
import com.BlogStackBlogHubService.beans.response.PageResponseBean;
import com.BlogStackBlogHubService.beans.response.ServiceResponseBean;
import com.BlogStackBlogHubService.beans.response.UserResponseBean;
import com.BlogStackBlogHubService.commons.BlogStackMessageConstants;
import com.BlogStackBlogHubService.entities.BlogStackBlogsMaster;
import com.BlogStackBlogHubService.entity.pojo.mapper.IBlogStackBlogMasterEntityPojoMapper;
import com.BlogStackBlogHubService.enums.UuidPrefixEnum;
import com.BlogStackBlogHubService.exceptions.BlogstackDataNotFoundException;
import com.BlogStackBlogHubService.feign.clients.IBlogStackUserFeignService;
import com.BlogStackBlogHubService.pojo.entity.mapper.IBlogStackBlogMasterPojoEntityMapper;
import com.BlogStackBlogHubService.pojo.entity.mapper.IBlogStackUserPojoEntityMapping;
import com.BlogStackBlogHubService.repositories.IBlogStackBlogsMasterRepository;
import com.BlogStackBlogHubService.services.IBlogStackBlogsService;
import com.BlogStackBlogHubService.utils.BlogStackCommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;


@Service
public class BlogStackBlogsServiceImpl implements IBlogStackBlogsService {

   @Autowired
   private IBlogStackBlogsMasterRepository blogStackBlogsMasterRepository;
   @Autowired
   private IBlogStackUserFeignService blogStackUserFeignService;
    @Value("#{'${spring.application.name}'.toUpperCase()}")
    private String springApplicationName;
   private static Logger LOGGER = LoggerFactory.getLogger(BlogStackBlogsServiceImpl.class);


    @Override
    public Optional<ServiceResponseBean> addBlog(BlogMasterRequestBean blogsRequestBean) {
        String blogId = BlogStackCommonUtils.INSTANCE.uniqueIdentifier(UuidPrefixEnum.BLOG_ID.getValue());
        LOGGER.warn("BlogId :: {}", blogId);
        blogsRequestBean.setBlogId(blogId);
        blogsRequestBean.setCreatedBy(springApplicationName);
        BlogStackBlogsMaster blogStackBlogMaster = this.blogStackBlogsMasterRepository.saveAndFlush(IBlogStackBlogMasterPojoEntityMapper.INSTANCE.blogMasterRequestToBlogMasterEntity(blogsRequestBean));
        return Optional.of(ServiceResponseBean.builder().status(Boolean.TRUE).data(IBlogStackBlogMasterEntityPojoMapper.mapBlogMasterEntityPojoMapping.apply(Optional.of(blogStackBlogMaster))).build());

    }

    @Override
    public Optional<ServiceResponseBean> fetchAllBlogs(Integer page, Integer size) {

        Page<BlogStackBlogsMaster> blogStackBlogsMastersPage =this.blogStackBlogsMasterRepository.findAll(PageRequest.of(page,size));
            LOGGER.debug("BlogStackQuestionMaster :: {}", blogStackBlogsMastersPage);

        if(blogStackBlogsMastersPage.isEmpty())
            throw new BlogstackDataNotFoundException(BlogStackMessageConstants.INSTANCE.DATA_NOT_FOUND);

        List<BlogMasterResponseBean> blogMasterResponseBeanList = new ArrayList<>();
        blogStackBlogsMastersPage.getContent().stream().map(blog->{
          LinkedHashMap body=  (LinkedHashMap) this.blogStackUserFeignService.fetchUserByUserId(blog.getBsbUserId()).getBody();
          LinkedHashMap dataMap =(LinkedHashMap) body.get("data");
          BlogMasterResponseBean blogMasterResponseBean =IBlogStackBlogMasterEntityPojoMapper.mapBlogMasterEntityPojoMapping.apply(Optional.of(blog));
          blogMasterResponseBean.setUserResponseBean(IBlogStackUserPojoEntityMapping.mapLMapToUserResponseBean.apply(dataMap));
          LOGGER.info("RESPONSE"+blogMasterResponseBean);
          blogMasterResponseBeanList.add(blogMasterResponseBean);
          return blogMasterResponseBean;
        }).toList();

        return Optional.of(ServiceResponseBean.builder()
                .status(Boolean.TRUE).data(PageResponseBean.builder().payload(blogMasterResponseBeanList)
                        .numberOfElements(blogStackBlogsMastersPage.getNumberOfElements())
                        .pageSize(blogStackBlogsMastersPage.getSize())
                        .totalElements(blogStackBlogsMastersPage.getTotalElements())
                        .totalPages(blogStackBlogsMastersPage.getTotalPages())
                        .currentPage(blogStackBlogsMastersPage.getNumber())
                        .build()).build());
    }

    @Override
    public Optional<ServiceResponseBean> fetchBlogById(String blogId) {
        Optional<BlogStackBlogsMaster> blogStackBlogsMaster = this.blogStackBlogsMasterRepository.findByBsbBlogId(blogId);
        LOGGER.warn("BlogStackQuestionMasterOptional :: {}", blogStackBlogsMaster);
        LOGGER.info("USERID"+blogStackBlogsMaster.get().getBsbUserId());
        if(blogStackBlogsMaster.isEmpty())
            throw new BlogstackDataNotFoundException(BlogStackMessageConstants.INSTANCE.DATA_NOT_FOUND);
         ;
        LOGGER.info("UserResponseBean  :: ==>"+  this.blogStackUserFeignService.fetchUserByUserId(blogStackBlogsMaster.get().getBsbUserId()).getBody().getClass().getName());
            //Feign Call return LinkedHashMap
        LinkedHashMap body = (LinkedHashMap) this.blogStackUserFeignService.fetchUserByUserId(blogStackBlogsMaster.get().getBsbUserId()).getBody();
        LOGGER.info(""+body.get("data"));
        LinkedHashMap dataMap =(LinkedHashMap) body.get("data");
        dataMap.keySet().stream().forEach(key-> System.out.println("KEYS==>"+key+    "KEY TYPE ==> "+ key.getClass().getName()));
        dataMap.values().stream().forEach(value-> System.out.println("VALUE==>"+value+ "Value TYPE==>" +value.getClass().getName() ));
        BlogMasterResponseBean blogMasterResponseBean =IBlogStackBlogMasterEntityPojoMapper.mapBlogMasterEntityPojoMapping.apply(blogStackBlogsMaster);
        blogMasterResponseBean.setUserResponseBean(IBlogStackUserPojoEntityMapping.mapLMapToUserResponseBean.apply(dataMap));
        return Optional.of(ServiceResponseBean.builder().status(Boolean.TRUE).data(blogMasterResponseBean).build());
    }

    @Override
    public Optional<ServiceResponseBean> updateBlog(BlogMasterRequestBean blogsRequestBean) {
        Optional<BlogStackBlogsMaster> blogStackBlogsMasterOptional = this.blogStackBlogsMasterRepository.findByBsbBlogId(blogsRequestBean.getBlogId());
        LOGGER.debug("BlogStackQuestionMasterOptional :: {}", blogStackBlogsMasterOptional);

        if (blogStackBlogsMasterOptional.isEmpty())
            throw new BlogstackDataNotFoundException(BlogStackMessageConstants.INSTANCE.DATA_NOT_FOUND);

        blogsRequestBean.setModifiedBy(this.springApplicationName);
        BlogStackBlogsMaster blogStackBlogsMaster = IBlogStackBlogMasterPojoEntityMapper.updateBlogMaster.apply(blogsRequestBean,blogStackBlogsMasterOptional.get());
        LOGGER.debug("BlogStackBlogsMaster :: {}", blogStackBlogsMaster);
        this.blogStackBlogsMasterRepository.saveAndFlush(blogStackBlogsMaster);

        return Optional.of(ServiceResponseBean.builder().status(Boolean.TRUE).data(IBlogStackBlogMasterEntityPojoMapper.mapBlogMasterEntityPojoMapping.apply(Optional.of(blogStackBlogsMaster))).build());

    }

    @Override
    public Optional<ServiceResponseBean> deleteBlog(String blogId) {
        Optional<BlogStackBlogsMaster> blogStackBlogsMasterOptional = this.blogStackBlogsMasterRepository.findByBsbBlogId(blogId);
        LOGGER.warn("BlogStackBlogsMasterOptional :: {}", blogStackBlogsMasterOptional);

        if(blogStackBlogsMasterOptional.isEmpty())
            throw new BlogstackDataNotFoundException(BlogStackMessageConstants.INSTANCE.DATA_NOT_FOUND);

        this.blogStackBlogsMasterRepository.delete(blogStackBlogsMasterOptional.get());
        return Optional.of(ServiceResponseBean.builder().status(Boolean.TRUE).message(BlogStackMessageConstants.INSTANCE.DATA_DELETED).build());
    }

}
