package com.saliai.wechat.utils;

import java.util.Map;
import java.util.Random;
import java.util.SortedMap;

/**
 * @author pengkun
 * @version 1.0
 */
public class StringUtil {
    private static final String NUMBER = "0123456789";
    private static final String ARRSTR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String map2String(SortedMap<Object, Object> map) {
        if (map.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Object, Object> entity : map.entrySet()) {
            String key = (String) entity.getKey();
            String value = (String)entity.getValue();
            sb.append(key);
            sb.append("=");
            sb.append(value);
            sb.append("&");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    /**
     * 返回随机数，包含数字、字母
     * @param length 长度
     * @return
     */
    public static String randomString(int length) {
        return randomNonce(length, ARRSTR);
    }

    /**
     * 返回随机数，只包含数字
     * @param length 长度
     * @return
     */
    public static String randomNumber(int length) {
        return randomNonce(length, NUMBER);
    }

    /**
     * 返回随机数
     * @param length 长度
     * @param content 包含的字符集
     * @return
     */
    private static String randomNonce(int length, String content) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = new Random().nextInt(length);
            builder.append(content.charAt(randomIndex));
        }
        return builder.toString();
    }
}
