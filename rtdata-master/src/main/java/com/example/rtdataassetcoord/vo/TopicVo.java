package com.example.rtdataassetcoord.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TopicVo {

    @ApiModelProperty(value = "选题名字")
    private String topicName;

    @ApiModelProperty(value = "活动类型")
    private String topicType;

    @ApiModelProperty(value = "0代表不需要写作，1代表需要协作")
    private Integer collaborate;
}
