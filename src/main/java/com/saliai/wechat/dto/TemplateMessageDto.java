package com.saliai.wechat.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Map;

/**
 * @Description 模板发送
 * @author：pengkun
 * @date 2018/11/27 9:44
 * @Version 1.0
 */
@Data
public class TemplateMessageDto {
    /**
     * 粉丝id
     */
    private String openid;
    /**
     * 模板id
     */
    private String templateId;
    /**
     * 链接
     */
    private String url;
    /**
     * 颜色
     */
    private String color = "#173177";
    /**
     * 参数数据
     */
    private Map<String,String> dataMap;

    @Override
    public String toString(){
        JSONObject jsObj = new JSONObject();
        jsObj.put("touser", openid);
        jsObj.put("template_id", templateId);
        jsObj.put("url", url);

        JSONObject data = new JSONObject();
        if(dataMap != null){
            for(String key : dataMap.keySet()){
                JSONObject item = new JSONObject();
                item.put("value", dataMap.get(key));
                item.put("color", color);
                data.put(key,item);
            }
        }
        jsObj.put("data", data);
        return jsObj.toString();
    }

}
