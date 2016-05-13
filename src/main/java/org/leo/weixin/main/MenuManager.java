package org.leo.weixin.main;

import org.leo.course.menu.Button;
import org.leo.course.menu.ClickButton;
import org.leo.course.menu.ComplexButton;
import org.leo.course.menu.Menu;
import org.leo.course.menu.ViewButton;
import org.leo.course.pojo.Token;
import org.leo.course.util.CommonUtil;
import org.leo.course.util.MenuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * �˵���������
 * 
 * @author leo
 * @date 2015-10-17
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	/**
	 * ����˵��ṹ
	 * 
	 * @return
	 */
	private static Menu getMenu() {

		ViewButton btn1 = new ViewButton();
		btn1.setName("�ٷ��̳�");
		btn1.setType("view");
		btn1.setUrl("http://www.maidehao.com");

		ViewButton btn2 = new ViewButton();
		btn2.setName("�Żݻ");
		btn2.setType("view");
		btn2.setUrl("http://www.maidehao.com");
		
		
		ClickButton btn31 = new ClickButton();
		btn31.setName("��ϵ�ͷ�");
		btn31.setType("click");
		btn31.setKey("connectKF");
		
		ClickButton btn32 = new ClickButton();
		btn32.setName("��Ա����");
		btn32.setType("click");
		btn32.setKey("membercenter");	
		
		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("�ҵ�����");
		mainBtn3.setSub_button(new Button[] { btn31, btn32});

		
		Menu menu = new Menu();
		menu.setButton(new Button[] { btn1, btn2, mainBtn3 });
		
		return menu;
	}

	public static void main(String[] args) {
		// �������û�Ψһƾ֤
		String appId = "wxe71e2c3a1dbe7740";
		// �������û�Ψһƾ֤��Կ
		String appSecret = "0e967a0dcc14d651ce4bf9979e1d8594";

		// ���ýӿڻ�ȡƾ֤
		Token token = CommonUtil.getToken(appId, appSecret);
		System.out.println(" leosu  get token is��" + token.getAccessToken());

		if (null != token) {
			// �����˵�
			boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());

			// �жϲ˵��������
			if (result) {
				log.info("�˵������ɹ���");
				System.out.println(" leosu  create menu successful ");
			} else {
				log.info("�˵�����ʧ�ܣ�");
				System.out.println(" leosu  create menu failed ");
			}
		}
	}
}
