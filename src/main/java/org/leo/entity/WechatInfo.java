package org.leo.entity;

/**
 * @ClassName: wechatinfo
 * @Description: wechat 相关
 * @author: leo
 * @date: 2016年4月1日 下午1:53:30
 */

public class WechatInfo {
	public static Integer ISUSING = 0;
	
	//三方网站登录 public static final String WECHATQRCONNECT = "https://open.weixin.qq.com/connect/qrconnect";
	//微信公众号 微信浏览器内登录
	public static final String WECHATQRCONNECT = "https://open.weixin.qq.com/connect/oauth2/authorize";
	//public static final String APPID = "wxe71e2c3a1dbe7740"; //买德好账号
	//public static final String SECRET = "0e967a0dcc14d651ce4bf9979e1d8594";  //买德好账号
	public static final String APPID = "wx0d84b978f8a520b4";  //测试帐号
	public static final String SECRET = "84fc4469f117524cd748ec5e84848b10";  //测试帐号

	//重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
	public static final String STATE = "3d6be0a4035d839573b04816624a4leo";

	
	//"http%3A%2F%2Fmaidehao.com%3A8080%2FwechatCallback"
	//public static final String REDIRECTURIOLD = "http://wxdev.maidehao.com/wechatlogin-pox/wechatCallback";
	public static final String REDIRECTURIOLD = "http://15r075i779.imwork.net/wechat-server-dev-pox/wechatCallback";
	public static final String REDIRECTURIOLD3 = "http://15r075i779.imwork.net/wechat-server-dev-pox/wechatCallbackwwwwwwwwwww";
	public static final String REDIRECTURIOLD8 = "http://15r075i779.imwork.net/wechat-server-dev-pox/wechatCallbacktttt";
	//public static final String REDIRECTURI = "http%3A%2F%2Fwxdev.maidehao.com%2Fwechatlogin-pox%2FwechatCallback";

	
	public static final String ACCESSTOKENURI = "https://api.weixin.qq.com/sns/oauth2/access_token";
	public static final String ACCESSTOKENREFRESHURI = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

	public static String ACCESSCODE = "";
	//https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
	
	
	public static String ACCESSTOKEN = "";
	public static String OPENID = "";
	public static String UNIONID = "";
	public static String SCOPE = "";
	public static String REFRESHTOKEN = "";
	public static Integer EXPIRES = 0;
	
	
	//userinfo
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
	public static String nickname = "";
	public static Integer sex = 0;
	public static String province = "";
	public static String city = "";
	public static String country = "";
	public static String headimgurl = "";
	public static String privilege1 = "";
	public static String privilege2 = "";	
	
}
