package com.example.rtdataassetcoord.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.example.rtdataassetcoord.entity.MediaContent;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MediaRankDetailDto {
    @ApiModelProperty(value = "媒体评级id")
    private String id;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @ApiModelProperty(value = "发布平台")
    private String publishPlatform;

    @ApiModelProperty(value = "粉丝数量")
    private Integer fansNum;

    @ApiModelProperty(value = "本月发布数量")
    private Integer publishNum;

    @ApiModelProperty(value = "本月协同选题数量")
    private Integer selectTopicNum;

    @ApiModelProperty(value = "媒体内容")
    private List<MediaContent>  mediaContents;

    @ApiModelProperty(value = "相关文件")
    private List<FileDto> files;
}
