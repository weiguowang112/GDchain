package com.example.rtdataassetcoord.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2023-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MediaContent对象", description="")
public class MediaContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "媒体内容表id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "媒体评级时间id")
    @TableField("mediaId")
    private String mediaId;

    @ApiModelProperty(value = "阅读数")
    @TableField("readNum")
    private Integer readNum;

    @ApiModelProperty(value = "评论数")
    @TableField("commentNum")
    private Integer commentNum;

    @ApiModelProperty(value = "转发数")
    @TableField("forwardNum")
    private Integer forwardNum;

    @ApiModelProperty(value = "点赞数")
    @TableField("likeNum")
    private Integer likeNum;

    @ApiModelProperty(value = "发布日期")
    @TableField("publishTime")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

    @ApiModelProperty(value = "链接")
    private String link;


}
