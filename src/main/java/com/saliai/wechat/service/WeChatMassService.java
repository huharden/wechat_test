package com.saliai.wechat.service;

/**
 * @Author: zhangzhk
 * @Description: 微信群发接口
 * @Date: 2018/11/1 14:00
 * @Modify By:
 */
public interface WeChatMassService {

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
    public String preview(String appId, String appSecret, String toUserType, String toUser, String msgType, String msgContent);
}
