package com.example.rtdataassetcoord.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserCopyrightDto {


    @ApiModelProperty(value = "版权id")
    private String copyrightId;

    @ApiModelProperty(value = "著作权人信息")
    private String copyrightOwner;

    @ApiModelProperty(value = "作品名称")
    private String name;

    @ApiModelProperty(value = "版权信息描述")
    private String illustration;

    @ApiModelProperty(value = "版权图片")
    private String img;

    @ApiModelProperty(value = "hash值")
    private String hash;

}
