package com.saliai.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.saliai.wechat.config.AppConfig;
import com.saliai.wechat.exception.WeChatException;
import com.saliai.wechat.service.WeChatAccessTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @Author: zhangzhk
 * @Description: 全局唯一接口调用凭据access_token服务实现类
 * @Date: 2018/11/1 13:41
 * @Modify By:
 */
@Service
@Slf4j
public class WeChatAccessTokenServiceImpl implements WeChatAccessTokenService {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取access_token
     * @param appId
     * @param appSecret
     * @return
     */
    @Override
    public String getAccessToken(@PathVariable String appId, @PathVariable String appSecret) {
        String access_token;
        String expires_in;
        if (redisTemplate.hasKey(appId + "_accessToken")) {
            if (redisTemplate.getExpire(appId + "_accessToken", TimeUnit.SECONDS) < 30) {
                log.info("【access_token】即将过期，重新获取。");
                JSONObject jsonObject = getAccessTokenByHttps(appId, appSecret);
                access_token = jsonObject.getString("access_token");
                expires_in = jsonObject.getString("expires_in");
                log.info("【access_token】存入redis缓存。");
                redisTemplate.opsForValue().set(appId + "_accessToken", jsonObject.getString("access_token"), Integer.parseInt(expires_in), TimeUnit.SECONDS);
            } else {
                log.info("【access_token】未过期，从缓存中读取。");
                access_token = redisTemplate.opsForValue().get(appId + "_accessToken").toString();
            }
        } else {
            log.info("【access_token】不存在，调用 getAccessTokenByHttps 方法获取。");
            JSONObject jsonObject = getAccessTokenByHttps(appId, appSecret);
            log.info("【access_token】获取结果：{}", jsonObject.toString());
            access_token = jsonObject.getString("access_token");
            expires_in = jsonObject.getString("expires_in");
            log.info("【access_token】存入redis缓存。");
            redisTemplate.opsForValue().set(appId + "_accessToken", access_token, Integer.parseInt(expires_in), TimeUnit.SECONDS);
        }

        return access_token;
    }

    /**
     * Https请求获取唯一接口调用凭据access_token
     * @param appId
     * @param appSecret
     * @return
     */
    public JSONObject getAccessTokenByHttps(String appId, String appSecret) {
        String url = appConfig.getWECHAT_ACCESS_TOKEN_URL().replace("APPID", appId)
                .replace("APPSECRET", appSecret);
        JSONObject jsonObject = restTemplate.postForObject(url, null, JSONObject.class);
        if (jsonObject.getString("access_token") == null || "".equals(jsonObject.getString("access_token")))
            throw new WeChatException(Integer.parseInt(jsonObject.getString("errcode")), jsonObject.getString("errmsg"));
        return jsonObject;
    }

}
