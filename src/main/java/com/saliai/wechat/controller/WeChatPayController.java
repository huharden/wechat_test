package com.saliai.wechat.controller;

import com.saliai.wechat.dto.OrderApplyPayDto;
import com.saliai.wechat.dto.TemplateMessageDto;
import com.saliai.wechat.service.WeChatPayService;
import com.saliai.wechat.utils.IPAddrUtil;
import com.saliai.wechat.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description 微信支付
 * @author：pengkun
 * @date 2018/12/11 16:20
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/pay")
public class WeChatPayController {

    @Autowired
    private WeChatPayService weChatPayService;

    @PostMapping(value="/prePay")
    public ResponseVo sendTemplateMsg(@RequestBody OrderApplyPayDto orderApplyPayDto, HttpServletRequest request) {
        String ip = IPAddrUtil.getIpAddr(request);
        ResponseVo responseVo = weChatPayService.prePay(ip,orderApplyPayDto);
        return responseVo;
    };
}
