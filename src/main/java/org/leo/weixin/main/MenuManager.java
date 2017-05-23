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


		
		ViewButton btn11 = new ViewButton();
		btn11.setName("品牌推荐");
		btn11.setType("view");
		btn11.setUrl("http://m.maidehao.com/");
		
		ViewButton btn12 = new ViewButton();
		btn12.setName("促销活动");
		btn12.setType("view");
		btn12.setUrl("http://m.maidehao.com/");
		
		ViewButton btn13 = new ViewButton();
		btn13.setName("历史消息");
		btn13.setType("view");
		btn13.setUrl("http://mp.weixin.qq.com/mp/getmasssendmsg?__biz=MzI4MTE4MDMwMQ==#wechat_webview_type=1&wechat_redirect");	
		
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("新发现");
		mainBtn1.setSub_button(new Button[] { btn13} );
		
		
		
		
		ClickButton btn21 = new ClickButton();
		btn21.setName("【淘宝】德国直邮电器店");
		btn21.setType("click");
		btn21.setKey("TBDM");	
		
		ClickButton btn22 = new ClickButton();
		btn22.setName("【淘宝】德国好物市集店");
		btn22.setType("click");
		btn22.setKey("TBXH");

		ViewButton btn23 = new ViewButton();
		btn23.setName("下厨房店");
		btn23.setType("view");
		btn23.setUrl("https://www.xiachufang.com/shop/111241495/");
		
		ViewButton btn24 = new ViewButton();
		btn24.setName("买德好商城-内测版");  //买德好官方商城
		btn24.setType("view");
		btn24.setUrl("http://m.maidehao.com");
		
		ComplexButton btn2 = new ComplexButton();
		btn2.setName("BUY好货");
		btn2.setSub_button(new Button[] { btn21, btn22, btn23, btn24} );
		
		
		/*ViewButton btn2 = new ViewButton();
		btn2.setName("BUY好货");
		btn2.setType("view");
		btn2.setUrl("http://m.maidehao.com/");*/

		/*ClickButton btn2 = new ClickButton();
		btn2.setName("找小德去");
		btn2.setType("click");
		btn2.setKey("findlittlede");*/

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
		
		ClickButton btn31 = new ClickButton();
		btn31.setName("\ue12f优惠资讯");
		btn31.setType("click");
		btn31.setKey("connectKF");
		
		ViewButton btn32 = new ViewButton();
		//btn32.setName("\ue057个人中心");
		btn32.setName("\ue057个人中心");
		btn32.setType("view");
		btn32.setUrl("http://m.maidehao.com/#/center");
		//btn32.setUrl("http://m.maidehao.com/#/center");
		
		
		ClickButton btn33 = new ClickButton();
		btn33.setName("\ue009服务中心");
		btn33.setType("click");
		btn33.setKey("connectKF");	
		
		//emojo:  http://www.fuhaodq.com/biaoqingfuhao/1531.html
		//emojo 表情大全：  http://www.oicqzone.com/tool/emoji/
		ClickButton btn34 = new ClickButton();
		btn34.setName("\ue03e我们的故事");
		btn34.setType("click");
		btn34.setKey("findlittlede");
		
		ViewButton btn35 = new ViewButton();
		btn35.setName("买德好商城");
		btn35.setType("view");
		btn35.setUrl("http://m.maidehao.com");	

		
		/*ViewButton btn33 = new ViewButton();
		btn33.setName("内部测试");
		btn33.setType("view");
		btn33.setUrl("http://m.maidehao.com/");
		//btn33.setUrl("http://m.maidehao.com/before_index.html");
		
		ViewButton btn34 = new ViewButton();
		btn34.setName("testting");
		btn34.setType("view");
		btn34.setUrl("http://15r075i779.imwork.net/test_mobile/");*/
		
		//btn34.setUrl("http://15r075i779.imwork.net/test_mobile/before_index.html");
		
		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("找服务");
		mainBtn3.setSub_button(new Button[] {btn31,btn32, btn33, btn34} );


		
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, btn2, mainBtn3 });
		
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