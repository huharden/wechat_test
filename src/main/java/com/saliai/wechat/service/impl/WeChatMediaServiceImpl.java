package com.saliai.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.saliai.wechat.config.AppConfig;
import com.saliai.wechat.service.WeChatAccessTokenService;
import com.saliai.wechat.service.WeChatMediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Author: zhangzhk
 * @Description:
 * @Date: 2018/11/1 16:03
 * @Modify By:
 */
@Service
@Slf4j
public class WeChatMediaServiceImpl implements WeChatMediaService {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeChatAccessTokenService weChatAccessTokenService;

    /**
     * 上传图文消息内的图片获取URL
     * @param appId
     * @param appSecret
     * @param file
     * @return
     */
    @Override
    public String uploadImg(String appId, String appSecret, File file) {
        FileSystemResource resource = new FileSystemResource(file);
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("media", resource);

        String access_token = weChatAccessTokenService.getAccessToken(appId, appSecret);
        String url = appConfig.getWECHAT_UPLOAD_IMG_URL().replace("ACCESS_TOKEN", access_token);
        String result = restTemplate.postForObject(url, param, String.class);
        return result;
    }

    /**
     * 视频media_id
     * @param appId
     * @param appSecret
     * @param mediaId
     * @param title
     * @param description
     * @return
     */
    @Override
    public String uploadVideo(String appId, String appSecret, String mediaId, String title, String description) {
        JSONObject paramJson = new JSONObject();
        paramJson.put("media_id", mediaId);
        paramJson.put("title", title);
        paramJson.put("description", description);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity<String> entity = new HttpEntity<>(paramJson.toString(), headers);

        String access_token = weChatAccessTokenService.getAccessToken(appId, appSecret);
        String url = appConfig.getWECHAT_UPLOAD_VIDEO_URL().replace("ACCESS_TOKEN", access_token);
        String result = restTemplate.postForObject(url, entity, String.class);
        return result;
    }

    /**
     * media_id
     * @param appId
     * @param appSecret
     * @param file
     * @param type
     * @return
     */
    @Override
    public String mediaId(String appId, String appSecret, File file, String type) {
        String access_token = weChatAccessTokenService.getAccessToken(appId, appSecret);

        FileSystemResource resource = new FileSystemResource(file);
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("media", resource);
        param.add("type", type);
        param.add("access_token", access_token);

        String url = appConfig.getWECHAT_MEDIA_UPLOAD_URL().replace("ACCESS_TOKEN", access_token);
        String result = restTemplate.postForObject(url, param, String.class);
        return result;
    }

    /**
     * 上传图文消息素材
     * @param appId
     * @param appSecret
     * @param param
     * @return
     */
    @Override
    public String uploadNews(String appId, String appSecret, String param) {
        String access_token = weChatAccessTokenService.getAccessToken(appId, appSecret);
        String url = appConfig.getWECHAT_UPLOAD_NEWS_URL().replace("ACCESS_TOKEN", access_token);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity<String> entity = new HttpEntity<>(param, headers);

        String result = restTemplate.postForObject(url, entity, String.class);
        return result;
    }
}
