package com.example.rtdataassetcoord.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
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
@ApiModel(value="Media对象", description="")
public class Media implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "媒体评级主键id")
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "用户id")
    @TableField("userId")
    private Integer userId;

    @ApiModelProperty(value = "发布平台")
    @TableField("publishPlatform")
    private String publishPlatform;

    @ApiModelProperty(value = "粉丝数量")
    @TableField("fansNum")
    private Integer fansNum;

    @ApiModelProperty(value = "本月发布数量")
    @TableField("publishNum")
    private Integer publishNum;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "状态。0已提交还没审核，1审核通过，2审核不通过")
    private Integer status;


}
