package com.example.rtdataassetcoord.dto;



import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TopicReviewListDto {

    @ApiModelProperty(value = "选题id")
    private String caseId;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @ApiModelProperty(value = "选题名称")
    private String topicName;

    @ApiModelProperty(value = "活动类型")
    private String topicType;

    @ApiModelProperty(value = "0代表不需要写作，1代表需要协作")
    private Integer collaborate;

    @ApiModelProperty(value = "机构官网")
    private String orgWebsite;

    @ApiModelProperty(value = "审核情况(已审核人数/总人数)")
    private String reviewSituation;

    @ApiModelProperty(value = "状态 0未投票 1已投票")
    private Integer status;
}
