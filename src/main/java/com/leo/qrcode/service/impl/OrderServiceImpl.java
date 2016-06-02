package com.leo.qrcode.service.impl;

import java.io.File;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.leo.qrcode.entity.AccessTokenReturn;
import com.leo.qrcode.service.OrderService;
import com.leo.qrcode.util.ConfConstant;
import com.leo.qrcode.util.HttpUtil;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: OrderServiceImpl
 * @Description: 订单业务处理类
 * @author: leo
 * @date: 2016年4月2日 下午 
 */
public class OrderServiceImpl implements OrderService {
	public String getAccessToken(String appid, String appsecret) {	
		//AccessTokenReturn accessToken = new AccessTokenReturn();
		// 
		String appId = StringUtils.isEmpty(appid)? ConfConstant.APPID : appid;
		String appSecret = StringUtils.isEmpty(appsecret)? ConfConstant.APPID : appsecret;
		String url = ConfConstant.TOKEN;
		url = url + "&appid=" + appId + "&secret=" + appSecret;
		
		// 
		String returnJson = HttpUtil.sendPost(url, "");
		System.out.println("leo AccessToken  result is: \n" + returnJson);
		
		////将json字符串转换为json对象
		JSONObject jsonObject = JSONObject.fromObject(returnJson);
		
		//将建json对象转换为Person对象
		AccessTokenReturn accessToken = (AccessTokenReturn) JSONObject.toBean(jsonObject, AccessTokenReturn.class);
		System.out.println("leo AccessToken is: \n" + accessToken.getAccess_token());
		return accessToken.getAccess_token();
	}
}
