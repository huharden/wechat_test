package com.saliai.wechat.controller;

import com.saliai.wechat.service.WeChatDataCubeService;
import com.saliai.wechat.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhangzhk
 * @Description: 图文分析数据Controller
 * @Date: 2018/11/13 13:41
 * @Modify By:
 */
@RestController
@RequestMapping(value = "/dataCube")
public class WeChatDataCubeController {

    @Autowired
    private WeChatDataCubeService weChatDataCubeService;

    /**
     * 获取图文群发每日数据，最大时间跨度为1
     * @param appId 微信公众号appId
     * @param appSecret 微信公众号密钥
     * @param beginDate 获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”
     * @param endDate   获取数据的结束日期，end_date允许设置的最大值为昨日
     * @param timeSpan  最大时间跨度
     * @return
     */
    @PostMapping(value = "/getArticleSummary")
    public ResponseVo getArticleSummary(String appId, String appSecret, String beginDate, String endDate, String timeSpan) {
        return weChatDataCubeService.getArticleSummary(appId, appSecret, beginDate, endDate, timeSpan);
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
    @PostMapping(value = "/getArticleTotal")
    public ResponseVo getArticleTotal(String appId, String appSecret, String beginDate, String endDate, String timeSpan) {
        return weChatDataCubeService.getArticleTotal(appId, appSecret, beginDate, endDate, timeSpan);
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
    @PostMapping(value = "/getUserRead")
    public ResponseVo getUserRead(String appId, String appSecret, String beginDate, String endDate, String timeSpan) {
        return weChatDataCubeService.getUserRead(appId, appSecret, beginDate, endDate, timeSpan);
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
    @PostMapping(value = "/getUserReadHour")
    public ResponseVo getUserReadHour(String appId, String appSecret, String beginDate, String endDate, String timeSpan) {
        return weChatDataCubeService.getUserReadHour(appId, appSecret, beginDate, endDate, timeSpan);
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
    @PostMapping(value = "/getUserShare")
    public ResponseVo getUserShare(String appId, String appSecret, String beginDate, String endDate, String timeSpan) {
        return weChatDataCubeService.getUserShare(appId, appSecret, beginDate, endDate, timeSpan);
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
    @PostMapping(value = "/getUserShareHour")
    public ResponseVo getUserShareHour(String appId, String appSecret, String beginDate, String endDate, String timeSpan) {
        return weChatDataCubeService.getUserShareHour(appId, appSecret, beginDate, endDate, timeSpan);
    }
}
