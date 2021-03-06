package org.leo.course.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.leo.course.service.CoreService_solar;
import org.leo.course.util.SignUtil;

/**
 * 请求处理的核心类
 * 
 * @author leo
 * @date 2015-09-29
 */
public class CoreServlet_solar extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 请求校验（确认请求来自微信服务器）
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		


		PrintWriter out = response.getWriter();
		System.out.println(" leosu  we get timestamp is: " + timestamp);
		if(null == timestamp) {
			System.out.println(" leosu  we didn't get timestamp" + timestamp);
			out.print("test failed....");
			out.close();
			out = null;
		}
		// 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}

	/**
	 * 请求校验与处理
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println(" leosu  receive dopost" );

		// 接收参数微信加密签名、 时间戳、随机数
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");

		PrintWriter out = response.getWriter();
		System.out.println(" leosu  we get timestamp is: " + timestamp);
		if(null == timestamp) {
			System.out.println(" leosu  we didn't get timestamp" + timestamp);
			out.print("test failed....");
			out.close();
			out = null;
		}
		System.out.println(" leosu  XXXXXXXXXXXXXXXXXXXXXXXxx ");
		// 请求校验
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			// 调用核心服务类接收处理请求
			String respXml = CoreService_solar.processRequest(request);
			out.print(respXml);
		}
		out.close();
		out = null;
	}
}
