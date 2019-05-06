package com.saliai.wechat.controller;

import com.saliai.wechat.dto.DecryptWeChatUserDto;
import com.saliai.wechat.service.WeChatOauthService;
import com.saliai.wechat.vo.ResponseVo;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: zhangzhk
 * @Description:
 * @Date: 2018/11/2 09:56
 * @Modify By:
 */
@RestController
@RequestMapping(value = "/oauth2")
public class WeChatOauthController {

    @Autowired
    private WeChatOauthService weChatOauthService;

    @PostMapping(value = "/accessToken/{appid}/{secret}/{code}/{grant_type}")
    public ResponseVo getOauthAccessToken(@PathVariable String appid, @PathVariable String secret,
                                          @PathVariable String code, @PathVariable String grant_type) {
        return weChatOauthService.getOauthAccessToken(appid, secret, code, grant_type);
    }


    @PostMapping(value = "/getOauthWeChatUserInfo/{appid}/{secret}/{code}/{grant_type}")
    public ResponseVo getOauthWeChatUserInfo(@PathVariable String appid, @PathVariable String secret,
                                             @PathVariable String code, @PathVariable String grant_type) {
        return weChatOauthService.getOauthWeChatUserInfo(appid, secret, code, grant_type);
    }


    /**
     * @Author pengkun
     * @Description  获取微信用户信息
     * @Date 9:33 2018/11/28
     * @Param [access_token, openid, lang]
     * @return com.saliai.wechat.vo.ResponseVo
     **/
    @PostMapping(value = "/getUserInfo/{access_token}/{openid}/{lang}")
    public ResponseVo getUserInfo(@PathVariable String access_token, @PathVariable String openid,
                                          @PathVariable String lang) {
        return weChatOauthService.getUserInfo(access_token, openid, lang);
    }

    /**
     * 判断用户是否关注公众号
     * @param appId
     * @param appSecret
     * @param openId
     * @return
     */
    @PostMapping(value = "/getSubscribe/{appId}/{appSecret}/{openId}")
    public ResponseVo getSubscribe(@PathVariable String appId, @PathVariable String appSecret, @PathVariable String openId) {
        return weChatOauthService.getSubscribe(appId, appSecret, openId);
    }

    /**
     * @Author pengkun
     * @Description  微信小程序解密用户信息
     * @Date 16:42 2018/12/20
     * @Param [appId, appSecret, decryptWeChatUserDto]
     * @return com.saliai.wechat.vo.ResponseVo
     **/
    @PostMapping(value = "/getUserInfo/{appId}/{appSecret}")
    public ResponseVo getUserInfo(@PathVariable String appId, @PathVariable String appSecret, @RequestBody DecryptWeChatUserDto decryptWeChatUserDto){
        return weChatOauthService.getUserInfo(appId, appSecret, decryptWeChatUserDto);
    }
}
