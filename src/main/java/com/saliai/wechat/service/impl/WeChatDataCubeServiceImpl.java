package com.saliai.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.saliai.wechat.config.AppConfig;
import com.saliai.wechat.enums.ResultEnum;
import com.saliai.wechat.service.WeChatAccessTokenService;
import com.saliai.wechat.service.WeChatDataCubeService;
import com.saliai.wechat.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: zhangzhk
 * @Description: 图文分析数据接口实现
 * @Date: 2018/11/13 13:56
 * @Modify By:
 */
@Service
@Slf4j
public class WeChatDataCubeServiceImpl implements WeChatDataCubeService {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeChatAccessTokenService weChatAccessTokenService;

    /**
     * 获取图文群发每日数据，最大时间跨度为1
     * @param appId 微信公众号appId
     * @param appSecret 微信公众号密钥
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @param timeSpan  最大时间跨度
     * @return
     */
    @Override
    public ResponseVo getArticleSummary(String appId, String appSecret, String beginDate, String endDate, String timeSpan) {
        String access_token = weChatAccessTokenService.getAccessToken(appId, appSecret);
        String url = appConfig.getWECHAT_GET_ARTICLE_SUMMARY_URL().replace("ACCESS_TOKEN", access_token);
        JSONObject paramJson = new JSONObject();
        paramJson.put("begin_date", beginDate);
        paramJson.put("end_date", endDate);
        String result = restTemplate.postForObject(url, paramJson.toString(), String.class);
        ResponseVo responseVo = new ResponseVo(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), result);
        return responseVo;
    }

    /**
     * 获取图文群发总数据，最大时间跨度为1
     * @param appId 微信公众号appId
     * @param appSecret 微信公众号密钥
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @param timeSpan  最大时间跨度
     * @return
     */
    @Override
    public ResponseVo getArticleTotal(String appId, String appSecret, String beginDate, String endDate, String timeSpan) {
        String access_token = weChatAccessTokenService.getAccessToken(appId, appSecret);
        String url = appConfig.getWECHAT_GET_ARTICLE_TOTAL_URL().replace("ACCESS_TOKEN", access_token);
        JSONObject paramJson = new JSONObject();
        paramJson.put("begin_date", beginDate);
        paramJson.put("end_date", endDate);
        String result = restTemplate.postForObject(url, paramJson.toString(), String.class);
        ResponseVo responseVo = new ResponseVo(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), result);
        return responseVo;
    }

    /**
     * 获取图文群发每日数据，最大时间跨度为3
     * @param appId 微信公众号appId
     * @param appSecret 微信公众号密钥
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @param timeSpan  最大时间跨度
     * @return
     */
    @Override
    public ResponseVo getUserRead(String appId, String appSecret, String beginDate, String endDate, String timeSpan) {
        String access_token = weChatAccessTokenService.getAccessToken(appId, appSecret);
        String url = appConfig.getWECHAT_GET_USER_READ_URL().replace("ACCESS_TOKEN", access_token);
        JSONObject paramJson = new JSONObject();
        paramJson.put("begin_date", beginDate);
        paramJson.put("end_date", endDate);
        String result = restTemplate.postForObject(url, paramJson.toString(), String.class);
        ResponseVo responseVo = new ResponseVo(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), result);
        return responseVo;
    }

    /**
     * 获取图文统计分时数据，最大时间跨度为1
     * @param appId 微信公众号appId
     * @param appSecret 微信公众号密钥
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @param timeSpan  最大时间跨度
     * @return
     */
    @Override
    public ResponseVo getUserReadHour(String appId, String appSecret, String beginDate, String endDate, String timeSpan) {
        String access_token = weChatAccessTokenService.getAccessToken(appId, appSecret);
        String url = appConfig.getWECHAT_GET_USER_READ_HOUR_URL().replace("ACCESS_TOKEN", access_token);
        JSONObject paramJson = new JSONObject();
        paramJson.put("begin_date", beginDate);
        paramJson.put("end_date", endDate);
        String result = restTemplate.postForObject(url, paramJson.toString(), String.class);
        ResponseVo responseVo = new ResponseVo(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), result);
        return responseVo;
    }

    /**
     * 获取图文分享转发数据，最大时间跨度为7
     * @param appId 微信公众号appId
     * @param appSecret 微信公众号密钥
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @param timeSpan  最大时间跨度
     * @return
     */
    @Override
    public ResponseVo getUserShare(String appId, String appSecret, String beginDate, String endDate, String timeSpan) {
        String access_token = weChatAccessTokenService.getAccessToken(appId, appSecret);
        String url = appConfig.getWECHAT_GET_USER_SHARE_URL().replace("ACCESS_TOKEN", access_token);
        JSONObject paramJson = new JSONObject();
        paramJson.put("begin_date", beginDate);
        paramJson.put("end_date", endDate);
        String result = restTemplate.postForObject(url, paramJson.toString(), String.class);
        ResponseVo responseVo = new ResponseVo(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), result);
        return responseVo;
    }

    /**
     * 获取图文分享转发分时数据，最大时间跨度为1
     * @param appId 微信公众号appId
     * @param appSecret 微信公众号密钥
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @param timeSpan  最大时间跨度
     * @return
     */
    @Override
    public ResponseVo getUserShareHour(String appId, String appSecret, String beginDate, String endDate, String timeSpan) {
        String access_token = weChatAccessTokenService.getAccessToken(appId, appSecret);
        String url = appConfig.getWECHAT_GET_USER_SHARE_HOUR_URL().replace("ACCESS_TOKEN", access_token);
        JSONObject paramJson = new JSONObject();
        paramJson.put("begin_date", beginDate);
        paramJson.put("end_date", endDate);
        String result = restTemplate.postForObject(url, paramJson.toString(), String.class);
        ResponseVo responseVo = new ResponseVo(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), result);
        return responseVo;
    }
}
