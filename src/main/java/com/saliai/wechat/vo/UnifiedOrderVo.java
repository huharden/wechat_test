package com.saliai.wechat.vo;

import lombok.Data;

/**
 * @Description 统一下单返回的数据包装类
 * @author：pengkun
 * @date 2018/12/10 09:54
 * @Version 1.0
 */
@Data
public class UnifiedOrderVo {
    /**
     * 公众号appid
     */
    private String appId;

    /**
     * 时间戳，秒
     */
    private long timeStamp;

    /**
     * 随机字符串
     */
    private String nonceStr;

    /**
     * 预支付id
     */
    private String prepayId;

    /**
     * 加密方式
     */
    private String signType = "MD5";

    /**
     * 支付签名
     */
    private String paySign;
}
