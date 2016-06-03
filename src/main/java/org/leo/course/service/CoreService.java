package org.leo.course.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.leo.course.message.resp.Article;
import org.leo.course.message.resp.Image;
import org.leo.course.message.resp.ImageMessage;
import org.leo.course.message.resp.NewsMessage;
import org.leo.course.message.resp.TextMessage;
import org.leo.course.util.AdvancedUtil;
import org.leo.course.util.CommonUtil;
import org.leo.course.util.MenuUtil;
import org.leo.course.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leo.qrcode.util.ConfConstant;
import com.leo.qrcode.util.HttpUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 核心服务类
 * 
 * @author leo
 * @date 2015-09-29
 */
public class CoreService {
	private static Logger log = LoggerFactory.getLogger(MenuUtil.class);
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml格式的消息数据
		String respXml = null;
		// 默认返回的文本消息内容
		String respContent = "Welcome to MDH. 欢迎来到买德好。";
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			System.out.println("leosu:   CoreServlet  receive msgtype is：" + msgType);

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				respContent = "您发送的是文本消息！";
				System.out.println(" leosu  send msg is：" + respContent);
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 语音消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是语音消息！";
			}
			// 视频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "您发送的是视频消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				System.out.println("leosu:   CoreServlet  receive eventType is：" + eventType);
				
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = ("\"终于等到你，还好我没放弃\""
							+ "\n感恩您关注买德好"
							+ "\n从此您的生活和正品德货之间"
							+ "\n只隔着一个买德好的距离"
							+ "\n我们精选将升级您生活品质的德国好物"
							+ "\n连同优质服务一起打包送到您手上"
							+ "\nwww.maidehao.com"
							+ "\n真德不一样，你来就知道！");
					
					String eventKey = requestMap.get("EventKey");
					System.out.println("leosu:   CoreServlet  receive eventKey is：" + eventKey);
					
					respXml =  sendMsgArticle(requestMap);
					return respXml;
					
/*					if (eventKey.equals("qrscene_webcome4")) {
						respContent = ("场景值是： "
								+ eventKey);
					} else if (eventKey.equals("qrscene_webcome")) {
						respContent = ("场景值是： "
								+ eventKey);
					} else if (eventKey.equals("qrscene_footballgame01")) {

					}
					else {
						respContent = ("场景值是： "
								+ eventKey);
					}*/
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 暂不做处理
				}
				//EVENT_TYPE_SCAN
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					String eventKey = requestMap.get("EventKey");
					System.out.println("leosu:   CoreServlet  receive eventKey is：" + eventKey);
					
					respXml =  sendMsgArticle(requestMap);
					return respXml;	
					
