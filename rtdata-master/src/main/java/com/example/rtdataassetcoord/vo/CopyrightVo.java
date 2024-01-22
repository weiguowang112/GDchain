package com.example.rtdataassetcoord.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CopyrightVo {

    @ApiModelProperty(value = "作品名称")
    private String name;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "userId")
    private Integer userId;

    @ApiModelProperty(value = "描述信息")
    private String description;

    @ApiModelProperty(value = "相关连接")
    private String relatedLink;

    @ApiModelProperty(value = "数据类别（填写整型值）")
    private Integer dataCategory;

    @ApiModelProperty(value = "版权图片")
    private String img;

    @ApiModelProperty(value = "发布时间")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;
}
