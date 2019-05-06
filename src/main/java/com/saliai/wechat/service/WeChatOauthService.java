package com.saliai.wechat.service;

import com.saliai.wechat.dto.DecryptWeChatUserDto;
import com.saliai.wechat.vo.ResponseVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhangzhk
 * @Description:
 * @Date: 2018/9/21 15:52
 * @Modify By:
 */
public interface WeChatOauthService {

    /**
     * 获取网页授权access_token
     * @param appid         公众号的唯一标识
     * @param secret        公众号的appsecret
     * @param code          微信回调返回的code参数
     * @param grant_type    填写为authorization_code
     * @return
     */
    public ResponseVo getOauthAccessToken(String appid, String secret, String code, String grant_type);

    /**
     * @Author pengkun
     * @Description  获取网页授权微信用户信息
     * @Date 9:37 2018/11/28
     * @Param [appid, secret, code, grant_type]
     * @return com.saliai.wechat.vo.ResponseVo
     **/
    public ResponseVo getOauthWeChatUserInfo(String appid, String secret, String code, String grant_type);

    /**
     * 刷新网页授权access_token
     * @param appid         公众号的唯一标识
     * @param grant_type    填写为refresh_token
     * @param refresh_token 通过access_token获取到的refresh_token参数
     * @return
     */
    public ResponseVo getRefreshToken(String appid, String grant_type, String refresh_token);

    /**
     * 拉取用户信息(需scope为 snsapi_userinfo)
     * @param access_token  网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     * @param openid        用户的唯一标识
     * @param lang          返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
     * @return
     */
    public ResponseVo getUserInfo(String access_token, String openid, String lang);

    /**
     * 检验授权凭证（access_token）是否有效
     * @param access_token   网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
     * @param openid        用户的唯一标识
     * @return
     */
    public ResponseVo getAuth(String access_token, String openid);

    /**
     * 判断用户是否关注公众号
     * @param appId
     * @param appSecret
     * @param openId
     * @return
     */
    public ResponseVo getSubscribe(String appId, String appSecret, String openId);

    /**
     * 微信小程序解密用户信息
     * @param appId
     * @param appSecret
     * @param decryptWeChatUserDto  解密信息
     * @return
     */
    public ResponseVo getUserInfo(String appId, String appSecret, DecryptWeChatUserDto decryptWeChatUserDto);
}
