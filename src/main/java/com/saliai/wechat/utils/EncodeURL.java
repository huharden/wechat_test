package com.saliai.wechat.utils;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @Author: zhangzhk
 * @Description: EncodeURL工具类
 * @Date: 2018/9/21 14:38
 * @Modify By:
 */
@Component
public class EncodeURL {

	public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
	
//	public static void main(String[] args) {
//        String source="http://mem.100healths.cn/Saliai_HM_View/WxCustomer/wxlogin";
//        System.out.println(urlEncodeUTF8(source));
//    }
}
