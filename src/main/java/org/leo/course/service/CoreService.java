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
 * ���ķ�����
 * 
 * @author leo
 * @date 2015-09-29
 */
public class CoreService {
	private static Logger log = LoggerFactory.getLogger(MenuUtil.class);
	/**
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml��ʽ����Ϣ����
		String respXml = null;
		// Ĭ�Ϸ��ص��ı���Ϣ����
		String respContent = "Welcome to MDH. ��ӭ������ºá�";
		try {
			// ����parseXml��������������Ϣ
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// ���ͷ��ʺ�
			String fromUserName = requestMap.get("FromUserName");
			// ������΢�ź�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");
			System.out.println(" leosu  receive msgtype is��" + msgType);
			log.warn(" leosu  receive msgtype is��" + msgType);

			// �ظ��ı���Ϣ
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			// �ı���Ϣ
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				respContent = "�����͵����ı���Ϣ��";
				System.out.println(" leosu  send msg is��" + respContent);
			}
			// ͼƬ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "�����͵���ͼƬ��Ϣ��";
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "�����͵���������Ϣ��";
			}
			// ��Ƶ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "�����͵�����Ƶ��Ϣ��";
			}
			// ����λ����Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "�����͵��ǵ���λ����Ϣ��";
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "�����͵���������Ϣ��";
			}
			// �¼�����
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");
				// ����
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = ("���ã���ӭ������ºã�");
				}
				// ȡ������
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO �ݲ�������
				}
				//EVENT_TYPE_SCAN
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					respContent = ("You are scanning MDH.");
				}
				//myself define welcome, 
				else if (eventType.equals("welcom")) {
					respContent = ("Thanks for you MDH.");
				}
				// �Զ���˵�����¼�
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// �¼�KEYֵ���봴���˵�ʱ��keyֵ��Ӧ
					String eventKey = requestMap.get("EventKey");
					// ����keyֵ�ж��û�����İ�ť
					if (eventKey.equals("connectKF")) {
						Article article = new Article();
						article.setTitle("��º�");
						article.setDescription("��ºÿ羳������վ����\n\n��Ҫ��Ӫ�¹��� ĸӤ�� ������ ���ߣ� �ľߵȡ�\n\n ��ӭݰ��");
						article.setPicUrl("");
						article.setUrl("http://www.maidehao.com");
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						// ����ͼ����Ϣ
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respXml = MessageUtil.messageToXml(newsMessage);
						return respXml;
					} else if (eventKey.equals("membercenter")) {
						respContent = ("�ٷ�΢�ź�: maidehao01 \n\n ý�����:  pr@maidehao.com  \n\n  �������:  bd@maidehao.com \n\n �ͷ��绰:  400-801-6708��\n\n �ٷ��̳�:  http://www.maidehao.com");
					}
				}
			}
			// �����ı���Ϣ������
			textMessage.setContent(respContent);
			// ���ı���Ϣ����ת����xml
			respXml = MessageUtil.messageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
}
