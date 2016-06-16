package org.leo.service.impl;

import org.leo.entity.AccessTokenReturn;

import org.leo.entity.WechatInfo;
import org.leo.service.AccessToken;
import org.leo.util.HttpUtil;
//import org.leo.util.XmlUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class AccessTokenImpl implements AccessToken {
	

	
	/**
	 * 
	 * @Title: placeOrder
	 * @Description: 得到access token
	 * @param code
	 *            微信开发平台带回来的code
	 * @param out_trade_no
	 *            商户订单号
	 * @return
	 * @throws Exception
	 * @return: OrderReturn
	 */
	@Override
	public void getAccessToken(String code, String state) throws Exception {		
		//访问微信， 得到access token；
		//https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
		String parameter = "appid=" + WechatInfo.APPIDSOLORMAN
				     + "&secret=" + WechatInfo.SECRETSOLORMAN
				     + "&code=" + code
				     + "&grant_type=authorization_code";
		String returnJson = HttpUtil.doPostStr(WechatInfo.ACCESSTOKENURI, parameter);
		
		//System.out.println(" leosu   the access token result is: " + returnJson);
		System.out.println(" leosu  we have got the json");

		try {
			//AccessTokenReturn accessToken = (AccessTokenReturn) XmlUtil.xml2Object(returnXml, AccessTokenReturn.class);
			System.out.println(" leosu here will get jsonobject");
			//得到json 对象；
			JSONObject jsonObject = JSONObject.fromObject(returnJson);

			//json对象转换为  自定义的类；
			System.out.println(" leosu here will get AccessTokenReturn");
	        AccessTokenReturn accessToken = (AccessTokenReturn) JSONObject.toBean(jsonObject, AccessTokenReturn.class);

			// 保存到静态实体类变量中。。。。。
			WechatInfo.ACCESSTOKEN = accessToken.getAccess_token();
			WechatInfo.EXPIRES = Integer.parseInt(accessToken.getExpires_in());
			WechatInfo.OPENID = accessToken.getOpenid();
			WechatInfo.UNIONID = accessToken.getUnionid();
			WechatInfo.SCOPE = accessToken.getScope();
			WechatInfo.REFRESHTOKEN = accessToken.getRefresh_token();
			System.out.println(" leosu WechatInfo.ACCESSTOKEN is: "
			                   +  WechatInfo.ACCESSTOKEN  + "; \n leo... and WechatInfo.UNIONID is: " + WechatInfo.UNIONID);					
		} catch (Exception e1) {
			System.out.println(" leosu , here exception..................");
			e1.printStackTrace();
		}
	}
	

	@Override
	public void refreshAccessToken() throws Exception {
		try {
			//refresh  access token  刷新有效期；
			// https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN
			String refreshParameter = "appid=" + WechatInfo.APPID + "&grant_type=refresh_token&refresh_token=" + WechatInfo.REFRESHTOKEN;
			String returnRefreshJson = HttpUtil.doPostStr(WechatInfo.ACCESSTOKENREFRESHURI, refreshParameter);
			System.out.println(" leosu here will get refresh access token: \n " + returnRefreshJson);			
		} catch (Exception e1) {
			System.out.println(" leosu , here exception..................");
			e1.printStackTrace();
		}
	}
	

	@Override
	public void checkAccessToken() throws Exception {
		try {
			//检验授权凭证（access_token）是否有效
			//https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID
			String checkAccesstoken = HttpUtil.doPostStr("https://api.weixin.qq.com/sns/auth", "access_token=" + WechatInfo.ACCESSTOKEN
					+ "&openid=" + WechatInfo.OPENID);
			System.out.println(" leosu here will get refresh access check whether can use or not?: \n " + checkAccesstoken);
	
		} catch (Exception e1) {
			System.out.println(" leosu , here exception..................");
			e1.printStackTrace();
		}
	}

	@Override
	public void getUserInfo() throws Exception {
		try {
			//获取用户个人信息（UnionID机制）
			//https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID
			String userInfo = HttpUtil.doPostStr("https://api.weixin.qq.com/sns/userinfo", "access_token=" + WechatInfo.ACCESSTOKEN
					+ "&openid=" + WechatInfo.OPENID);
			System.out.println(" leosu here will get personal info: \n " + userInfo);
			
			//解析userinfo
			JSONObject userObject = JSONObject.fromObject(userInfo);
			WechatInfo.nickname = userObject.getString("nickname");
			WechatInfo.province = userObject.getString("province");
			WechatInfo.headimgurl = userObject.getString("headimgurl");
			WechatInfo.city = userObject.getString("city");
			WechatInfo.sex = userObject.getInt("sex");
			JSONArray privileges = userObject.getJSONArray("privilege"); //得到likes数组
			for(int i = 0 ; i < privileges.size(); i++) {
			WechatInfo.privilege1 = privileges.getString(i);
			}

			
			/* { 
			"openid":"OPENID",
			"nickname":"NICKNAME",
			"sex":1,
			"province":"PROVINCE",
			"city":"CITY",
			"country":"COUNTRY",
			"headimgurl": "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
			"privilege":[
			"PRIVILEGE1", 
			"PRIVILEGE2"
			],
			"unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"

			} */
		} catch (Exception e1) {
			System.out.println(" leosu , here exception..................");
			e1.printStackTrace();
		}
	}
	

}
