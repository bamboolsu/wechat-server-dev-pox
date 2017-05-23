package org.leo.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.leo.course.message.resp.Article;
import org.leo.course.message.resp.Image;
import org.leo.course.message.resp.ImageMessage;
import org.leo.course.message.resp.KfMessage;
import org.leo.course.message.resp.NewsMessage;
import org.leo.course.message.resp.TextMessage;
import org.leo.course.util.AdvancedUtil;
import org.leo.course.util.CommonUtil;
import org.leo.course.util.MenuUtil;
import org.leo.course.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.leo.qrcode.util.ConfConstant;

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
				String recvContent = requestMap.get("Content");
				if (recvContent.contains("碧然德") || recvContent.contains("滤水壶") || recvContent.contains("水壶")
						|| recvContent.toLowerCase().contains("brita".toLowerCase())
						|| recvContent.contains("滤水") || recvContent.contains("滤芯")
						|| recvContent.toLowerCase().contains("shuihu".toLowerCase())
						|| recvContent.toLowerCase().contains("lvxin".toLowerCase())
						|| recvContent.toLowerCase().contains("lvshui".toLowerCase())
						){
					//图文消息
					Article article = new Article();
					article.setTitle("Brita碧然德 MARELLA 滤水壶使用说明");
					article.setDescription("本小编昨天夜观天象，掐指一算，算到了一位对生活很讲究的人类，今天要来看这份brita滤水壶的说明书了。早就准备好啦，跟着小编的节奏黑喂狗~ \n");
					article.setPicUrl("http://mmbiz.qpic.cn/mmbiz/7nxEoPnEmicMcficetA2yfCzzC6NWZetmsR8abWsulrkWY4No84kT5LiclkwSoXiaceqtKRKDHicPOkju5dQrUQc0Hg/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
					article.setUrl("https://mp.weixin.qq.com/s?__biz=MzI4MTE4MDMwMQ==&mid=502644080&idx=1&sn=b954600c9caa596295a01f68059e61cd&scene=0&previewkey=vK8YmSUaFyGUu4xgidgCdcNS9bJajjJKzz%2F0By7ITJA%3D&key=77421cf58af4a653a626824ea53029e7763cce15e90ea7b70af3f9466f64ab4b32d5f2832a44ded83f9d53ab56b3ffe6&ascene=0&uin=MTg2MTU3MDgwMQ%3D%3D&devicetype=iMac+MacBookAir7%2C1+OSX+OSX+10.11.3+build(15D21)&version=11020201&pass_ticket=t6fZ5QvGIBuMRHskITNZ11QnCuL5QX9GGM%2BYa3t7jMTtQ1%2BXw9bMLc0EBm%2FdVXEO");
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
				} /*else if (recvContent.contains("博世") || recvContent.contains("厨师机") || recvContent.contains("料理机")
						|| recvContent.toLowerCase().contains("boshi".toLowerCase())
						|| recvContent.contains("搅拌机") || recvContent.contains("4405")
						|| recvContent.toLowerCase().contains("bosch".toLowerCase())
						|| recvContent.toLowerCase().contains("chushiji".toLowerCase())
						|| recvContent.toLowerCase().contains("liaoliji".toLowerCase())
						|| recvContent.toLowerCase().contains("jiaobanji".toLowerCase())
						){
					//图文消息
					Article article = new Article();
					article.setTitle("Bosch博世MUM4405厨师机使用说明必看");
					article.setDescription("现在在家自己动手做蛋糕、甜点、面食的吃货们越来越多了，根据自己的口味来随心调整，爱加什么料就加什么料，简直就跟一次充满惊喜的探险一样！但俗话说的好，吃东西五分钟，做东西两小时，一想起要抡着胳膊打蛋、揉面团、打奶油，退堂的小鼓就咚咚咚响起来了。所以，吃货们，还不快掌声欢迎你们的福音—— \n");
					article.setPicUrl("http://mmbiz.qpic.cn/mmbiz/7nxEoPnEmicPdUpH83QwaZ6hhXueCe7OgsDolKy8bSvqcEmyfKuB9ibvLZrdPSpiaqSfO0NbtVDSaU8fNMRwWF6dQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
					article.setUrl("http://shuomingshu.maidehao.com/Bosch.htm");
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
				}*/ else if ( recvContent.contains("料理机")
						|| recvContent.contains("搅拌机") || recvContent.contains("4405")
						|| recvContent.toLowerCase().contains("liaoliji".toLowerCase())
						|| recvContent.toLowerCase().contains("jiaobanji".toLowerCase())
						){
					//图文消息
					Article article = new Article();
					article.setTitle("Bosch博世MUM4405厨师机使用说明必看");
					article.setDescription("现在在家自己动手做蛋糕、甜点、面食的吃货们越来越多了，根据自己的口味来随心调整，爱加什么料就加什么料，简直就跟一次充满惊喜的探险一样！但俗话说的好，吃东西五分钟，做东西两小时，一想起要抡着胳膊打蛋、揉面团、打奶油，退堂的小鼓就咚咚咚响起来了。所以，吃货们，还不快掌声欢迎你们的福音—— \n");
					article.setPicUrl("http://mmbiz.qpic.cn/mmbiz/7nxEoPnEmicPdUpH83QwaZ6hhXueCe7OgsDolKy8bSvqcEmyfKuB9ibvLZrdPSpiaqSfO0NbtVDSaU8fNMRwWF6dQ/640?wx_fmt=jpeg&tp=webp&wxfrom=5&wx_lazy=1");
					article.setUrl("http://shuomingshu.maidehao.com/Bosch.htm");
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
				} else if (recvContent.contains("博世") || recvContent.contains("厨师机") || recvContent.contains("4系")
						|| recvContent.toLowerCase().contains("boshi".toLowerCase())
						|| recvContent.toLowerCase().contains("bosch".toLowerCase())
						|| recvContent.toLowerCase().contains("chushiji".toLowerCase()
						)
						){ 
					//图文消息
					Article article = new Article();
					article.setTitle("Bosch博世MUM4系厨师机使用说明必看");
					article.setDescription("为什么喜欢在厨房倒腾的吃货们 \n" 
							+ "都需要有一台厨师机？\n" 
							+ "因为它能帮你省时省力，事半功倍。\n" 
							+ "我们享受每一道美食的创作过程，\n" 
							+ "但没有人会喜欢繁杂低效率的琐碎步骤！\n" 
							+ "和面、打蛋、切菜、榨汁、\n" 
							+ "磨粉、打发奶油、绞肉馅··· \n" 
							+ "想想头都要炸了，还不快全扔给 \n" 
							+ "Bosch MUM4系家用厨师机！\n");
					article.setPicUrl("http://shuomingshu.maidehao.com/BoschMUM4_files/640_005.jpg");
					article.setUrl("http://shuomingshu.maidehao.com/BoschMUM4.htm");
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
				} else if (recvContent.contains("玻璃罐") || recvContent.contains("密封罐") 
						|| recvContent.toLowerCase().contains("weck".toLowerCase())
						|| recvContent.toLowerCase().contains("boliguan".toLowerCase())
						|| recvContent.toLowerCase().contains("mifengguan".toLowerCase()
						)
						){ 
					//图文消息
					Article article = new Article();
					article.setTitle("Weck玻璃密封罐简餐菜谱");
					article.setDescription("Weck的玻璃密封罐 \n" 
							+ "不仅仅只是好看那么简单。\n" 
							+ "我们可以用它来完成 \n" 
							+ "很多创意美食料理~\n" 
							+ "动手做起来！\n"  );
					article.setPicUrl("http://shuomingshu.maidehao.com/weck_files/640_002.jpg");
					article.setUrl("http://shuomingshu.maidehao.com/weck.htm");
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
				}  else if (recvContent.contains("客服") || recvContent.contains("小德") 
						|| recvContent.toLowerCase().contains("kefu".toLowerCase())
						|| recvContent.toLowerCase().contains("xiaode".toLowerCase())
						){
					// 获取接口访问凭证
					String accessToken = CommonUtil.getToken(ConfConstant.APPID, ConfConstant.APPSECRET).getAccessToken();
					log.info(" token  is :", accessToken);
					
					//得到客服 列表；
					// 拼接请求地址
					String requestUrl = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=ACCESS_TOKEN";
					requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
					// 发送客服消息
					JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
					log.info(" msg content is :", jsonObject);
					
					
					//添加客服列表
					/*requestUrl = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN";
					requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);			

					Map<String,String> batchget =new HashMap<String,String>();
					batchget.put("kf_account", "kf2002@maidehao01");		
					batchget.put("nickname", "bamboolsu");		 					
					JSONObject json_batchget = JSONObject.fromObject(batchget);//将java对象转换为json对象
					String json_batchget_str = json_batchget.toString();//将json对象转换为字符串

					JSONObject partlyMatrialRst = CommonUtil.httpsRequest(requestUrl, "POST", json_batchget_str);
					log.info(" msg content is :", partlyMatrialRst);*/

					//消息转发到指定客服
					// 创建图文消息
					KfMessage kfMessage = new KfMessage();
					kfMessage.setToUserName(fromUserName);
					kfMessage.setFromUserName(toUserName);
					kfMessage.setCreateTime(new Date().getTime());
					kfMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_KF);
					kfMessage.setKfAccount("kf2002@maidehao01");
					
					respXml = MessageUtil.messageToXml(kfMessage);
					return respXml;
				} 

				respContent = "小德推测您在找水壶,料理机等的说明书 \n" 
						+ "找水壶，请输入： 碧然德，滤水壶，滤芯，水壶；\n\n"
						+ "找料理机， 请输入： 料理机，搅拌机，4405；\n\n"
						+ "找Bosch博世MUM4系， 请输入： 博世，厨师机，bosch，4系；\n\n"
						+ "找weck玻璃杯子， 请输入： weck、玻璃罐、密封罐；\n\n"
						+ "找客服， 请输入： 客服， kefu， 小德， xiaode; \n\n"
						+ "oh，其它问题超出了我能回答的范围了！";
						//+ "还找不到那就来找万能的小德吧，微信号maidehaokefu（买德好客服 全拼）";
				System.out.println(" leosu  send msg is：" + respContent);
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！\n抱歉，图片我们暂时无法识别，\n还是来文字咨询吧";
			}
			// 语音消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是语音消息！\n抱歉，语音我们暂时无法识别，\n还是来文字咨询吧";
			}
			// 视频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "您发送的是视频消息！\n抱歉，视频我们暂时无法识别，\n还是来文字咨询吧";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！\n抱歉，视频我们暂时无法识别，\n还是来文字咨询吧";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！\n抱歉，视频我们暂时无法识别，\n还是来文字咨询吧";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				String eventKey = requestMap.get("EventKey");
				System.out.println("leosu:   CoreServlet  receive eventType is：" + eventType);
				
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					//新用户（没有关注过的用户）， 走这里； 此处场景值 加qrscene_
					/*respContent = ("\"终于等到你，还好我没放弃\""
							+ "\n感恩您关注买德好"
							+ "\n从此您的生活和正品德货之间"
							+ "\n只隔着一个买德好的距离"
							+ "\n我们精选将升级您生活品质的德国好物"
							+ "\n连同优质服务一起打包送到您手上"
							+ "\nwww.maidehao.com"
							+ "\n真德不一样，你来就知道！");*/
					
					
					System.out.println("leosu:   CoreServlet  receive eventKey is：" + eventKey);
					
					if (eventKey.contains("http:")) {
						respContent = ("场景值是： " + eventKey);
						String url = eventKey.substring(8);
						System.out.println("leosu:   scene's url is：" + url);
						//图文消息
						Article article = new Article();
						article.setTitle("url title");
						article.setDescription("此处是测试url， description");
						article.setPicUrl("");
						article.setUrl(url);
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
					} else if (eventKey.contains("weibo0920")) {
						//带链接的 文字 消息
						respContent = ("终于等到你，还好我没放弃~点击【<a href =\"http://m.maidehao.com/?vt=0.0.56#/mobile\">传送门</a>】绑定手机号，即可使用你的专属优惠券啦~！\n \n"
								+ "领券完毕~是不是心痒痒德？戳【<a href =\"http://m.maidehao.com\">BUY好货</a>】去逛逛吧，付款时别忘了使用优惠券哦~");
						
					} else {
						//respXml =  sendMsgArticle(requestMap);
						//return respXml;		
						//we will send text to user
						respContent = ("Hi，你好！\n"
								+ "又被一个讲究生活品质的高B格人类添加了，买德好感觉无上荣幸！\n" 
								+ "相信在这里，你也会玩得很high~\n\n"
								+ "因为有好多牛B德货评测和诚意推荐\n"
								+ "因为有不定期的优惠福利来哄你开心\n"
								+ "因为你会从只会“买买买”跨越到买得“好好好”的境界\n"
								+ "因为你的生活变得更有滋有味有态度。\n\n"
								+ "设计师威廉•莫里斯诺说：\n"
								+ "\"不要在家里摆放任何一件不好看又没用的东西\"\n\n"
								+ "在这里，咱不会遇到这样的东西~\n"
								+ "咱遇到的都是好生活。\n\n"
								+ "[欢迎进入\"新发现\"看看历史消息，说不定能发现欣喜哦]");
						
					}

					
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
					//老用户（已经关注过的用户）， 走这里； 此处场景值 是什么就是什么；
					System.out.println("leosu: EVENT_TYPE_SCAN   CoreServlet  receive eventKey is：" + eventKey);
					
					/*respXml =  sendMsgArticle(requestMap);
					return respXml;*/
					//only send text
					respContent = ("Hi，你好！\n"
							+ "又被一个讲究生活品质的高B格人类添加了，买德好感觉无上荣幸！\n" 
							+ "相信在这里，你也会玩得很high~\n\n"
							+ "因为有好多牛B德货评测和诚意推荐\n"
							+ "因为有不定期的优惠福利来哄你开心\n"
							+ "因为你会从只会“买买买”跨越到买得“好好好”的境界\n"
							+ "因为你的生活变得更有滋有味有态度。\n\n"
							+ "设计师威廉•莫里斯诺说：\n"
							+ "\"不要在家里摆放任何一件不好看又没用的东西\"\n\n"
							+ "在这里，咱不会遇到这样的东西~\n"
							+ "咱遇到的都是好生活。\n\n"
							+ "[欢迎进入\"新发现\"看看历史消息，说不定能发现欣喜哦]");
					
					
					if (eventKey.equals("weibo0920")) {
						//带链接的 文字 消息
						respContent = ("终于等到你，还好我没放弃~点击【<a href =\"http://m.maidehao.com/?vt=0.0.56#/mobile\">传送门</a>】绑定手机号，即可使用你的专属优惠券啦~！\n \n"
								+ "领券完毕~是不是心痒痒德？戳【<a href =\"http://m.maidehao.com\">BUY好货</a>】去逛逛吧，付款时别忘了使用优惠券哦~");
						
					}
					
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
					System.out.println("leosu:  eventType.equals(welcome),   eventKey is：" + eventKey);
					respContent = ("Thanks for you MDH.");
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建菜单时的key值对应
					//String eventKey = requestMap.get("EventKey");
					System.out.println("leosu:  eventType.equals(MessageUtil.EVENT_TYPE_CLICK),   eventKey is：" + eventKey);
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
