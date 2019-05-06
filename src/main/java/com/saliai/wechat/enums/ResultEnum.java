package com.saliai.wechat.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: zhangzhk
 * @Description:
 * @Date: 2018/9/21 15:49
 * @Modify By:
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS(200, "请求成功"),

    Fail(400, "请求失败"),

    CODE_USED(10000,"code已使用"),

    PRE_PAY_FAIL(10001,"预支付失败")
    ;

    private int code;

    private String message;
}
