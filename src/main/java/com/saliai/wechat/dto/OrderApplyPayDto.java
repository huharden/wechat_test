package com.saliai.wechat.dto;

import lombok.Data;

/**
 * @Description 订单申请预支付请求实体
 * @author：pengkun
 * @date 2018/7/19 10:13
 * @Version 1.0
 */
@Data
public class OrderApplyPayDto {

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 商品描述
     */
    private String desc;

    /**
     * 订单金额（分）
     */
    private String totalFee;

    /**
     * 成功回调地址
     */
    private String notifyUrl;

    /**
     * 公众号appid
     */
    private String appid;

    /**
     * 商户号安全码
     */
    private String secret;

    /**
     * 公众号绑定的商户号
     */
    private String mchId;

    /**
     * 用户openId
     */
    private String openId;

    /**
     * 支付方式（JSAPI--JSAPI支付（或小程序支付）、NATIVE--Native支付、APP--app支付，MWEB--H5支付）
     */
    private String tradeType;
}
