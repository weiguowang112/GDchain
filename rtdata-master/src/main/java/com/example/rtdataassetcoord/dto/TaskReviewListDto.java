package com.example.rtdataassetcoord.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TaskReviewListDto {

    @ApiModelProperty(value = "选题任务id")
    private String id;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @ApiModelProperty(value = "选题名称")
    private String topicName;

    @ApiModelProperty(value = "任务时长（几天）")
    private Integer taskTime;

    @ApiModelProperty(value = "任务内容")
    private List<String> tasks;

    @ApiModelProperty(value = "文件名字")
    private String fileName;


}
