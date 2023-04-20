package com.lrh.common.result;

import lombok.Getter;

/**
 * @ProjectName: lrh-oa-parent
 * @Package: com.lrh.common.result
 * @ClassName: ResultCodeEnum
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/4/11 14:08
 */

@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"成功"),
    FAIL(201, "失败"),
    LOGIN_ERROR(208,"认证失败");

    private Integer code;
    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
