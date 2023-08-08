package com.BlogStackBlogHubService.services.impl;

import com.BlogStackBlogHubService.beans.response.ServiceResponseBean;
import com.BlogStackBlogHubService.commons.BlogStackMessageConstants;
import com.BlogStackBlogHubService.entities.BlogStackBlogsMaster;
import com.BlogStackBlogHubService.entity.pojo.mapper.IBlogStackBlogMasterEntityPojoMapper;
import com.BlogStackBlogHubService.exceptions.BlogstackDataNotFoundException;
import com.BlogStackBlogHubService.feign.services.IBlogStackUploadFileService;
import com.BlogStackBlogHubService.repositories.IBlogStackBlogsMasterRepository;
import com.BlogStackBlogHubService.services.IBlogStackS3ImageUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class BlogStackS3BlogsImageUpload implements IBlogStackS3ImageUploadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogStackS3BlogsImageUpload.class);

    @Value("${application.bucket.name}")
    private String bucketName;

//    @Autowired
//    private AmazonS3 s3Client;

    @Autowired
    private IBlogStackUploadFileService blogStackUploadFileService;

    @Autowired
    private IBlogStackBlogsMasterRepository blogStackBlogsMasterRepository;

//    @Override
//    public File convertMultiPartFileToFile(MultipartFile blogStackBlogImage) throws FileNotFoundException, IOException {
//        FileOutputStream fileOutputStream = null;
//        File convertedFile = null;
//        try {
//            convertedFile = new File(Objects.requireNonNull(blogStackBlogImage.getOriginalFilename()));
//            fileOutputStream = new FileOutputStream(convertedFile);
//            fileOutputStream.write(blogStackBlogImage.getBytes());
//        } finally {
//            assert fileOutputStream != null;
//            fileOutputStream.close();
//        }
//        return convertedFile;
//    }
//
//    @Override
//    public String uploadFile(MultipartFile blogStackBlogImage) throws IOException {
//        File convertedFile = convertMultiPartFileToFile(blogStackBlogImage);
//        String fileName = System.currentTimeMillis() + BlogStackCommonConstants.INSTANCE.UNDERSCORE_STRING + blogStackBlogImage.getOriginalFilename();
//        s3Client.putObject(new PutObjectRequest(bucketName, fileName, convertedFile));
//        URL url = s3Client.getUrl(bucketName, fileName);
//        return url.toString();
//    }

    @Override
    public ServiceResponseBean uploadBlogPhoto(String blogId, MultipartFile blogStackBlogsPhoto) throws IOException {
        Optional<BlogStackBlogsMaster> blogStackBlogMasterOptional = this.blogStackBlogsMasterRepository.findByBsbBlogId(blogId);

        if (blogStackBlogMasterOptional.isEmpty())
            throw new BlogstackDataNotFoundException(BlogStackMessageConstants.INSTANCE.DATA_NOT_FOUND);

//        String blogStackBlogImageUrl = uploadFile(blogStackUserProfilePhoto);
        ResponseEntity<String> blogStackBlogImageUrl = this.blogStackUploadFileService.uploadFile(blogStackBlogsPhoto, bucketName);
        blogStackBlogMasterOptional.get().setBsbBlogPicture(blogStackBlogImageUrl.getBody());

        BlogStackBlogsMaster blogStackBlogsMaster = this.blogStackBlogsMasterRepository.saveAndFlush(blogStackBlogMasterOptional.get());
        return ServiceResponseBean.builder().status(Boolean.TRUE).data(IBlogStackBlogMasterEntityPojoMapper.mapBlogMasterEntityPojoMapping.apply(Optional.of(blogStackBlogsMaster))).build();
    }
}