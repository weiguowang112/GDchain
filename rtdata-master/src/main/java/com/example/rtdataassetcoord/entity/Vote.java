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
 * @since 2023-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Vote对象", description="")
public class Vote implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "vote表id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户id")
    @TableField("userId")
    private Integer userId;

    @ApiModelProperty(value = "事件id")
    @TableField("caseId")
    private String caseId;


    @ApiModelProperty(value = "投票操作。1代表通过，2代表不不通过")
    private Integer op;

    @ApiModelProperty(value = "当op为2时需要，填写反对建议")
    private String advice;


}
