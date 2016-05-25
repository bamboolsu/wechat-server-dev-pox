package org.leo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.leo.entity.WechatInfo;
import org.leo.service.AccessToken;
import org.leo.service.impl.AccessTokenImpl;


@WebServlet("/wechatCallback")
public class WechatCallback extends HttpServlet {

	/**
	 * 微信平台回调的函数；
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			if (WechatInfo.UNIONID != null) {
				accessToken.getUserInfo();		
			}

			//设置返回的code 与 state ， 用于成功后访问；
			request.setAttribute("code", code);
			request.setAttribute("state", state);
			request.getRequestDispatcher("/WEB-INF/jsp/loginSuccess.jsp").forward(request, response);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
