package com.saliai.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.saliai.wechat.config.AppConfig;
import com.saliai.wechat.enums.ResultEnum;
import com.saliai.wechat.service.WeChatAccessTokenService;
import com.saliai.wechat.service.WeChatQrCodeService;
import com.saliai.wechat.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Description TODO
 * @author：pengkun
 * @date 2019/2/16 11:00
 * @Version 1.0
 */
@Service
@Slf4j
public class WeChatQrCodeServiceImpl implements WeChatQrCodeService {

    @Autowired
    private WeChatAccessTokenService weChatAccessTokenService;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @param appId
     * @param appSecret
     * @param jsonObject
     * @return com.saliai.wechat.vo.ResponseVo
     * @Author pengkun
     * @Description 创建二维码
     * @Date 11:00 2019/2/16
     * @Param [appId, appSecret, jsonObject]
     */
    @Override
    public ResponseVo create(String appId, String appSecret, JSONObject jsonObject) {
        log.info(" >>> create request:{}",jsonObject.toString());
        String accessToken = weChatAccessTokenService.getAccessToken(appId,appSecret);

        String url = appConfig.getCreateQrCodeUrl().replace("ACCESS_TOKEN",accessToken);
        JSONObject result = restTemplate.postForObject(url, jsonObject,JSONObject.class);
        log.info(" >>> create response:{}",result);
        return new ResponseVo(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage(),result.toString());
    }
}
