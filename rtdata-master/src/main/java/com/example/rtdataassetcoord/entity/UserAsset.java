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
 * @since 2023-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserAsset对象", description="")
public class UserAsset implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户资产表")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "购买人userid")
    @TableField("userId")
    private Integer userId;

    @ApiModelProperty(value = "数据资产id")
    @TableField("assetId")
    private String assetId;

    @ApiModelProperty(value = "状态：0代表已购买未使用，1代表已使用")
    private Integer status;


}
