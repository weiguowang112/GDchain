package com.example.rtdataassetcoord.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TopicListDto {

    @ApiModelProperty(value = "选题表id")
    private String id;

    @ApiModelProperty(value = "选题名称")
    private String topicName;

    @ApiModelProperty(value = "活动类型")
    private String topicType;

    @ApiModelProperty(value = "钥匙持有者id")
    private Integer keyHolderId;

    @ApiModelProperty(value = "钥匙持有者id")
    private String orgName;

    @ApiModelProperty(value = "参与机构数量")
    private Integer joinOrgCount;

    @ApiModelProperty(value = "选题审核通过时间")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "选题开始时间，为选题通过的时间+7天")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date topicStartTime;

    @ApiModelProperty(value = "2：成员加入阶段（4天），3：钥匙持有人票选阶段（3天），4：项目协作中")
    private Integer topicStatus;

    @ApiModelProperty(value = "0：未加入 1：已加入")
    private Integer joinStatus;


}
