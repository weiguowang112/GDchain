package com.example.rtdataassetcoord.vo;


import com.example.rtdataassetcoord.entity.Task;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class TopicTaskVo {
    @ApiModelProperty(value = "选题id")
    private String topicId;

    @ApiModelProperty(value = "任务分配者id（钥匙持有人）")
    private Integer assignerId;

    @ApiModelProperty(value = "任务接收者id")
    private Integer receiverId;

    @ApiModelProperty(value = "任务时长（几天）")
    private Integer taskTime;


    @ApiModelProperty(value = "任务时长（几天）")
    private List<String> tasks;

}
