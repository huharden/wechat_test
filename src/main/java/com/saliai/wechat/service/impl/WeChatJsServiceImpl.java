package com.saliai.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.saliai.wechat.config.AppConfig;
import com.saliai.wechat.enums.ResultEnum;
import com.saliai.wechat.exception.WeChatException;
import com.saliai.wechat.service.WeChatAccessTokenService;
import com.saliai.wechat.service.WeChatJsService;
import com.saliai.wechat.utils.WeiChatSignUtil;
import com.saliai.wechat.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description 微信公众号获取jssdk配置信息
 * @author：pengkun
 * @date 2019/1/26 11:26
 * @Version 1.0
 */
@Service
@Slf4j
public class WeChatJsServiceImpl implements WeChatJsService {

    @Autowired
    private WeChatAccessTokenService weChatAccessTokenService;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    public static final int TIME_OUT = 7200;

    /**
     * @param appId 微信公众号id
     * @param appSecret 微信公众号秘钥
     * @param url 需要使用JSSDK的页面
     * @return com.saliai.wechat.vo.ResponseVo
     * @Author pengkun
     * @Description
     * @Date 11:23 2019/1/26
     * @Param [appId, appSecret]
     */
    @Override
    public ResponseVo getWeChatJsConfigInfo(String appId, String appSecret,String url) {
        String jsapi_ticket = "";
        if(redisTemplate.hasKey(appId + "_jsTicket")){
            if(redisTemplate.getExpire(appId + "_jsTicket", TimeUnit.SECONDS) < 30){
                log.info("【jsTicket】即将过期，重新获取。");
                jsapi_ticket = getJsTicket(appId,appSecret);
                log.info("【jsTicket】获取结果：{}", jsapi_ticket);
                log.info("【jsTicket】存入redis缓存。");
                redisTemplate.opsForValue().set(appId + "_jsTicket", jsapi_ticket, TIME_OUT, TimeUnit.SECONDS);
            }else {
                log.info("【access_token】未过期，从缓存中读取。");
                jsapi_ticket = redisTemplate.opsForValue().get(appId + "_jsTicket").toString();
            }
        }else {
            jsapi_ticket = getJsTicket(appId,appSecret);
            log.info("【jsTicket】获取结果：{}", jsapi_ticket);
            log.info("【jsTicket】存入redis缓存。");
            redisTemplate.opsForValue().set(appId + "_jsTicket", jsapi_ticket, TIME_OUT, TimeUnit.SECONDS);
        }

        Map<String,String> map = WeiChatSignUtil.sign(jsapi_ticket,url);
        map.put("appId",appId);
        return new ResponseVo(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage(),map);
    }


    public String getJsTicket(String appId, String appSecret){
        //获取 access_token
        String accessToken = weChatAccessTokenService.getAccessToken(appId,appSecret);
        //获取 jsapi_ticket
        String url = appConfig.getJSAPI_TICKET().replace("ACCESS_TOKEN",accessToken);
        JSONObject jsonObject = restTemplate.getForEntity(url,JSONObject.class).getBody();
        log.info("获取jssdk返回信息：{}",jsonObject.toString());
        if(!"0".equals(jsonObject.getString("errcode"))){
            throw new WeChatException(jsonObject.getIntValue("errcode"),jsonObject.getString("errmsg"));
        }
        return jsonObject.getString("ticket");
    }
}
