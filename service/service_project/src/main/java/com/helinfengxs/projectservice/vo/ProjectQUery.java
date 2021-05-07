package com.helinfengxs.projectservice.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ProjectQUery {
    private String title;

    @ApiModelProperty(value = "测试类型")
    private Integer testType;

    @ApiModelProperty(value = "开始时间")
    private String begin;
    @ApiModelProperty(value = "结束时间")
    private String end;


}
