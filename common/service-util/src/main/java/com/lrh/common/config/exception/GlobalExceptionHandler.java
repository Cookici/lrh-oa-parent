package com.lrh.common.config.exception;

import com.lrh.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ProjectName: lrh-oa-parent
 * @Package: com.lrh.common.config.config.exception
 * @ClassName: GlobalExceptionHandler
 * @Author: 63283
 * @Description: 异常处理
 * @Date: 2023/4/12 22:02
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    //全局异常处理,执行的方法
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(){
        return Result.fail().message("执行全局异常处理");
    }



    //自定义异常处理
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result error(CustomException customException){
        customException.printStackTrace();
        return Result.fail().code(customException.getCode()).message(customException.getMessage());
    }


}
