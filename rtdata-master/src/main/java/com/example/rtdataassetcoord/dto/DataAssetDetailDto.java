package com.example.rtdataassetcoord.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class DataAssetDetailDto {
    @ApiModelProperty(value = "审核记录id")
    private String id;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @ApiModelProperty(value = "评级")
    private Integer rank;

    @ApiModelProperty(value = "评分")
    private BigDecimal grade;

    @ApiModelProperty(value = "数据资产名称")
    private String name;

    @ApiModelProperty(value = "发行数量")
    private Integer number;

    @ApiModelProperty(value = "定价")
    private Integer price;

    @ApiModelProperty(value = "资产类型，0通用数据资产  1影响力数据资产")
    private Integer type;

    @ApiModelProperty(value = "nft评级地址")
    private String nftAddress;

    @ApiModelProperty(value = "相关文件")
    private List<FileDto> files;


    @ApiModelProperty(value = "选择的权益id")
    private List<Integer>  rightIds;

}
