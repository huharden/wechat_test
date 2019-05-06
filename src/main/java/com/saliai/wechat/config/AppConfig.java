package com.saliai.wechat.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: zhangzhk
 * @Description: 初始化配置类
 * @Date: 2018/9/21 15:23
 * @Modify By:
 */
@Configuration
@PropertySource(value = "classpath:properties/wechat.properties")
@Getter
public class AppConfig {

    @Value("${wechat.APP_ID}")
    private String WECHAT_APP_ID;

    @Value("${wechat.APP_SECRET}")
    private String WECHAT_APP_SECRET;

    @Value("${wechat.API_SECRET}")
    private String WECHAT_API_SECRET;

    @Value("${wechat.MCH_ID}")
    private String WECHAT_MCH_ID;

    @Value("${wechat.OAUTH_ACCESS_TOKEN_URL}")
    private String WECHAT_OAUTH_ACCESS_TOKEN_URL;

    @Value("${wechat.OAUTH_GRANT_TYPE}")
    private String WECHAT_OAUTH_GRANT_TYPE;

    @Value("${wechat.SNS_URL}")
    private String WECHAT_SNS_URL;

    @Value("${wechat.ACCESS_TOKEN_URL}")
    private String WECHAT_ACCESS_TOKEN_URL;

    @Value("${wechat.UNIFIED_ORDER_URL}")
    private String WECHAT_UNIFIED_ORDER_URL;

    @Value("${wechat.UPLOAD_IMG_URL}")
    private String WECHAT_UPLOAD_IMG_URL;

    @Value("${wechat.MEDIA_UPLOAD_URL}")
    private String WECHAT_MEDIA_UPLOAD_URL;

    @Value("${wechat.UPLOAD_NEWS_URL}")
    private String WECHAT_UPLOAD_NEWS_URL;

    @Value("${wechat.PREVIEW_URL}")
    private String WECHAT_PREVIEW_URL;

    @Value("${wechat.UPLOAD_VIDEO_URL}")
    private String WECHAT_UPLOAD_VIDEO_URL;

    @Value("${wechat.GET_ARTICLE_SUMMARY_URL}")
    private String WECHAT_GET_ARTICLE_SUMMARY_URL;

    @Value("${wechat.GET_ARTICLE_TOTAL_URL}")
    private String WECHAT_GET_ARTICLE_TOTAL_URL;

    @Value("${wechat.GET_USER_READ_URL}")
    private String WECHAT_GET_USER_READ_URL;

    @Value("${wechat.GET_USER_READ_HOUR_URL}")
    private String WECHAT_GET_USER_READ_HOUR_URL;

    @Value("${wechat.GET_USER_SHARE_URL}")
    private String WECHAT_GET_USER_SHARE_URL;

    @Value("${wechat.GET_USER_SHARE_HOUR_URL}")
    private String WECHAT_GET_USER_SHARE_HOUR_URL;

    @Value("${wechat.SEND_TEMPLATE_MESSAGE_URL}")
    private String WECHAT_SEND_TEMPLATE_MESSAGE_URL;

    @Value("${wechat.SUBSCRIBE_URL}")
    private String WECHAT_SUBSCRIBE_URL;

    @Value("${wechat.APP_SEND_TEMPLATE_MESSAGE_URL}")
    public String WECHAT_APP_SEND_TEMPLATE_MESSAGE_URL;

    @Value("${wechat.APP_GET_SESSIONKEY_URL}")
    private String WECHAT_APP_GET_SESSIONKEY_URL;

    @Value("${wechat.GET_JSAPI_TICKET}")
    private String JSAPI_TICKET;

    @Value("${wechat.CREATE_QRCODE_TICKET_URL}")
    private String createQrCodeUrl;
}
