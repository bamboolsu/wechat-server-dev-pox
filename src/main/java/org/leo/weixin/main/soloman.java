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
public class soloman {
	private static Logger log = LoggerFactory.getLogger(soloman.class);

	/**
	 * 定义菜单结构
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		
		ViewButton btn11 = new ViewButton();
		btn11.setName("加盟合作");
		btn11.setType("view");
		btn11.setUrl("http://www.solargrid.com.cn/FrontNews/news_detail/id/301/sort_id/5");

		ViewButton btn12 = new ViewButton();
		btn12.setName("光伏政策");
		btn12.setType("view");
		btn12.setUrl("http://www.solargrid.com.cn/FrontNews/news_list/sort_id/20.html");
		
		ViewButton btn13 = new ViewButton();
		btn13.setName("所罗门学院");
		btn13.setType("view");
		btn13.setUrl("http://www.solargrid.com.cn/FrontNews/news_detail/id/210/sort_id/2");
		
		ViewButton btn14 = new ViewButton();
		btn14.setName("关于我们");
		btn14.setType("view");
		btn14.setUrl("http://www.solargrid.com.cn/FrontNews/news_detail/id/218/sort_id/7");
		
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("所罗门");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13, btn14});
		
		
		
		ViewButton btn21 = new ViewButton();
		btn21.setName("微电宝");
		btn21.setType("view");
		btn21.setUrl("http://www.solargrid.com.cn");

		ViewButton btn22 = new ViewButton();
		btn22.setName("电站风采");
		btn22.setType("view");
		btn22.setUrl("http://www.solargrid.com.cn");
		
		ViewButton btn23 = new ViewButton();
		btn23.setName("建站流程");
		btn23.setType("view");
		btn23.setUrl("http://www.solargrid.com.cn");
				
		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("光伏电站");
		mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23});	
		
		
		ViewButton btn31 = new ViewButton();
		btn31.setName("优惠活动");
		btn31.setType("view");
		btn31.setUrl("http://www.solargrid.com.cn");

		ViewButton btn32 = new ViewButton();
		btn32.setName("成为会员");
		btn32.setType("view");
		btn32.setUrl("http://www.solargrid.com.cn");
		
		ViewButton btn33 = new ViewButton();
		btn33.setName("我想投资");
		btn33.setType("view");
		btn33.setUrl("http://www.solargrid.com.cn");
		
		ViewButton btn34 = new ViewButton();
		btn34.setName("我有屋顶");
		btn34.setType("view");
		btn34.setUrl("http://www.solargrid.com.cn");
		
		ViewButton btn35 = new ViewButton();
		btn35.setName("內部測試");
		btn35.setType("view");
		btn35.setUrl("http://15r075i779.imwork.net/wechat-server-dev-pox/index-test.jsp");
		
		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("我想");
		mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33, btn34, btn35});	
		
		/* only creat 3 level menu */
		ViewButton btn4 = new ViewButton();
		btn4.setName("买德好");
		btn4.setType("view");
		btn4.setUrl("http://www.maidehao.com");	

		
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });
		
		return menu;
	}

	public static void main(String[] args) {
		// 第三方用户唯一凭证  
		String appId = "wxbbaf255da7c36932";
		// 第三方用户唯一凭证密钥 
		String appSecret = "cd3a75c437a978a6845cde07f3c3d542";

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