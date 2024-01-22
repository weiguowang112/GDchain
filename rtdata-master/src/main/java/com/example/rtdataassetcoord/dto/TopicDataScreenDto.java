package com.example.rtdataassetcoord.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TopicDataScreenDto {

    @ApiModelProperty(value = "本月参与选题数")
    private Integer thisMonthCoTopics;

    @ApiModelProperty(value = "本月获得钥匙数")
    private Integer thisMonthGetKey;

    @ApiModelProperty(value = "累计参与协作数量")
    private Integer allCoTopics;

}
