package com.saliai.wechat;

import com.saliai.wechat.config.AppConfig;
import com.saliai.wechat.service.WeChatAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @Author: zhangzhk
 * @Description:
 * @Date: 2018/9/25 11:15
 * @Modify By:
 */
@RestController
public class TestController {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeChatAccessTokenService weChatAccessTokenService;

    @PostMapping(value = "/uploadTest")
    public void test(@RequestParam MultiValueMap<String, Object> map) {
        try {
            ByteArrayResource byteArrayResource = (ByteArrayResource) map.get("file");
            FileOutputStream outputStream = new FileOutputStream("/Users/saliai/local/tmp/test.jpg");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("media", map.get("file"));

        String access_token = weChatAccessTokenService.getAccessToken("wx5bed90f0cebdec2e", "96236d8dbbc5a156e3fd09813ccaafcb");
        String url = appConfig.getWECHAT_UPLOAD_IMG_URL().replace("ACCESS_TOKEN", access_token);
        String result = restTemplate.postForObject(url, param, String.class);

        System.out.println(result);

//        String url = "http://localhost:8080/oauth2/auth/{0}/{1}";
//        restTemplate.postForEntity(MessageFormat.format(url, 1111, 2222), null, null);
    }
}
