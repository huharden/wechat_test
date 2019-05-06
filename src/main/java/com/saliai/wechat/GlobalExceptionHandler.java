package com.saliai.wechat;

import com.saliai.wechat.exception.WeChatException;
import com.saliai.wechat.vo.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: zhangzhk
 * @Description: 全局异常处理
 * @Date: 2018/9/21 15:43
 * @Modify By:
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * 捕捉自定义异常
     * @param request
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(WeChatException.class)
    public ResponseVo wechatExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
//        response.setStatus(HttpStatus.BAD_REQUEST.value());
        WeChatException weChatException = (WeChatException) e;
        return new ResponseVo(weChatException.getCode(), weChatException.getMessage(), null);
    }

    /**
     * 捕捉运行时异常
     * @param request
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseVo runtimeExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
//        response.setStatus(HttpStatus.BAD_REQUEST.value());
        RuntimeException runtimeException = (RuntimeException) e;
        return new ResponseVo(500, runtimeException.getMessage(), null);
    }
}
