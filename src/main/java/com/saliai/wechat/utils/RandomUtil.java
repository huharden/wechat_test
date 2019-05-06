package com.saliai.wechat.utils;

import java.util.Random;

/**
 * @Author: zhangzhk
 * @Description: 随机数工具类
 * @Date: 2018/9/21 14:39
 * @Modify By:
 */
public class RandomUtil {
	public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	/** 
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     *  
     * @param length 随机字符串长度 
     * @return 随机字符串
     */
    public static String generateString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return sb.toString();
    }
}
