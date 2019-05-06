package com.saliai.wechat.utils;

import com.saliai.wechat.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: zhangzhk
 * @Description:
 * @Date: 2018/9/21 15:10
 * @Modify By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SignUtilTest {

    @Autowired
    private SignUtil signUtil;

    @Autowired
    private AppConfig appConfig;

    @Test
    public void createSign() {
        System.out.println(appConfig.getWECHAT_APP_SECRET());
    }
}