package com.BlogStackBlogHubService.beans.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseBean {

    private Long bsuSeqId;
    private String bsuUserId;
    private String bsuPassword;
    private String bsuEmailId;
    private String bsuLastName;
    private String bsuMiddleName;
    private String bsuFirstName;
    private String bsuGender;
    private String bsuPhoneNumber;
    private String bsuStatus;
    private LocalDate bsuDateOfBirth;
    private String bsuAddress;
    private String bsuProfilePhoto;
    @JsonIgnore
    private Set<RoleResponseBean> blogStackRoleDetails;
    private String bsuCreatedBy;
    private LocalDateTime bsuCreatedDate;
    private String bsuModifiedBy;
    private LocalDateTime bsuModifiedDate;
}
