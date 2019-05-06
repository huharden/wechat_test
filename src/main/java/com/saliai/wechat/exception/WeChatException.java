package com.saliai.wechat.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: zhangzhk
 * @Description: 自定义异常
 * @Date: 2018/9/21 15:42
 * @Modify By:
 */
@Data
@AllArgsConstructor
public class WeChatException extends RuntimeException{

    private int code;

    private String message;
}
