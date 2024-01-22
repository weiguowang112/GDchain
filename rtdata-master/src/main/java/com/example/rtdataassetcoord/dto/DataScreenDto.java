package com.example.rtdataassetcoord.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DataScreenDto {

    @ApiModelProperty(value = "数据资产总数")
    private Integer totalDataAsset;


    @ApiModelProperty(value = "发行方总数")
    private Integer totalIssuer;


    @ApiModelProperty(value = "本月上新数")
    private Integer newIssueThisMonth;


    @ApiModelProperty(value = "今日上新数")
    private Integer newIssueToday;
}
