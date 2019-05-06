package com.saliai.wechat.controller;

import com.saliai.wechat.dto.WeChatJssdkDto;
import com.saliai.wechat.service.WeChatJsService;
import com.saliai.wechat.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 获取微信JSSDK
 * @author：pengkun
 * @date 2019/1/26 11:10
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/jssdk")
public class WeChatJSSDKController {

    @Autowired
    private WeChatJsService weChatJsService;

    /**
     * @Author pengkun
     * @Description  获取微信使用JSSDK的配置信息
     * @Date 13:57 2019/1/26
     * @Param [weChatJssdkDto]
     * @return com.saliai.wechat.vo.ResponseVo
     **/
    @PostMapping(value="/getWeChatJsConfigInfo")
    public ResponseVo getWeChatJsConfigInfo(@RequestBody WeChatJssdkDto weChatJssdkDto){
        return weChatJsService.getWeChatJsConfigInfo(weChatJssdkDto.getAppId(),weChatJssdkDto.getAppSecret(),weChatJssdkDto.getUrl());
    }

}
