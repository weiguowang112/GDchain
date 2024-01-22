package com.example.rtdataassetcoord.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MediaRankListDto {

    @ApiModelProperty(value = "媒体评级id")
    private String id;

    @ApiModelProperty(value = "机构id")
    private Integer userId;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @ApiModelProperty(value = "上月评级")
    private Integer lastMonthRank;

    @ApiModelProperty(value = "上月评分")
    private BigDecimal lastMonthGrade;

    @ApiModelProperty(value = "截止日期")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date deadline;

    @ApiModelProperty(value = "审核情况(已审核人数/总人数)")
    private String reviewSituation;

    @ApiModelProperty(value = "状态 0未投票 1已投票")
    private Integer status;
}
