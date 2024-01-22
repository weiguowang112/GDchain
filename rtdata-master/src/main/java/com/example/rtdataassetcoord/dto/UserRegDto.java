package com.example.rtdataassetcoord.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserRegDto {

    private String orgName;
    private String username;
    private String phone;
    private String password;
    private Integer spaceId;
    private String headImg;
}
