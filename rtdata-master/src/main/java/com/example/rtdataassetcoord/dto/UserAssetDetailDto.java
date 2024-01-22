package com.example.rtdataassetcoord.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserAssetDetailDto {


    @ApiModelProperty(value = "数据资产id")
    private String id;

    @ApiModelProperty(value = "数据资产名称")
    private String name;

    @ApiModelProperty(value = "数据资产权益list")
    private List<Integer> rightIds;


    @ApiModelProperty(value = "通用数据资产文件")
    private List<FileDto> fileDtos;




}
