package com.example.rtdataassetcoord.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VoteKeyHolderDto {

    @ApiModelProperty(value = "选题id")
    private String topicId;

    @ApiModelProperty(value = "投票人id")
    private Integer voterId;

    @ApiModelProperty(value = "被投票人id")
    private Integer candidateId;
}
