package com.saliai.wechat.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhangzhk
 * @Description: 全局唯一接口调用凭据access_token服务类
 * @Date: 2018/11/1 13:35
 * @Modify By:
 */
public interface WeChatAccessTokenService {

    /**
     * 获取唯一接口调用凭据access_token
     * @param appId
     * @param appSecret
     * @return
     */
    public String getAccessToken(String appId, String appSecret);
}
