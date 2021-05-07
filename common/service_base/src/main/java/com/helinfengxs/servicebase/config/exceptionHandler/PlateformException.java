package com.helinfengxs.servicebase.config.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlateformException extends RuntimeException{
    //自定义异常状态码
    private Integer code;
    //自定义异常信息
    private String msg;


}
