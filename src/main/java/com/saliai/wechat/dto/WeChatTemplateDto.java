package com.saliai.wechat.dto;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Map;

/**
 * @Description 微信小程序模板消息请求实体
 * @author：pengkun
 * @date 2018/12/12 14:00
 * @Version 1.0
 */
@Data
public class WeChatTemplateDto {
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
     * formId或prepay_id
     */
    private String formId;
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
        jsObj.put("form_id",formId);
        jsObj.put("page", url);

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
