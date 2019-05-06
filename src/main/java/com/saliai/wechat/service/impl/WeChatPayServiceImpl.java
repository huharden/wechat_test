package com.saliai.wechat.service.impl;

import com.saliai.wechat.config.AppConfig;
import com.saliai.wechat.dto.OrderApplyPayDto;
import com.saliai.wechat.enums.ResultEnum;
import com.saliai.wechat.service.WeChatPayService;
import com.saliai.wechat.utils.MD5Util;
import com.saliai.wechat.utils.StringUtil;
import com.saliai.wechat.utils.XMLUtil;
import com.saliai.wechat.vo.ResponseVo;
import com.saliai.wechat.vo.UnifiedOrderVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @Description 微信支付服务
 * @author：pengkun
 * @date 2018/12/11 16:32
 * @Version 1.0
 */
@Service
@Slf4j
public class WeChatPayServiceImpl implements WeChatPayService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppConfig appConfig;

    /**
     * @param ip
     * @param orderApplyPayDto
     * @return com.saliai.wechat.vo.ResponseVo
     * @Author pengkun
     * @Description 预支付
     * @Date 16:32 2018/12/11
     * @Param [ip, orderApplyPayDto]
     */
    @Override
    public ResponseVo prePay(String ip, OrderApplyPayDto orderApplyPayDto) {
        log.info(" >>> prePay 请求Ip:{}, 接收参数;{} <<< ",ip,orderApplyPayDto.toString());

        //随机数
        String nonce_str = StringUtil.randomString(16);
        //商品描述，例如：腾讯充值中心-QQ会员充值
        String body = orderApplyPayDto.getDesc();
        //商户订单号，数字、大小写字母_-|* ，字符<32
        String out_trade_no = orderApplyPayDto.getOrderNo();
        //价格，单位：分
        String total_fee = orderApplyPayDto.getTotalFee();
        //终端ip，真实的ip地址
        String spbill_create_ip = ip;

        //交易类型
        String trade_type = "";
        if(StringUtils.isBlank(orderApplyPayDto.getTradeType())){
            trade_type = "JSAPI";
        }else{
            trade_type = orderApplyPayDto.getTradeType();
        }

        SortedMap<Object, Object> map = new TreeMap<>();
        map.put("appid", orderApplyPayDto.getAppid());
        map.put("mch_id", orderApplyPayDto.getMchId());
        map.put("nonce_str", nonce_str);
        map.put("body", body);
        map.put("out_trade_no", out_trade_no);
        map.put("total_fee", total_fee);
        map.put("spbill_create_ip", spbill_create_ip);
        map.put("notify_url", orderApplyPayDto.getNotifyUrl());
        map.put("trade_type", trade_type);
        if("JSAPI".equals(trade_type)){
            map.put("openid",orderApplyPayDto.getOpenId());
        }
        String appendStr = StringUtil.map2String(map) + "&key=" + orderApplyPayDto.getSecret();
        //签名
        String sign = MD5Util.MD5Encode(appendStr, "UTF-8").toUpperCase();
        map.put("sign", sign);

        String requestParams = XMLUtil.getRequestXml(map);
        log.info(" >>> unifiedOrder request xml:{} <<< ",requestParams);
        //微信请求
        ResponseEntity<String> json = restTemplate.postForEntity(appConfig.getWECHAT_UNIFIED_ORDER_URL(), requestParams, String.class);
        log.info(" >>> unifiedOrder response xml:{} <<< ",json.getBody());
        String responseStr = json.getBody();

        //预支付信息
        Map<String, String> backMap = null;
        try {
            backMap = XMLUtil.doXMLParse(responseStr);
        }catch (JDOMException e){
            e.printStackTrace();
        }catch (IOException e1){
            e1.printStackTrace();
        }

        if ("SUCCESS".equals(backMap.get("return_code"))) {
            //预支付成功
            String appId = backMap.get("appid");
            long timeStamp = System.currentTimeMillis() / 1000;
            String nonceStr = backMap.get("nonce_str");
            String prepayId = backMap.get("prepay_id");
            String signType = "MD5";
            String wxPay_Str = "appId=" + appId + "&nonceStr=" + nonceStr + "&package=prepay_id=" + prepayId
                    + "&signType=" + signType + "&timeStamp=" + timeStamp + "&key="
                    + orderApplyPayDto.getSecret();
            String paySign = MD5Util.MD5Encode(wxPay_Str, "UTF-8").toUpperCase();

            UnifiedOrderVo unifiedOrder = new UnifiedOrderVo();
            unifiedOrder.setAppId(appId);
            unifiedOrder.setTimeStamp(timeStamp);
            unifiedOrder.setNonceStr(nonceStr);
            unifiedOrder.setPrepayId(prepayId);
            unifiedOrder.setPaySign(paySign);
            unifiedOrder.setSignType(signType);
            return new ResponseVo(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage(),unifiedOrder);
        } else {
            //预支付调用失败
            String returnMsg = backMap.get("return_msg");
            log.info(" 预支付失败:{} ",returnMsg);
            return new ResponseVo(ResultEnum.PRE_PAY_FAIL.getCode(),ResultEnum.PRE_PAY_FAIL.getMessage(),returnMsg);
        }

    }
}
