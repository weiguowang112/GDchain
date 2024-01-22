package com.example.rtdataassetcoord.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class MarkAssetDetailDto {
    @ApiModelProperty(value = "数据资产id")
    private String id;

    @ApiModelProperty(value = "机构id")
    private Integer userId;

    @ApiModelProperty(value = "机构名称")
    private String orgName;

    @ApiModelProperty(value = "数据资产名称")
    private String name;

    @ApiModelProperty(value = "发行数量")
    private Integer number;

    @ApiModelProperty(value = "定价")
    private Integer price;

    @ApiModelProperty(value = "封面文件地址")
    private String filePath;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "选择的权益id")
    private List<Integer> rightIds;


}
