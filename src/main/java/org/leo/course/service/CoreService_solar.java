package org.leo.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
					if (eventKey.equals("aboutus")) {
						Article article = new Article();
						article.setTitle("关于所罗门");
						article.setDescription("所罗门能源科技（杭州）有限公司是户用光伏发电系统的服务商,通过S2C工程云服务管理平台，最大化地降低用户使用清洁能源的成本。\n\n"
								+ "所罗门2014年7月成立于杭州市滨江区国家海创基地，是杭州市政府在新能源领域重点引进培育的“移动互联网+智慧能源”海归创业项目。创业团队具有海内外博士、硕士、MBA教育经历，"
								+ "具备平台发展所需的电力系统、EPC工程管理、IT技术、市场开发、投融资服务等所需要的经验和能力。" 
								+ "\n\n 所罗门音译于SolarGate，有“开启solar（太阳能）之门、共享绿色未来”的寓意。项目团队致力创新于在传统工程项目管理基础上的“互联网+分布式太阳能电站开发平台”业务，通过电站开发流程标准化的建立、企业内外部资源的互补，"
								+ "提高开发效率、降低新能源使用成本和入行门槛，为屋顶人和投资人提供投融资、EPC工程管理和电站运维等一站式服务解决方案，打造分布式电站领域内、国内一流的一体化管理平台。");
						article.setPicUrl("http://www.solargrid.com.cn//Public/Images/front/pc/news_head7.png");
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

					} else if (eventKey.equals("joinus")) {
						Article article = new Article();
						article.setTitle("关于所罗门");
						article.setDescription("  为降低清洁能源的使用成本，所罗门以标准化行业解决方案为主线、以移动APP端（含PC端）“我有屋顶、我要投资、我会安装、我会设计、我有产品等”为节点来构建“完善的商业运营流程线”，通过内外部资源的有效利用和互补来降低成本、提高效率，为户用使用清洁能源提供一揽子解决方案。。\n\n"
								+ "  所罗门通过“居民光伏S2C工程云服务管理平台”为市场提供一站式服务。不管您是行家里手、还是职场新贵，只要您自信有能力承担以下其中之一的部分，就可以加入我们，一起共创居民光伏事业的未来：\n\n"
								+ "     我有屋顶：具有推广户用光伏电站的技能\n" 
								+ "     我会安装：具备安装户用光伏电站的经验\n"
								+ "     我会设计：具备设计户用定制化方案的经验\n\n"
								+ "简历发送：HR@solargrid.com.cn");
						article.setPicUrl("http://www.solargrid.com.cn//Public/Images/front/pc/news_head7.png");
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

					} else if (eventKey.equals("solorschool")) {
						// todo
					} else if (eventKey.equals("connectUS")) {
						respContent = ("电话: 0571-56265338"
								+ "\n电话：400-856-5338"
								+ "\n传真：0571-5626 5339 "
								+ "\n地址：浙江省杭州市滨江区六和路368号海创基地"
								+ "\nE-mail: office@solargrid.com.cn"
								+ "\n官方网站: http://www.solargrid.com.cn");
					}  else if (eventKey.equals("buildprocess")) {
						//todo
						respContent = ("信息完善中......"
								+ "\n ............. "
								+ "\n电话: 0571-5626 5338"
								+ "\n电话：400-856-5338"
								+ "\n传真：0571-5626 5339 "
								+ "\n地址：浙江省杭州市滨江区六和路368号海创基地"
								+ "\nE-mail: office@solargrid.com.cn"
								+ "\n官方网站: http://www.solargrid.com.cn");
					} else {
						respContent = ("信息完善中...... we will do best"
								+ "\n ............. "
								+ "\n电话: 0571-5626 5338"
								+ "\n电话：400-856-5338"
								+ "\n传真：0571-5626 5339 "
								+ "\n地址：浙江省杭州市滨江区六和路368号海创基地"
								+ "\nE-mail: office@solargrid.com.cn"
								+ "\n官方网站: http://www.solargrid.com.cn");
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
