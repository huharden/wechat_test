package com.saliai.wechat.service;

import com.saliai.wechat.dto.TemplateMessageDto;
import com.saliai.wechat.dto.WeChatTemplateDto;
import com.saliai.wechat.vo.ResponseVo;

/**
 * @Description 微信模板服务类
 * @author：pengkun
 * @date 2018/11/27 10:01
 * @Version 1.0
 */
public interface WeChatTemplateService {

    /**
     * @Author pengkun
     * @Description  发送模板消息(公众号)
     * @Date 10:04 2018/11/27
     * @Param [templateMessageDto, appId, appSecret]
     * @return com.saliai.wechat.vo.ResponseVo
     **/
    public ResponseVo sendTemplateMsg(TemplateMessageDto templateMessageDto,String appId, String appSecret);

    /**
     * @Author pengkun
     * @Description  发送模板消息(小程序)
     * @Date 14:06 2018/12/12
     * @Param [weChatTemplateDto, appId, appSecret]
     * @return com.saliai.wechat.vo.ResponseVo
     **/
    public ResponseVo sendTemplateMsg(WeChatTemplateDto weChatTemplateDto,String appId, String appSecret);
}
