package com.saliai.wechat.utils;

import com.saliai.wechat.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * @Author: zhangzhk
 * @Description: Sign生成工具类
 * @Date: 2018/9/21 14:39
 * @Modify By:
 */
@Component
public class SignUtil {

    @Autowired
    private AppConfig appConfig;
	
	/**
	 * 生成微信支付所需的Sign
	 * @param characterEncoding
	 * @param parameters
	 * @return
	 */
	public String createSign(String characterEncoding, SortedMap<Object, Object> parameters){
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            if(null != v && !"".equals(v) 
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + appConfig.getWECHAT_APP_SECRET());
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }
}
