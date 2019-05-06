package com.saliai.wechat.dto;

import lombok.Data;

/**
 * @Description 解密微信小程序用户信息
 * @author：pengkun
 * @date 2018/12/20 16:15
 * @Version 1.0
 */
@Data
public class DecryptWeChatUserDto {
    /**
     * 明文,加密数据
     */
    private String encryptedData;
    /**
     * 加密算法的初始向量
     */
    private String iv;

    private String code;

}
