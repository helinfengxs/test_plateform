package com.helinfengxs.projectservice.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class InterfacePageQuery {
    @ApiModelProperty(value = "接口名称")
    private String interfaceName;
    @ApiModelProperty(value = "项目id")
    private String projectId;
    @ApiModelProperty(value = "开始时间")
    private String begin;
    @ApiModelProperty(value = "结束时间")
    private String end;
}