/*					if (eventKey.equals("webcome4")) {
						respContent = ("场景值是： "
								+ eventKey);
					} else if (eventKey.equals("webcome")) {
						respContent = ("场景值是： "
								+ eventKey);
					} else if (eventKey.equals("footballgame01")) {

					}
					else  {
						respContent = ("场景值是： "
								+ eventKey);
					}*/
				}
				//myself define welcome,  //never will come here
				else if (eventType.equals("welcome")) {
					respContent = ("Thanks for you MDH.");
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建菜单时的key值对应
					String eventKey = requestMap.get("EventKey");
					// 根据key值判断用户点击的按钮
					
					if (eventKey.equals("findlittlede")) {
						//图片消息
						String imageid = findImageId(requestMap);
						if (imageid != null) {
							Image myImg = new Image();
							myImg.setMediaId(imageid);
							ImageMessage newsMessage = new ImageMessage();
							newsMessage.setToUserName(fromUserName);
							newsMessage.setFromUserName(toUserName);
							newsMessage.setCreateTime(new Date().getTime());
							newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE);
							newsMessage.setImage(myImg);
							respXml = MessageUtil.messageToXml(newsMessage);
							return respXml;
						} else {
							// 文字消息
							respContent = ("想要什么德国好玩的 | 好用的 | 好吃的，小德都知道\n微信号搜150 0032 4984\n客服电话400-801-6708");
						}
						
					} else if (eventKey.equals("membercenter")) {
						//图文消息
						Article article = new Article();
						article.setTitle("买德好");
						article.setDescription("买德好跨境购物网站，。\n\n主要经营德国的 母婴， 电器， 厨具， 文具等。\n\n 欢迎莅临");
						article.setPicUrl("");
						article.setUrl("http://www.maidehao.com");
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						// 创建图文消息
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respXml = MessageUtil.messageToXml(newsMessage);
						return respXml;
					} else if (eventKey.equals("connectKF")) {
						respContent = ("官方微信号: maidehao01"
								+ "\n媒体合作: pr@maidehao.com"
								+ "\n商务合作: bd@maidehao.com"
								+ "\n客服电话: 400-801-6708"
								+ "\n官方商城: http://www.maidehao.com");
					}
				}
			}
			// 设置文本消息的内容
			textMessage.setContent(respContent);
			// 将文本消息对象转换成xml
			respXml = MessageUtil.messageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
	

	
	public static String findImageId(Map<String, String> requestMap) {
		String mediaId = null;
		// 获取接口访问凭证
		String accessToken = CommonUtil.getToken(ConfConstant.APPID, ConfConstant.APPSECRET).getAccessToken();
	    String requestUrl = ConfConstant.BATCHGET_MATERIAL + accessToken;
		

	    Integer perCount = 10;
	    Integer curPos = 0;
		Map<String,String> batchget =new HashMap<String,String>();
		batchget.put("type", "image");		 //"type":TYPE,	
		batchget.put("offset", curPos.toString());		 //"offset":OFFSET,
		batchget.put("count", perCount.toString());		 // "count":COUNT
		JSONObject json_batchget = JSONObject.fromObject(batchget);//将java对象转换为json对象
		String json_batchget_str = json_batchget.toString();//将json对象转换为字符串

		JSONObject partlyMatrialRst = CommonUtil.httpsRequest(requestUrl, "POST", json_batchget_str);

		//todo check the result, 
		if (partlyMatrialRst.containsKey("errcode")) {
			System.out.println("wechat_server_login: userInfo is: " + partlyMatrialRst.toString());
			return null;
		}
				
		Integer total_count = Integer.parseInt(partlyMatrialRst.getString("total_count"));
		Integer left_count = total_count;
		System.out.println("left_count is: " + left_count + "  total_count is: " + total_count + "\n\n");
		System.out.println(" get item is: " + partlyMatrialRst.toString());
		while (left_count>=1) {
			Integer item_count = Integer.parseInt(partlyMatrialRst.getString("item_count"));
			JSONArray items = partlyMatrialRst.getJSONArray("item"); //得到items数组
			
			for (int i = 0; i < item_count; i++) {
				if (items.getJSONObject(i).getString("name").equals("findlittlede.jpg")) {
					mediaId = items.getJSONObject(i).getString("media_id");
					System.out.println("xxxxxxxxxxxxxxxxxx  HAHA WE GOT IT");
					return mediaId;
				}
			}
			curPos += item_count;
			left_count -= item_count;
			batchget =new HashMap<String,String>();
			batchget.put("type", "image");		 //"type":TYPE,	
			batchget.put("offset", curPos.toString());		 //"offset":OFFSET,
			batchget.put("count", perCount.toString());		 // "count":COUNT
			if (left_count > perCount) {
				batchget.put("count", perCount.toString());		 // "count":COUNT
			}
			else {
				batchget.put("count", left_count.toString());		 // "count":COUNT
			}
			json_batchget = JSONObject.fromObject(batchget);//将java对象转换为json对象
			json_batchget_str = json_batchget.toString();//将json对象转换为字符串

			partlyMatrialRst = CommonUtil.httpsRequest(requestUrl, "POST", json_batchget_str);

			//todo check the result, 
			if (partlyMatrialRst.containsKey("errcode")) {
				System.out.println("wechat_server_login: userInfo is: " + partlyMatrialRst.toString());
				return null;
			}
			System.out.println("left_count is: " + left_count + "  total_count is: " + total_count + "\n\n");
			System.out.println(" get item is: " + partlyMatrialRst.toString());
		}
		return null;
	}
	
	public static String sendMsgArticle(Map<String, String> requestMap) {
		// 获取接口访问凭证
		String accessToken = CommonUtil.getToken(ConfConstant.APPID, ConfConstant.APPSECRET).getAccessToken();
		/**
		 * 发送客服消息（文本消息）
		 */
		// 组装文本客服消息
		String jsonTextMsg = AdvancedUtil.makeTextCustomMessage(requestMap.get("FromUserName"),
				"福袋快来！规则必看！"
						+ "\n1.转发下面文章到朋友圈，截图发回这里"
						+ "\n2.抽选三名获价值199元“全家享福袋”"
						+ "\n3.凭截图到【买德好】淘宝店立减10元"
						+ "\n具体详见下面文章末尾");
		AdvancedUtil.sendCustomMessage(accessToken, jsonTextMsg);
		

		
		Article article = new Article();
		article.setTitle("我在德国过得挺好，除了···");
		article.setDescription("多得是，你不知道的事");
		article.setPicUrl("http://mmbiz.qpic.cn/mmbiz/7nxEoPnEmicN0hwYNua2dDYeYeVsaibGTQMbZHoBKq58GWtJA6zUibxKiaiayexaIyNWhwbliajxiaKDbwkJWy1D9kZ2Q/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
		article.setUrl("https://mp.weixin.qq.com/s?__biz=MzI4MTE4MDMwMQ==&mid=502644045&idx=1&sn=bdca01ad6eda4afbbb83f2c7914240ba&scene=1&srcid=0603eoWgZPiJXzDqW6mUyZw4&key=f5c31ae61525f82eabc53964c59c0ac7604b425aba9fad48ff5c5e42fd2548880dab87f38be23240bda4714dac45bba3&ascene=0&uin=MTg2MTU3MDgwMQ%3D%3D&devicetype=iMac+MacBookAir7%2C1+OSX+OSX+10.11.3+build(15D21)&version=11020201&pass_ticket=TH49pJLL85yAXndY4zXa7%2FC23kctUyczW1DBSxR7Sfemxqs1rRyvSz%2Ftd4VxtFo4");
		List<Article> articleList = new ArrayList<Article>();
		articleList.add(article);						
	
		// 创建图文消息
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(requestMap.get("FromUserName"));
		newsMessage.setFromUserName(requestMap.get("ToUserName"));
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		String respXml = MessageUtil.messageToXml(newsMessage);
		return respXml;	
	}
}
