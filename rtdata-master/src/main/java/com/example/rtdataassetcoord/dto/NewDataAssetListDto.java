package com.example.rtdataassetcoord.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@Data
public class NewDataAssetListDto {

    @ApiModelProperty(value = "数据资产名称")
    private String name;

    @ApiModelProperty(value = "数据资产发行方")
    private String orgName;

    @ApiModelProperty(value = "发行数量")
    private Integer number;

    @ApiModelProperty(value = "定价")
    private Integer price;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
