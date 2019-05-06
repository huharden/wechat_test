package com.saliai.wechat.service;

import com.saliai.wechat.vo.ResponseVo;

/**
 * @Description 微信公众号获取jssdk配置信息
 * @author：pengkun
 * @date 2019/1/26 11:21
 * @Version 1.0
 */
public interface WeChatJsService {

    /**
     * @Author pengkun
     * @Description  
     * @Date 11:23 2019/1/26
     * @Param [appId, appSecret,url]
     * @return com.saliai.wechat.vo.ResponseVo
     **/
    public ResponseVo getWeChatJsConfigInfo(String appId,String appSecret,String url);
}
