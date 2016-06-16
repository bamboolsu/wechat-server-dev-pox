package org.leo.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.solr.common.params.CommonParams.EchoParamStyle;
import org.leo.course.message.resp.Article;
import org.leo.course.message.resp.NewsMessage;
import org.leo.course.message.resp.TextMessage;
import org.leo.course.util.MenuUtil;
import org.leo.course.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 核心服务类
 * 
 * @author leo
 * @date 2015-09-29
 */
public class CoreService_solar {
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
			System.out.println(" leosu  receive msgtype is：" + msgType);
			log.warn(" leosu  receive msgtype is：" + msgType);

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
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = ("\"终于等到你，还好我没放弃\""
							+ "\n感恩您关注所罗门能源科技"
							+ "\n从此您的生活和新能源之间"
							+ "\n只隔着一个屋顶的距离"
							+ "\n清洁能源升级您生活品质"
							+ "\n优质服务一起打包送到您手上"
							+ "\nhttp://www.solargrid.com.cn/"
							+ "\n真的不一样，你来就知道！");
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 暂不做处理
				}
				//EVENT_TYPE_SCAN
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					respContent = ("You are scanning MDH.");
				}
				//myself define welcome, 
				else if (eventType.equals("welcom")) {
					respContent = ("Thanks for you MDH.");
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建菜单时的key值对应
					String eventKey = requestMap.get("EventKey");
					// 根据key值判断用户点击的按钮
					if (eventKey.equals("membercenter")) {
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
								+ "\n官方网站: http://www.maidehao.com");
					}
					else {
						respContent = ("信息完善中......"
								+ "\n ............. "
								+ "\n电话: 0571-5626 5338"
								+ "\n电话：400-856-5338"
								+ "\n传真：0571-5626 5339 "
								+ "\n地址：浙江省杭州市滨江区六和路368号海创基地"
								+ "\nE-mail: office@solargrid.com.cn"
								+ "\n官方网站: http://www.maidehao.com");
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
}
