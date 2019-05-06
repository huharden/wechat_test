package com.saliai.wechat.service;

import com.saliai.wechat.dto.OrderApplyPayDto;
import com.saliai.wechat.vo.ResponseVo;

/**
 * @Description 微信支付
 * @author：pengkun
 * @date 2018/12/11 16:28
 * @Version 1.0
 */
public interface WeChatPayService {

    /**
     * @Author pengkun
     * @Description 预支付
     * @Date 16:32 2018/12/11
     * @Param [ip, orderApplyPayDto]
     * @return com.saliai.wechat.vo.ResponseVo
     **/
    public ResponseVo prePay(String ip,OrderApplyPayDto orderApplyPayDto);

}
