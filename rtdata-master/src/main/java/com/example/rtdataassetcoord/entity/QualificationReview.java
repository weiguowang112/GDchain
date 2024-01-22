package com.example.rtdataassetcoord.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
 * @since 2023-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="QualificationReview对象", description="")
public class QualificationReview implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资格审查表id")
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @ApiModelProperty(value = "用户id")
    @TableField("userId")
    private Integer userId;

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

    @ApiModelProperty(value = "状态 0提交还没审核 1审核通过成 2审核不通过")
    private Integer status;


}
