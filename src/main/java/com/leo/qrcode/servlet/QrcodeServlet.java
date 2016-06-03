package com.leo.qrcode.servlet;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leo.qrcode.entity.AccessTokenReturn;
import com.leo.qrcode.entity.Limit_scene;
import com.leo.qrcode.entity.QrcodeTicket;
import com.leo.qrcode.service.OrderService;
import com.leo.qrcode.service.impl.OrderServiceImpl;
import com.leo.qrcode.util.ConfConstant;
import com.leo.qrcode.util.HttpUtil;
import com.leo.qrcode.util.JsonUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/qrcodeServlet")
public class QrcodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * 下单生成支付二维码
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			OrderService orderService = new OrderServiceImpl();

			// 获取统一token
			String accessToken = orderService.getAccessToken(request.getParameter("appid"), request.getParameter("appsecret"));			
			

			//{"action_name": "QR_LIMIT_STR_SCENE", "action_info": {"scene": {"scene_str": "123"}}}
			Map<String,String> scene_str=new HashMap<String,String>();
			scene_str.put("scene_str", request.getParameter("scene_str"));			
			Map<String,Object> scene=new HashMap<String,Object>();
			scene.put("scene", scene_str);
			Map<String,Object> strMap=new HashMap<String,Object>();
			strMap.put("action_info", scene);
			strMap.put("action_name", request.getParameter("action_name"));	
			//方法一：　生成　ｊｓｏｎ字符串　；　这个方法依赖很多的库才能使用；
			//String qrcodeNew = JsonUtils.toJson(strMap);		
			//方法二：　生成　ｊｓｏｎ字符串；　推荐使用此方法；
			JSONObject json_new = JSONObject.fromObject(strMap);//将java对象转换为json对象
			String qrcode_new = json_new.toString();//将json对象转换为字符串
			
			String url = ConfConstant.QRCREATE + accessToken;
			
			
			//方法一：　转移字符拼接成字符串；　　ok;     "{\"action_name\":\"QR_LIMIT_STR_SCENE\",\"action_info\":{\"scene\":{\"scene_str\":\"123\"}}}"
			//方法二：　生成ｊｓｏｎ字符串；
			// 创建二维码ticket  或者说取得　ｔｉｃｋｅｔ
			String returnJson = HttpUtil.sendPost(url,qrcode_new);
			System.out.println("leo qrcode create returnJson is: \n" + returnJson);	
			////将json字符串转换为json对象
			JSONObject jsonObject = JSONObject.fromObject(returnJson);
			
			//将建json对象转换为QrcodeTicket对象
			QrcodeTicket qrcodeTicket = (QrcodeTicket) JSONObject.toBean(jsonObject, QrcodeTicket.class);
			
			System.out.println("leo ticket is: \n" + qrcodeTicket.getTicket());	
			System.out.println("leo url is: \n" + qrcodeTicket.getUrl());	
			
			String qrcodeurl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+qrcodeTicket.getTicket();
			//request.getRequestDispatcher("/WEB-INF/jsp/loginSuccess.jsp").forward(request, response);
			//request.getRequestDispatcher(qrcodeTicket.getUrl()).forward(request, response);
			
			System.out.println("leo qrcodeurl is: \n" + qrcodeurl);
			response.sendRedirect(qrcodeurl);
			

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
