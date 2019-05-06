package com.saliai.wechat.service;

import java.io.File;

/**
 * @Author: zhangzhk
 * @Description: 微信多媒体上传接口
 * @Date: 2018/11/1 14:03
 * @Modify By:
 */
public interface WeChatMediaService {

    /**
     * 上传图文消息内的图片获取URL
     * @param appId
     * @param appSecret
     * @param file
     * @return
     */
    public String uploadImg(String appId, String appSecret, File file);

    /**
     * 上传视频
     * @param appId
     * @param appSecret
     * @param mediaId
     * @param title
     * @param description
     * @return
     */
    public String uploadVideo(String appId, String appSecret, String mediaId, String title, String description);

    /**
     * media_id
     * @param appId
     * @param appSecret
     * @param file
     * @param type 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），次数为news，即图文消息
     * @return
     */
    public String mediaId(String appId, String appSecret, File file, String type);

    /**
     * 上传图文消息素材
     * @param appId
     * @param appSecret
     * @param param
     * @return
     */
    public String uploadNews(String appId, String appSecret, String param);
}
