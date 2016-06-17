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
		
		//所罗门：  关于我们， 加盟合作，所罗门学院
		ClickButton btn11 = new ClickButton();
		btn11.setName("关于我们");
		btn11.setType("click");
		btn11.setKey("aboutus");

		ClickButton btn12 = new ClickButton();
		btn12.setName("加盟合作");
		btn12.setType("click");
		
		//暂时屏蔽  18130632592
		ClickButton btn13 = new ClickButton();
		btn13.setName("所罗门学院");
		btn13.setType("click");
		btn13.setKey("solorschool");
		
		ClickButton btn14 = new ClickButton();
		btn14.setName("联系我们");
		btn14.setType("click");
		btn14.setKey("connectUS");
		
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("所罗门");
		mainBtn1.setSub_button(new Button[] { btn11, btn12, btn14});
		
		
		//光伏电站：    微电宝， 电站风采，建站流程；光伏电站
		//暂时不使用
		ClickButton btn21 = new ClickButton();
		btn21.setName("微电宝");
		btn21.setType("click");
		btn21.setKey("wedianbao");

		//先屏蔽这个子菜单， 未来要展示一些示范电站
		ClickButton btn22 = new ClickButton();
		btn22.setName("电站风采");
		btn22.setType("click");
		btn22.setKey("dianzhan");
		
		ClickButton btn23 = new ClickButton();
		btn23.setName("建站流程");
		btn23.setType("click");
		btn23.setKey("buildprocess");
		
		ClickButton btn24 = new ClickButton();
		btn24.setName("光伏电站");
		btn24.setType("click");
		btn24.setKey("guangfudianzhan");
				
		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("光伏电站");
		mainBtn2.setSub_button(new Button[] {btn23});	
		
		
		//我想：     成为会员；投资电站；提供屋顶
/*		ViewButton btn31 = new ViewButton();
		btn31.setName("优惠活动");
		btn31.setType("view");
		btn31.setUrl("http://www.solargrid.com.cn");
*/
		ViewButton btn32 = new ViewButton();
		btn32.setName("成为会员");
		btn32.setType("view");
		btn32.setUrl("http://wechat.maidehao.com/wechat_login_basemember.jsp");
		
		ViewButton btn33 = new ViewButton();
		btn33.setName("投资电站");
		btn33.setType("view");
		//btn33.setUrl("http://wechat.maidehao.com/wechat_login_investment.jsp");
		btn33.setUrl("http://wechat.maidehao.com/wechat_login_basemember.jsp");
		
		ViewButton btn34 = new ViewButton();
		btn34.setName("提供屋顶");
		btn34.setType("view");
		//btn34.setUrl("http://wechat.maidehao.com/wechat_login_housetop.jsp");
		btn34.setUrl("http://wechat.maidehao.com/wechat_login_basemember.jsp");
				
		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("我想");
		mainBtn3.setSub_button(new Button[] {btn32, btn33, btn34});	
				
		
		/* only creat 3 level menu */
/*		ViewButton btn4 = new ViewButton();
		btn4.setName("买德好");
		btn4.setType("view");
		btn4.setUrl("http://www.maidehao.com");	*/

		
		Menu menu = new Menu();
		menu.setButton(new Button[] {mainBtn3, mainBtn2, mainBtn1 });
		
		return menu;
	}

	public static void main(String[] args) {
		// 第三方用户唯一凭证  
		String appId = "wxbbaf255da7c36932";
		// 第三方用户唯一凭证密钥 
		String appSecret = "7b7461e14a1975a5c1bfd680a12b37ab";

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