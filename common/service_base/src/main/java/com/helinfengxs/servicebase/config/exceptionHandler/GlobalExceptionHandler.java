package com.helinfengxs.servicebase.config.exceptionHandler;

import com.helinfengxs.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 全局处理异常
     * @param e 全局异常类
     * @return 异常信息
     */

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){

        e.printStackTrace();
        return R.error().message("服务器处理异常");
    }

    /**
     * 自定义异常
     * @param e 自定义异常类
     * @return 自定义异常信息
     */
    @ExceptionHandler(PlateformException.class)
    @ResponseBody
    public R error(PlateformException e){
        log.error(e.getMsg());
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMsg());
    }

}
