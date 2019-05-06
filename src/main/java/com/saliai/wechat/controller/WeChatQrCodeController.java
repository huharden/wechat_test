package com.saliai.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.saliai.wechat.service.WeChatQrCodeService;
import com.saliai.wechat.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 二维码
 * @author：pengkun
 * @date 2019/2/16 10:54
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/qrCode")
public class WeChatQrCodeController {

    @Autowired
    private WeChatQrCodeService weChatQrCodeService;

    /**
     * @Author pengkun
     * @Description  创建二维码
     * @Date 11:14 2019/2/16
     * @Param [appId, appSecret, jsonObject]
     * @return com.saliai.wechat.vo.ResponseVo
     **/
    @PostMapping(value = "/create/{appId}/{appSecret}")
    public ResponseVo create(@PathVariable String appId, @PathVariable String appSecret,@RequestBody JSONObject jsonObject) {
        return weChatQrCodeService.create(appId, appSecret, jsonObject);
    }
}
