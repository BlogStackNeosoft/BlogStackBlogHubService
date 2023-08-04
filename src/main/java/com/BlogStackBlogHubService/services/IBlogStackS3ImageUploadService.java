package com.BlogStackBlogHubService.services;

import com.BlogStackBlogHubService.beans.response.ServiceResponseBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IBlogStackS3ImageUploadService {

    File convertMultiPartFileToFile(MultipartFile file)throws FileNotFoundException, IOException;

    String uploadFile(MultipartFile file) throws IOException;

    ServiceResponseBean uploadBlogPhoto(String email, MultipartFile blogStackUserProfilePhoto) throws IOException;

}
