package com.saliai.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.saliai.wechat.vo.ResponseVo;

/**
 * @Description 二维码服务
 * @author：pengkun
 * @date 2019/2/16 10:58
 * @Version 1.0
 */
public interface WeChatQrCodeService {
    /**
     * @Author pengkun
     * @Description  创建二维码
     * @Date 11:00 2019/2/16
     * @Param [appId, appSecret, jsonObject]
     * @return com.saliai.wechat.vo.ResponseVo
     **/
    public ResponseVo create(String appId,String appSecret,JSONObject jsonObject);
}
