package com.example.rtdataassetcoord.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2023-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DataCategory对象", description="")
public class DataCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "数据类别主键id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private Integer id;

    @ApiModelProperty(value = "类别名称")
    private String categoryName;


}
