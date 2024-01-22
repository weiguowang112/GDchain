package com.example.rtdataassetcoord.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
public class InfluenceAssetVo {


    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "数据资产名称")
    private String name;

    @ApiModelProperty(value = "发行数量")
    private Integer number;

    @ApiModelProperty(value = "定价")
    private Integer price;

    @ApiModelProperty(value = "nft评级地址")
    private String nftAddress;

    @ApiModelProperty(value = "封面文件地址")
    private String filePath;


    @ApiModelProperty(value = "其他文件地址")
    private List<String>  filePaths;


    @ApiModelProperty(value = "选择的权益id")
    private List<Integer>  rightIds;

}
