package org.leo.course.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.leo.course.pojo.SNSUserInfo;
import org.leo.course.pojo.WeixinOauth2Token;
import org.leo.course.util.AdvancedUtil;

/**
 * ��Ȩ��Ļص�������
 * 
 * @author leo
 * @date 2015-11-12
 */
public class OAuthServlet extends HttpServlet {
	private static final long serialVersionUID = -1847238807216447030L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("gb2312");
		response.setCharacterEncoding("gb2312");

		// �û�ͬ����Ȩ���ܻ�ȡ��code
		String code = request.getParameter("code");

		// �û�ͬ����Ȩ
		if (!"authdeny".equals(code)) {
			// ��ȡ��ҳ��Ȩaccess_token
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken("wxe71e2c3a1dbe7740", "0e967a0dcc14d651ce4bf9979e1d8594", code);
			// ��ҳ��Ȩ�ӿڷ���ƾ֤
			String accessToken = weixinOauth2Token.getAccessToken();
			// �û���ʶ
			String openId = weixinOauth2Token.getOpenId();
			// ��ȡ�û���Ϣ
			SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);

			// ����Ҫ���ݵĲ���
			request.setAttribute("snsUserInfo", snsUserInfo);
		}
		// ��ת��index.jsp
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
