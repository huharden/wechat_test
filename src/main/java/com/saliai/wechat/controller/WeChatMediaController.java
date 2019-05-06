package com.saliai.wechat.controller;

import com.saliai.wechat.enums.ResultEnum;
import com.saliai.wechat.service.WeChatMediaService;
import com.saliai.wechat.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @Author: zhangzhk
 * @Description:
 * @Date: 2018/11/2 09:56
 * @Modify By:
 */
@RestController
@RequestMapping(value = "/media")
public class WeChatMediaController {

    @Autowired
    private WeChatMediaService weChatMediaService;

    /**
     * 获取上传图文消息内的图片URL
     * @param appId
     * @param appSecret
     * @param file
     * @return
     */
    @PostMapping(value = "/getUploadImgUrl")
    public ResponseVo getUploadImgUrl(String appId, String appSecret, File file) {
        ResponseVo responseVo = new ResponseVo(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), weChatMediaService.uploadImg(appId, appSecret, file));
        return responseVo;
    }

    /**
     * 获取media_id
     * @param appId
     * @param appSecret
     * @param file
     * @param type 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），次数为news，即图文消息
     * @return
     */
    @PostMapping(value = "/getMediaId")
    public ResponseVo getMediaId(String appId, String appSecret, File file, String type) {
        ResponseVo responseVo = new ResponseVo(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), weChatMediaService.mediaId(appId, appSecret, file, type));
        return responseVo;
    }

    @PostMapping(value = "/test")
    public String test(@RequestParam String str) {
        System.out.println(str);
        return null;
    }
}
