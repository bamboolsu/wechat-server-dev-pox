package org.leo.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.leo.entity.Member;
import org.leo.entity.WechatInfo;
import org.leo.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller("shopLoginController")
@RequestMapping("/solar/memberbase")
public class MemberBaseController extends BaseController {

	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	
	/**
	 * 登录提交
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public @ResponseBody
	void submit(String openid, String unionid, String name, String mobile,
			String email, String province, String city, String address,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		//生成或者 查询 member 
		Member member;
		if ((WechatInfo.UNIONID != null) && (!WechatInfo.UNIONID.isEmpty())) {
			member = memberService.findByUnionId(WechatInfo.UNIONID);
			if (member == null) {
				System.out.println("lsu: Error. ");
				return;
			}

		} else if ((WechatInfo.OPENID != null) && (!WechatInfo.OPENID.isEmpty())){
			member = memberService.findByOpenId(WechatInfo.OPENID);
			if (member == null) {
				System.out.println("lsu: Error. ");
				return;
			}
		} else {
			System.out.println("lsu: we didn't get the openid and unionid. ");
			return ;
		}		
		
		member.setName(name);
		member.setMobile(mobile);
		member.setEmail(email);
		member.setProvince(province);
		member.setCity(city);
		member.setAddress(address);
		memberService.update(member);
		return ;
	}
}
