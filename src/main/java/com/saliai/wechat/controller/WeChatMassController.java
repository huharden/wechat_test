package com.saliai.wechat.controller;

import com.saliai.wechat.vo.ResponseVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhangzhk
 * @Description:
 * @Date: 2018/11/2 09:57
 * @Modify By:
 */
@RestController
@RequestMapping(value = "/mass")
public class WeChatMassController {

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
    @PostMapping(value = "/preview")
    public ResponseVo preview(String appId, String appSecret, String toUserType, String toUser, String msgType, String msgContent) {

        return null;
    }
}
