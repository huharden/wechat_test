package com.saliai.wechat.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description 微信用户信息实体类
 * @author：pengkun
 * @date 2018/11/27 17:16
 * @Version 1.0
 */
@Data
public class WeChatUserInfo implements Serializable {
    private String openid;
    private String nickname;
    private String sex;
    private String city;
    private String province;
    private String country;
    private String headimgurl;

}
