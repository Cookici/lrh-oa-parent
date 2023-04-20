package com.lrh.common.config.exception;

import com.lrh.common.result.ResultCodeEnum;
import lombok.Data;

/**
 * @ProjectName: lrh-oa-parent
 * @Package: com.lrh.common.config.exception
 * @ClassName: CustomException
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/4/12 22:20
 */

@Data
public class CustomException extends RuntimeException{

    private Integer code;   //状态码
    private String msg;     //描述信息


    public CustomException(Integer code,String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }


    /**
     * 接受枚举类对象
     * @param resultCodeEnum
     */
    public CustomException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.msg= resultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

}
