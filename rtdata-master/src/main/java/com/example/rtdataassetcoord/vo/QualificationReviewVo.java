package com.example.rtdataassetcoord.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QualificationReviewVo {

    @ApiModelProperty(value = "法人名称")
    @TableField("legalPerson")
    private String legalPerson;

    @ApiModelProperty(value = "法人身份证件号")
    @TableField("legalPersonNumber")
    private String legalPersonNumber;

    @ApiModelProperty(value = "企业统一信用码")
    @TableField("enterpriseCode")
    private String enterpriseCode;

    @ApiModelProperty(value = "机构官网")
    @TableField("orgWebsite")
    private String orgWebsite;

}
