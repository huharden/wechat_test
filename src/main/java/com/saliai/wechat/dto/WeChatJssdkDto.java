package com.saliai.wechat.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description TODO
 * @author：pengkun
 * @date 2019/1/26 13:50
 * @Version 1.0
 */
@Data
public class WeChatJssdkDto implements Serializable {

    private static final long serialVersionUID = 7474470652821639751L;

    /**
     *  公众号appId
     */
    private String appId;

    /**
     *  公众号appSecret
     */
    private String appSecret;

    /**
     *  要使用url的页面
     */
    private String url;
}
