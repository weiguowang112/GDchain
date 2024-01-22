package com.example.rtdataassetcoord.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class VoteListDto {

    @ApiModelProperty(value = "选题id")
    private String id;

    @ApiModelProperty(value = "选题名称")
    private String topicName;

    @ApiModelProperty(value = "机构名称(投递选题方)")
    private String orgName;

    @ApiModelProperty(value = "参与机构数量")
    private Integer joinOrgCount;

    @ApiModelProperty(value = "投票开始时间")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date voteStartTime;

    @ApiModelProperty(value = "投票结束时间")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date voteEndTime;

    @ApiModelProperty(value = "0：未投票 1：已投票")
    private Integer voteStatus;
}
