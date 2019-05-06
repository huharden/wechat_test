package com.saliai.wechat.controller;

import com.saliai.wechat.enums.ResultEnum;
import com.saliai.wechat.service.WeChatAccessTokenService;
import com.saliai.wechat.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhangzhk
 * @Description: 全局唯一接口调用凭据access_token
 * @Date: 2018/11/27 09:23
 * @Modify By:
 */
@RestController
public class WeChatAccessTokenController {

    @Autowired
    private WeChatAccessTokenService weChatAccessTokenService;

    @PostMapping(value = "/accessToken/{appId}/{appSecret}")
    public ResponseVo getAccessToken(@PathVariable String appId, @PathVariable String appSecret) {
        String access_token = weChatAccessTokenService.getAccessToken(appId, appSecret);
        ResponseVo responseVo = new ResponseVo(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), access_token);
        return responseVo;
    }
}
