package com.saliai.wechat.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.saliai.wechat.config.AppConfig;
import com.saliai.wechat.service.WeChatAccessTokenService;
import com.saliai.wechat.service.WeChatMediaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;

/**
 * @Author: zhangzhk
 * @Description:
 * @Date: 2018/11/2 13:37
 * @Modify By:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WeChatMassServiceImplTest {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeChatAccessTokenService weChatAccessTokenService;

    @Autowired
    private WeChatMediaService weChatMediaService;

    @Test
    public void preview() {
        String appId = "wx5bed90f0cebdec2e";
        String appSecret = "96236d8dbbc5a156e3fd09813ccaafcb";
        String access_token = weChatAccessTokenService.getAccessToken(appId, appSecret);

        //  上传图文消息内的图片获取URL
        File file1 = new File("/Users/saliai/local/tmp/白后官网图片/images/白后官网_02.jpg");
        File file2 = new File("/Users/saliai/local/tmp/白后官网图片/images/白后官网_03.jpg");

        File video = new File("/Users/saliai/local/tmp/1541382164396319.mp4");

        JSONObject jsonImg1 = JSONObject.parseObject(weChatMediaService.uploadImg(appId, appSecret, file1));
        JSONObject jsonImg2 = JSONObject.parseObject(weChatMediaService.uploadImg(appId, appSecret, file2));

        //  视频素材
        String videoMedia = weChatMediaService.mediaId(appId, appSecret, video, "video");
        String title = "群发视频消息Demo";
        String description = "群发失效消息...";
        JSONObject jsonVideo = JSONObject.parseObject(weChatMediaService.uploadVideo(appId, appSecret, videoMedia, title, description));


        String img1_url = jsonImg1.getString("url");
        String img2_url = jsonImg2.getString("url");

        //  缩略图
        File thumbFile = new File("/Users/saliai/local/tmp/白后官网图片/logo.png");
        JSONObject thumbMediaIdJson = JSONObject.parseObject(weChatMediaService.mediaId(appId, appSecret, thumbFile, "thumb"));
        String thumbMediaId = thumbMediaIdJson.getString("thumb_media_id");

        //  上传图文消息素材
        String url = appConfig.getWECHAT_UPLOAD_NEWS_URL().replace("ACCESS_TOKEN", access_token);
        JSONArray jsonArray = new JSONArray();

        //  展示封面的消息
        JSONObject thumbJson = new JSONObject();
        thumbJson.put("thumb_media_id", thumbMediaId);
        thumbJson.put("author", "赛莱拉");
        thumbJson.put("title", "展示封面的群发消息Demo");
        thumbJson.put("content_source_url", "www.qq.com");

        StringBuffer content = new StringBuffer();
        content.append("<img src=\"").append(img1_url).append("\">")
                .append("<p>").append("展示封面的图片1描述...").append("</p>")
                .append("<img src=\"").append(img2_url).append("\">")
                .append("<p>").append("展示封面的图片2描述...").append("</p>")
                .append("<video width=\"100%\" controls=\"controls\" preload=\"meta\" " +
                        "x-webkit-airplay=\"true\" webkit-playsinline=\"true\" playsinline=\"true\" " +
                        "x5-video-player-type=\"h5\" x5-video-player-fullscreen=\"true\" >\n" +
                        "\n" +
                        "  <source src=\"https://v.qq.com/x/cover/j0f3p5zyzwt7cuc/z0328b31qh0.html\" type=\"video/mp4\">\n" +
                        "Your browser does not support the video tag.\n" +
                        "</video>\n");

        thumbJson.put("content", content);
        thumbJson.put("digest", "展示封面的群发消息");
        thumbJson.put("show_cover_pic", 1);

        //  不展示封面的消息
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("thumb_media_id", thumbMediaId);
        jsonObject1.put("author", "赛莱拉");
        jsonObject1.put("title", "不展示封面的群发消息Demo");
        jsonObject1.put("content_source_url", "www.qq.com");

        StringBuffer content1 = new StringBuffer();
        content1.append("<img src=\"").append(img1_url).append("\">")
                .append("<p>").append("不展示封面的图片1描述...").append("</p>")
                .append("<img src=\"").append(img2_url).append("\">")
                .append("<p>").append("不展示封面的图片2描述...").append("</p>");

        jsonObject1.put("content", content1);
        jsonObject1.put("digest", "不展示封面的群发消息");
        jsonObject1.put("show_cover_pic", 0);

        jsonArray.add(thumbJson);
        jsonArray.add(jsonObject1);


        String tmp = "{\"articles\":" + jsonArray.toString() + "}";

        String uploadNewsResult = weChatMediaService.uploadNews(appId, appSecret, tmp);

        JSONObject resultJson = JSONObject.parseObject(uploadNewsResult);

        System.out.println("【上传图文消息素材】返回结果：" + resultJson.toString());

        //  预览
        JSONObject mediaIdJson = new JSONObject();
        String previewUrl = appConfig.getWECHAT_PREVIEW_URL().replace("ACCESS_TOKEN", access_token);

        //  微信用户
//        String postParam = "{\"touser\":\"opMI2wreQNmeWo0FnKiHvvlR8oyE\","
//                         + "\"mpnews\":{\"media_id\":\"" + resultJson.getString("media_id") + "\"},"
//                         + "\"msgtype\":\"mpnews\"}";

        //  微信号
        String postParam = "{\"towxname\":\"Archer_zzk\","
                         + "\"mpnews\":{\"media_id\":\"" + resultJson.getString("media_id") + "\"},"
                         + "\"msgtype\":\"mpnews\"}";

        System.out.println("【postParam】：" + postParam);

        String previewResult = restTemplate.postForObject(previewUrl, postParam, String.class);

        System.out.println("【预览接口返回结果】：" + previewResult);

    }
}