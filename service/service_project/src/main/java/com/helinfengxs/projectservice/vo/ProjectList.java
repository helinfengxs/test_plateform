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
@ApiModel(value="Project列表对象", description="")
public class ProjectList {
    @ApiModelProperty(value = "项目id")
    private String id;

    @ApiModelProperty(value = "项目名称")
    private String title;

    @ApiModelProperty(value = "测试类型:1-接口测试,2-webui测试,3-app测试,4-性能测试")
    private String testType;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;

    @ApiModelProperty(value = "是否删除，0未删除，1删除")
    @TableLogic(value = "0",delval = "1")
    @TableField(select = false)
    private Integer status;
    @ApiModelProperty(value = "备注信息")
    private String describtion;
    @ApiModelProperty(value = "接口总数")
    private Integer interfaceCount;
    @ApiModelProperty(value = "用例总数")
    private Integer caseCount;
}
