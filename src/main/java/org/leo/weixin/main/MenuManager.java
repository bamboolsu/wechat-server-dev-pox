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
 * 菜单管理器类
 * 
 * @author leo
 * @date 2015-10-17
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	/**
	 * 定义菜单结构
	 * 
	 * @return
	 */
	private static Menu getMenu() {

		ViewButton btn1 = new ViewButton();
		btn1.setName("官方商城");
		btn1.setType("view");
		btn1.setUrl("http://www.maidehao.com");

		ViewButton btn2 = new ViewButton();
		btn2.setName("优惠活动");
		btn2.setType("view");
		btn2.setUrl("http://www.maidehao.com");
		
		
		ClickButton btn31 = new ClickButton();
		btn31.setName("联系客服");
		btn31.setType("click");
		btn31.setKey("connectKF");
		
		ClickButton btn32 = new ClickButton();
		btn32.setName("会员中心");
		btn32.setType("click");
		btn32.setKey("membercenter");	
		
		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("我的助手");
		mainBtn3.setSub_button(new Button[] { btn31, btn32});

		
		Menu menu = new Menu();
		menu.setButton(new Button[] { btn1, btn2, mainBtn3 });
		
		return menu;
	}

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wxe71e2c3a1dbe7740";
		// 第三方用户唯一凭证密钥
		String appSecret = "0e967a0dcc14d651ce4bf9979e1d8594";

		// 调用接口获取凭证
		Token token = CommonUtil.getToken(appId, appSecret);
		System.out.println(" leosu  get token is：" + token.getAccessToken());

		if (null != token) {
			// 创建菜单
			boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());

			// 判断菜单创建结果
			if (result) {
				log.info("菜单创建成功！");
				System.out.println(" leosu  create menu successful ");
			} else {
				log.info("菜单创建失败！");
				System.out.println(" leosu  create menu failed ");
			}
		}
	}
}
