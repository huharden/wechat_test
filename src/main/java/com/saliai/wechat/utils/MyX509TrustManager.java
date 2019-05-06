package com.saliai.wechat.utils;

import java.security.cert.CertificateException;

import javax.net.ssl.X509TrustManager;

/**
 * @Author: zhangzhk
 * @Description: 证书工具类
 * @Date: 2018/9/21 14:39
 * @Modify By:
 */
public class MyX509TrustManager implements X509TrustManager {

    // 检查客户端证书
	@Override
	public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
			String authType) throws CertificateException {
		// TODO Auto-generated method stub
		
	}

	// 检查服务器端证书
	@Override
	public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
			String authType) throws CertificateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
