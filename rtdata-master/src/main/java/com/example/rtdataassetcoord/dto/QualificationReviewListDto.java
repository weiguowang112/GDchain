package com.example.rtdataassetcoord.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QualificationReviewListDto {
    @ApiModelProperty(value = "审核记录id")
    private String caseId;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @ApiModelProperty(value = "法人名称")
    private String legalPerson;

    @ApiModelProperty(value = "法人身份证件号")
    private String legalPersonNumber;

    @ApiModelProperty(value = "企业统一信用码")
    private String enterpriseCode;

    @ApiModelProperty(value = "机构官网")
    private String orgWebsite;

    @ApiModelProperty(value = "审核情况(已审核人数/总人数)")
    private String reviewSituation;

    @ApiModelProperty(value = "状态 0未投票 1已投票")
    private Integer status;

}
