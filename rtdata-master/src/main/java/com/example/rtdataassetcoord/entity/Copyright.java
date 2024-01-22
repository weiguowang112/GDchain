package com.example.rtdataassetcoord.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2023-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Copyright对象", description="")
public class Copyright implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "元数据id")
    @TableId(value = "id", type = IdType.UUID)
    private String id;

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

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


}
