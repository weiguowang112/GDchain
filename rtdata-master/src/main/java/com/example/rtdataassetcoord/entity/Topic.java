package com.example.rtdataassetcoord.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

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
 * @since 2023-06-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Topic对象", description="")
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "选题表id")
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "媒体机构id")
    @TableField("userId")
    private Integer userId;

    @ApiModelProperty(value = "选题名称")
    @TableField("topicName")
    private String topicName;

    @ApiModelProperty(value = "活动类型")
    @TableField("topicType")
    private String topicType;

    @ApiModelProperty(value = "0代表不需要写作，1代表需要协作")
    private Integer collaborate;


    @ApiModelProperty(value = "钥匙持有者id")
    @TableField("keyHolderId")
    private Integer keyHolderId;

    @ApiModelProperty(value = "状态 0提交还没审核 1审核通过 2审核不通过")
    private Integer status;


    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
