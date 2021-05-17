package com.helinfengxs.projectservice.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 接口信息表
 * </p>
 *
 * @author helinfeng
 * @since 2021-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TInterface对象", description="接口信息表")
public class TInterface implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "接口id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "接口名称")
    private String interfaceName;

    @ApiModelProperty(value = "接口地址")
    private String interfaceAddress;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "状态：0未删除，1删除")
    private Integer status;

    @ApiModelProperty(value = "项目id")
    private String projectId;




}
