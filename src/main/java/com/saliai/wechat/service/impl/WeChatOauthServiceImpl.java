package com.saliai.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.saliai.wechat.config.AppConfig;
import com.saliai.wechat.dto.DecryptWeChatUserDto;
import com.saliai.wechat.dto.WeChatUserInfo;
import com.saliai.wechat.enums.ResultEnum;
import com.saliai.wechat.exception.WeChatException;
import com.saliai.wechat.service.WeChatAccessTokenService;
import com.saliai.wechat.service.WeChatOauthService;
import com.saliai.wechat.utils.WeChatUtil;
import com.saliai.wechat.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: zhangzhk
 * @Description:
 * @Date: 2018/9/25 10:43
 * @Modify By:
 */
@Service
@Slf4j
public class WeChatOauthServiceImpl implements WeChatOauthService {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeChatAccessTokenService weChatAccessTokenService;

    @Override
    public ResponseVo getOauthAccessToken(String appid, String secret, String code, String grant_type) {
        String url = appConfig.getWECHAT_OAUTH_ACCESS_TOKEN_URL().replace("APPID", appid)
                .replace("SECRET", secret)
                .replace("CODE", code);
        String rtn = restTemplate.postForObject(url, null, String.class);
        ResponseVo responseVo = new ResponseVo(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), rtn);
        return responseVo;
    }

    /**
     * @param appid 公众号的唯一标识
     * @param secret    公众号的appsecret
     * @param code  微信回调返回的code参数
     * @param grant_type    填写为authorization_code
     * @return com.saliai.wechat.vo.ResponseVo
     * @Author pengkun
     * @Description 获取网页授权微信用户信息
     * @Date 9:37 2018/11/28
     * @Param [appid, secret, code, grant_type]
     */
    @Override
    public ResponseVo getOauthWeChatUserInfo(String appid, String secret, String code, String grant_type) {
        String url = appConfig.getWECHAT_OAUTH_ACCESS_TOKEN_URL().replace("APPID", appid)
                .replace("SECRET", secret)
                .replace("CODE", code);
        String rtn = restTemplate.postForObject(url, null, String.class);
        JSONObject jsonObject = JSONObject.parseObject(rtn);
        log.info(jsonObject.toString());
        if(jsonObject.getString("access_token") == null){
            throw new WeChatException(ResultEnum.CODE_USED.getCode(),ResultEnum.CODE_USED.getMessage());
        }
        String access_token = jsonObject.getString("access_token");
        String openid = jsonObject.getString("openid");
        return getUserInfo(access_token,openid,"zh_CN");
    }

    @Override
    public ResponseVo getRefreshToken(String appid, String grant_type, String refresh_token) {
        return null;
    }

    @Override
    public ResponseVo getUserInfo(String access_token, String openid, String lang) {
        String url = appConfig.getWECHAT_SNS_URL().replace("ACCESS_TOKEN",access_token)
                .replace("OPENID",openid)
                .replace("LANG",lang);
        String str = restTemplate.getForObject(url,String.class);
        WeChatUserInfo weChatUserInfo = JSON.parseObject(str,WeChatUserInfo.class);
//        ResponseVo responseVo = new ResponseVo(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), weChatUserInfo);
        ResponseVo responseVo = new ResponseVo(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), str);
        return responseVo;
    }

    @Override
    public ResponseVo getAuth(String access_token, String openid) {
        return null;
    }

    /**
     * 判断用户是否关注公众号
     * @param appId
     * @param appSecret
     * @param openId
     * @return
     */
    @Override
    public ResponseVo getSubscribe(String appId, String appSecret, String openId) {
        String access_token = weChatAccessTokenService.getAccessToken(appId, appSecret);
        String url = appConfig.getWECHAT_SUBSCRIBE_URL().replace("ACCESS_TOKEN", access_token)
                .replace("OPENID", openId);
        JSONObject jsonObject = JSONObject.parseObject(restTemplate.postForObject(url, null, String.class));
        log.info("【用户是否关注公众号信息】-> {}", jsonObject.toString());
        ResponseVo responseVo = new ResponseVo(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), jsonObject.get("subscribe"));
        return responseVo;
    }

    /**
     * 微信小程序解密用户信息
     *
     * @param appId
     * @param appSecret
     * @param decryptWeChatUserDto 解密信息
     * @return
     */
    @Override
    public ResponseVo getUserInfo(String appId, String appSecret, DecryptWeChatUserDto decryptWeChatUserDto) {
        JSONObject return_json = getWxProgramSessionKey(appId,appSecret,decryptWeChatUserDto.getCode());
        log.info(" >>> 获取 session_key 和 openid返回结果:{} <<<",return_json);
        String session_key = return_json.getString("session_key");
        JSONObject jsonObject =WeChatUtil.getUserInfo(decryptWeChatUserDto.getEncryptedData(),decryptWeChatUserDto.getIv(),session_key);
        log.info(" >>> 解密微信数据为:{} <<< ",jsonObject);
        WeChatUserInfo weChatUser = new WeChatUserInfo();
        weChatUser.setOpenid(jsonObject.getString("openId"));
        weChatUser.setNickname(jsonObject.getString("nickName"));
        weChatUser.setCountry(jsonObject.getString("country"));
        weChatUser.setProvince(jsonObject.getString("province"));
        weChatUser.setCity(jsonObject.getString("city"));
        weChatUser.setHeadimgurl(jsonObject.getString("avatarUrl"));
        weChatUser.setSex(jsonObject.getString("gender"));
        return new ResponseVo(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), weChatUser);
    }


    private JSONObject getWxProgramSessionKey(String appId, String appSecret,String code){
        log.info(">>> getWxProgramSessionKey start <<< ");
        JSONObject jsonObject = null;
        try {
            String url = appConfig.getWECHAT_APP_GET_SESSIONKEY_URL().replace("APPID", appId)
                    .replace("APPSECRET", appSecret)
                    .replace("CODE", code);
            String result_json = restTemplate.postForEntity(url, null, String.class).getBody();
            jsonObject = JSONObject.parseObject(result_json);
        } catch (Exception e) {
            log.info(">>> getWxProgramSessionKey error <<< ");
            e.printStackTrace();
        }
        log.info(">>> getWxProgramSessionKey end <<< ");
        return jsonObject;
    }
}
