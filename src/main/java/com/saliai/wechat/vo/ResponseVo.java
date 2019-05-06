package com.saliai.wechat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: zhangzhk
 * @Description: 全局结果返回
 * @Date: 2018/9/21 15:45
 * @Modify By:
 */
@Data
@AllArgsConstructor
public class ResponseVo<T> {

    private int code;

    private String message;

    private T result;

    public ResponseVo(){

    }
}
