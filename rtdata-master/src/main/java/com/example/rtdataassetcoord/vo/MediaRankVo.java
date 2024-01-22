package com.example.rtdataassetcoord.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class MediaRankVo {


    @ApiModelProperty(value = "发布平台")
    private String publishPlatform;

    @ApiModelProperty(value = "粉丝数量")
    private Integer fansNum;

    @ApiModelProperty(value = "本月发布数量")
    private Integer publishNum;

    @ApiModelProperty(value = "评级内容")
    private List<MediaContentVo> mediaContents;

    @ApiModelProperty(value = "文件地址")
    private List<String> filePaths;

}
