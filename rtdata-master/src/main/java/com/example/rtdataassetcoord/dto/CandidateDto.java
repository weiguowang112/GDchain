package com.example.rtdataassetcoord.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CandidateDto {

    @ApiModelProperty(value = "userId")
    private Integer userId;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @ApiModelProperty(value = "选题想法")
    private String idea;

}
