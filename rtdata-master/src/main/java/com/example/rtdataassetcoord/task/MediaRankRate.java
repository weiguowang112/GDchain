package com.example.rtdataassetcoord.task;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MediaRankRate {
    private Integer userId;

    private Integer fansNum;

    private Double readRate;

    private Double commentRate;

    private Double forwardRate;

    private Double likeRate;
}
