package com.BlogStackBlogHubService.pojo.entity.mapper;

import com.BlogStackBlogHubService.beans.response.UserResponseBean;

import java.util.LinkedHashMap;
import java.util.function.Function;

//THIS MAPPER IS FOR CONVERTING LINKED HASHMAP TO USER RESPONSE BEAN
public interface IBlogStackUserPojoEntityMapping {

    static Function<LinkedHashMap<String,Object>,UserResponseBean> mapLMapToUserResponseBean = (linkedMap)->{
        return UserResponseBean.builder()
                .bsuUserId((String) linkedMap.get("user_id"))
                .bsuEmailId((String)linkedMap.get("email_id"))
                .bsuLastName((String)linkedMap.get("last_name"))
                .bsuFirstName((String)linkedMap.get("first_name"))
                .bsuGender((String)linkedMap.get("gender"))
                .bsuStatus((String)linkedMap.get("status"))
                .bsuPhoneNumber((String)linkedMap.get("phone_number"))
                .bsuAddress((String)linkedMap.get("address"))
                .build();
    };
}
