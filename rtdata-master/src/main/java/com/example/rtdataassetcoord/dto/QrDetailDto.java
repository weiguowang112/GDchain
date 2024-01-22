package com.example.rtdataassetcoord.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QrDetailDto {

    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @ApiModelProperty(value = "申请类型")
    private Integer spaceId;

    @ApiModelProperty(value = "法人名称")
    private String legalPerson;

    @ApiModelProperty(value = "法人身份证件号")
    private String legalPersonNumber;

    @ApiModelProperty(value = "企业统一信用码")
    private String enterpriseCode;

    @ApiModelProperty(value = "机构官网")
    private String orgWebsite;
}
