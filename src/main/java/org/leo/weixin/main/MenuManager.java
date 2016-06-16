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
		btn1.setName("买德好");
		btn1.setType("view");
		btn1.setUrl("http://m.maidehao.com/");

		ClickButton btn2 = new ClickButton();
		btn2.setName("找小德去");
		btn2.setType("click");
		btn2.setKey("findlittlede");

/*		ClickButton btn2 = new ClickButton();
		btn2.setName("个人中心");
		btn2.setType("click");
		btn2.setKey("membercenter");*/
		
		//ClickButton btn3 = new ClickButton();
		//btn3.setName("联系我们");
		//btn3.setType("click");
		//btn3.setKey("connectKF");		
		
/*		ViewButton btn31 = new ViewButton();
		btn31.setName("店铺测试");
		btn31.setType("view");
		btn31.setUrl("http://mp.weixin.qq.com/bizmall/mallshelf?id=&t=mall/list&biz=MzI4MTE4MDMwMQ==&shelf_id=1&showwxpaytitle=1#wechat_redirect");
*/		
		ClickButton btn32 = new ClickButton();
		btn32.setName("联系我们");
		btn32.setType("click");
		btn32.setKey("connectKF");	
		
		ViewButton btn33 = new ViewButton();
		btn33.setName("内部测试");
		btn33.setType("view");
		btn33.setUrl("http://m.maidehao.com/");
		//btn33.setUrl("http://m.maidehao.com/before_index.html");
		
		ViewButton btn34 = new ViewButton();
		btn34.setName("testting");
		btn34.setType("view");
		btn34.setUrl("http://15r075i779.imwork.net/test_mobile/");
		//btn34.setUrl("http://15r075i779.imwork.net/test_mobile/before_index.html");
		
		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("关于我们");
		mainBtn3.setSub_button(new Button[] {btn32, btn34} );


		
		Menu menu = new Menu();
		menu.setButton(new Button[] { btn1, btn2, mainBtn3 });
		
		return menu;
	}

	public static void main(String[] args) {
		// 第三方用户唯一凭证  （买德好官方）
		String appId = "wxe71e2c3a1dbe7740";
		// 第三方用户唯一凭证密钥 （买德好官方）
		String appSecret = "0e967a0dcc14d651ce4bf9979e1d8594";

		// 第三方用户唯一凭证 （测试号）
		//String appId = "wx0d84b978f8a520b4";
		// 第三方用户唯一凭证密钥（测试号）
		//String appSecret = "84fc4469f117524cd748ec5e84848b10";
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