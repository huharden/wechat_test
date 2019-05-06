package com.saliai.wechat.controller;

import com.saliai.wechat.dto.TemplateMessageDto;
import com.saliai.wechat.dto.WeChatTemplateDto;
import com.saliai.wechat.service.WeChatTemplateService;
import com.saliai.wechat.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 微信模板控制类
 * @author：pengkun
 * @date 2018/11/27 9:58
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/template")
public class WeChatTemplateController {

    @Autowired
    private WeChatTemplateService weChatTemplateService;

    /**
     * @Author pengkun
     * @Description  发送模板消息（公众号）
     * @Date 10:28 2018/11/27
     * @Param [templateMessageDto, appId, appSecret]
     * @return com.saliai.wechat.vo.ResponseVo
     **/
    @PostMapping(value="/sendTemplateMsg/{appId}/{appSecret}")
    public ResponseVo sendTemplateMsg(@RequestBody TemplateMessageDto templateMessageDto, @PathVariable String appId, @PathVariable String appSecret) {
        ResponseVo responseVo = weChatTemplateService.sendTemplateMsg(templateMessageDto, appId, appSecret);
        return responseVo;
    }

    /**
     * @Author pengkun
     * @Description  发送模板消息（小程序）
     * @Date 14:09 2018/12/12
     * @Param [weChatTemplateDto, appId, appSecret]
     * @return com.saliai.wechat.vo.ResponseVo
     **/
    @PostMapping(value="/sendWeChatAppTemplateMsg/{appId}/{appSecret}")
    public ResponseVo sendWeChatAppTemplateMsg(@RequestBody WeChatTemplateDto weChatTemplateDto, @PathVariable String appId, @PathVariable String appSecret) {
        ResponseVo responseVo = weChatTemplateService.sendTemplateMsg(weChatTemplateDto, appId, appSecret);
        return responseVo;
    }

}
