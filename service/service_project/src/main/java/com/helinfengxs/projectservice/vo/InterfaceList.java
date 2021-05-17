package com.helinfengxs.projectservice.vo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="interface列表实体类", description="用户接受查询关联用例表后数据")
public class InterfaceList {
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
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    @ApiModelProperty(value = "用例总数")
    private Integer interfaceCases;
}
