package com.BlogStackBlogHubService.beans.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponseBean {
    private Long brdSeqId;
    private String brdRoleId;
    private String brdRoleName;
    @JsonIgnore
    private String brdStatus;
}
