package com.saliai.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.saliai.wechat.config.AppConfig;
import com.saliai.wechat.dto.TemplateMessageDto;
import com.saliai.wechat.dto.WeChatTemplateDto;
import com.saliai.wechat.enums.ResultEnum;
import com.saliai.wechat.exception.WeChatException;
import com.saliai.wechat.service.WeChatAccessTokenService;
import com.saliai.wechat.service.WeChatTemplateService;
import com.saliai.wechat.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Description 模板消息服务类
 * @author：pengkun
 * @date 2018/11/27 10:04
 * @Version 1.0
 */
@Service
@Slf4j
public class WeChatTemplateServiceImpl implements WeChatTemplateService {
    @Autowired
    private AppConfig appConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeChatAccessTokenService weChatAccessTokenService;

    /**
     * @param templateMessageDto
     * @param appId
     * @param appSecret
     * @return com.saliai.wechat.vo.ResponseVo
     * @Author pengkun
     * @Description 发送模板消息(公众号)
     * @Date 10:04 2018/11/27
     * @Param [templateMessageDto, appId, appSecret]
     */
    @Override
    public ResponseVo sendTemplateMsg(TemplateMessageDto templateMessageDto, String appId, String appSecret) {
        log.info(" >>> sendTemplateMsg request:{}",templateMessageDto.toString());
        String accessToken = weChatAccessTokenService.getAccessToken(appId,appSecret);

        String url = appConfig.getWECHAT_SEND_TEMPLATE_MESSAGE_URL().replace("ACCESS_TOKEN",accessToken);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json;charset=UTF-8"));
        HttpEntity entity = new HttpEntity(templateMessageDto.toString(), headers);

        JSONObject jsonObject = restTemplate.postForObject(url,entity,JSONObject.class);
        log.info(" >>> sendTemplateMsg  result:"+jsonObject);
        if (null == jsonObject || jsonObject.getIntValue("errcode") != 0){
            throw new WeChatException(ResultEnum.Fail.getCode(),jsonObject.toString());
        }
        ResponseVo responseVo = new ResponseVo(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage(),jsonObject.toString());
        return responseVo;
    }

    /**
     * @param weChatTemplateDto
     * @param appId
     * @param appSecret
     * @return com.saliai.wechat.vo.ResponseVo
     * @Author pengkun
     * @Description 发送模板消息(小程序)
     * @Date 14:06 2018/12/12
     * @Param [weChatTemplateDto, appId, appSecret]
     */
    @Override
    public ResponseVo sendTemplateMsg(WeChatTemplateDto weChatTemplateDto, String appId, String appSecret) {
        log.info(" >>> sendWeChatAppTemplateMsg request:{}",weChatTemplateDto.toString());
        String accessToken = weChatAccessTokenService.getAccessToken(appId,appSecret);

        String url = appConfig.getWECHAT_APP_SEND_TEMPLATE_MESSAGE_URL().replace("ACCESS_TOKEN",accessToken);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json;charset=UTF-8"));
        HttpEntity entity = new HttpEntity(weChatTemplateDto.toString(), headers);

        JSONObject jsonObject = restTemplate.postForObject(url,entity,JSONObject.class);
        log.info(" >>> sendWeChatAppTemplateMsg  result:"+jsonObject);
        if (null == jsonObject || jsonObject.getIntValue("errcode") != 0){
            throw new WeChatException(ResultEnum.Fail.getCode(),jsonObject.toString());
        }
        ResponseVo responseVo = new ResponseVo(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage(),jsonObject.toString());
        return responseVo;
    }
}
