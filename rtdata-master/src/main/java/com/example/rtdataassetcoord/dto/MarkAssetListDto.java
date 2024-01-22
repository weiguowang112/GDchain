package com.example.rtdataassetcoord.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class MarkAssetListDto {
    @ApiModelProperty(value = "数据资产id")
    private String id;

    @ApiModelProperty(value = "机构id")
    private Integer userId;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @ApiModelProperty(value = "数据资产名称")
    private String name;

    @ApiModelProperty(value = "发行数量")
    private Integer number;

    @ApiModelProperty(value = "定价")
    private Integer price;

    @ApiModelProperty(value = "封面文件地址")
    private String filePath;


}
