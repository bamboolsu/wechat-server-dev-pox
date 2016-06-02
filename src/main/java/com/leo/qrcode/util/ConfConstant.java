package com.leo.qrcode.util;

/**
 * @ClassName: OrderConstant
 * @Description: 订单接口地址
 * @author: leo
 * @date: 2016年4月2日 下午 
 */
public class ConfConstant {
	
	public static final String TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	
	public static final String QRCREATE = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";

	public static final String SHOWQRCODE = "https://mp.weixin.qq.com/cgi-bin/showqrcode?";
	
	public static final String APPID = "wxe71e2c3a1dbe7740";
	public static final String APPSECRET = "0e967a0dcc14d651ce4bf9979e1d8594";
	public static Integer RESULT = 0;
}
