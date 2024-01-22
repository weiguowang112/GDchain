package com.example.rtdataassetcoord.dto;


import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserDto {

    private Integer id;

    private Integer spaceId;

    private String uuid;

    private String orgName;

    private String username;

    private String phone;

    private String password;

    private String headImg;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;


    @ApiModelProperty(value = "评级")
    private Integer rank;

    @ApiModelProperty(value = "评分")
    private BigDecimal grade;


}
