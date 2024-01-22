package com.example.rtdataassetcoord.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VoteVo {


    @ApiModelProperty(value = "事件id")
    @TableField("caseId")
    private String caseId;

    @ApiModelProperty(value = "投票操作。1代表通过，2代表不不通过")
    private Integer op;

    @ApiModelProperty(value = "当op为2时需要，填写反对建议")
    private String advice;
}
