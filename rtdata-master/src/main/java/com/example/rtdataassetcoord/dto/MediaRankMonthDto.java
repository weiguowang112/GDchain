package com.example.rtdataassetcoord.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MediaRankMonthDto {
    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @ApiModelProperty(value = "上月评级")
    private Integer rank;

    @ApiModelProperty(value = "上月评分")
    private BigDecimal grade;

    @ApiModelProperty(value = "机构名称")
    private String headImg;


}
