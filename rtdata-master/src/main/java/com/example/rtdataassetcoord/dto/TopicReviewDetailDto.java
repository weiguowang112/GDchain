package com.example.rtdataassetcoord.dto;



import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TopicReviewDetailDto {

    @ApiModelProperty(value = "选题id")
    private String id;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @ApiModelProperty(value = "选题名称")
    private String topicName;

    @ApiModelProperty(value = "活动类型")
    private String topicType;

    @ApiModelProperty(value = "0代表不需要写作，1代表需要协作")
    private Integer collaborate;

    @ApiModelProperty(value = "本月协同选题数")
    private Integer coTopics;


}
