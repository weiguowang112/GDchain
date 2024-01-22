package com.example.rtdataassetcoord.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class MediaContentVo {

    @ApiModelProperty(value = "阅读数")
    private Integer readNum;

    @ApiModelProperty(value = "评论数")
    private Integer commentNum;

    @ApiModelProperty(value = "转发数")
    private Integer forwardNum;

    @ApiModelProperty(value = "点赞数")
    private Integer likeNum;

    @ApiModelProperty(value = "发布日期")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

    @ApiModelProperty(value = "链接")
    private String link;
}
