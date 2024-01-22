package com.example.rtdataassetcoord.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class TaskListDto {
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
}
