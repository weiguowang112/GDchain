package com.example.rtdataassetcoord.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DataAssetListDto {
    @ApiModelProperty(value = "审核记录id")
    private String caseId;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @ApiModelProperty(value = "资产类型，0通用数据资产  1影响力数据资产")
    private Integer type;

    @ApiModelProperty(value = "评级")
    private Integer rank;

    @ApiModelProperty(value = "评分")
    private BigDecimal grade;

    @ApiModelProperty(value = "nft评级地址")
    private String nftAddress;

    @ApiModelProperty(value = "审核情况(已审核人数/总人数)")
    private String reviewSituation;

    @ApiModelProperty(value = "状态 0未投票 1已投票")
    private Integer status;

}
