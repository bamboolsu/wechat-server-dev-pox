package org.leo.controller;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.leo.entity.Member;
import org.leo.entity.WechatInfo;
import org.leo.service.AccessToken;
import org.leo.service.MemberService;
import org.leo.service.impl.AccessTokenImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller("wechatCallbackController")
@RequestMapping("/")
public class WechatCallbackController extends BaseController {
	
	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	
	/**
	 * 微信平台回调的函数；
	 */
	@RequestMapping(value = "/wechatCallbackController", method = RequestMethod.GET)
	public void
	doGet(HttpServletRequest request, HttpServletResponse response) {
		//WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext((ServletContext) request.getServletContext());
		//MemberService memberService = (MemberService) context.getBean("memberServiceImpl");
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		System.out.println("here we will doGet code parameter. leosu");
		System.out.println("code is:" + code + ";    state is:" + state);
		

		
		//通过code获取access_token
		try {
			//取得  access token
			AccessToken accessToken = new AccessTokenImpl();
			accessToken.getAccessToken(code, state);
			
			//刷新 access token；
			accessToken.refreshAccessToken();
			
			//check access token 的有效性；
			accessToken.checkAccessToken();
			
			
			//取得对应用户的微信相应信息；
			if ((WechatInfo.UNIONID != null) && (!WechatInfo.UNIONID.isEmpty())) {
				accessToken.getUserInfo();		
			}
			
			//生成或者 查询 member 
			Member member;
			if ((WechatInfo.UNIONID != null) && (!WechatInfo.UNIONID.isEmpty())) {
				member = memberService.findByUnionId(WechatInfo.UNIONID);
				if (member == null) {
					member  = new Member();
					member.setUnionId(WechatInfo.UNIONID);
					member.setOpenId(WechatInfo.OPENID);
					memberService.save(member);
				}
			} else if ((WechatInfo.OPENID != null) && (!WechatInfo.OPENID.isEmpty())){
				member = memberService.findByOpenId(WechatInfo.OPENID);
				if (member == null) {
					member  = new Member();
					member.setOpenId(WechatInfo.OPENID);
					memberService.save(member);
				}
			} else {
				System.out.println("lsu: we didn't get the openid and unionid. ");
				return ;
			}



			//设置返回的code 与 state ， 用于成功后访问；
			request.setAttribute("code", code);
			request.setAttribute("state", state);
			request.setAttribute("member", member);
			HttpSession session = request.getSession();
			session .setAttribute("member", member);
			//request.getRequestDispatcher("/WEB-INF/jsp/loginSuccess.jsp").forward(request, response);
			request.getRequestDispatcher("/acount.jsp").forward(request, response);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}


	@RequestMapping(value = "/wechatCallbackController", method = RequestMethod.POST)
	public void doPost(HttpServletRequest request, HttpServletResponse response) {

	}
}
