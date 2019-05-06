package com.saliai.wechat.service.impl;

import com.saliai.wechat.config.AppConfig;
import com.saliai.wechat.service.WeChatAccessTokenService;
import com.saliai.wechat.service.WeChatMassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: zhangzhk
 * @Description:
 * @Date: 2018/11/2 11:48
 * @Modify By:
 */
@Service
public class WeChatMassServiceImpl implements WeChatMassService {

    @Autowired
    private WeChatAccessTokenService weChatAccessTokenService;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 群发消息预览
     * @param appId         公众号appId
     * @param appSecret     公众号appSecret
     * @param toUserType    接收消息的用户类型，touser：用户公众号的openId，towxname：个人微信号
     * @param toUser        openId或者个人微信号
     * @param msgType       群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard
     * @param msgContent    消息类型的内容
     * @return
     */
    @Override
    public String preview(String appId, String appSecret, String toUserType, String toUser, String msgType, String msgContent) {
        String access_token = weChatAccessTokenService.getAccessToken(appId, appSecret);
        String url = appConfig.getWECHAT_PREVIEW_URL().replace("ACCESS_TOKEN", access_token);
        StringBuffer sb = new StringBuffer();
        sb.append("{");

        if ("touser".equals(toUserType)) {
            sb.append("\"touser\"").append(":").append("\"").append(toUser).append("\"").append(",");
        } else {
            sb.append("\"towxname\"").append(":").append("\"").append(toUser).append("\"").append(",");
        }

        if ("mpnews".equals(msgType)) {
            sb.append("\"mpnews\"").append(":{").append("\"media_id\"").append(":").append("\"").append(msgContent).append("\"}").append(",")
              .append("\"msgtype\"").append(":").append("\"").append(msgType).append("\"}");

        } else if ("text".equals(msgType)) {

        } else if ("voice".equals(msgType)) {

        } else if ("image".equals(msgType)) {

        } else if ("mpvideo".equals(msgType)) {

        } else if ("wxcard".equals(msgType)) {

        }

        String result = restTemplate.postForObject(url, sb.toString(), String.class);
        return result;
    }
}
