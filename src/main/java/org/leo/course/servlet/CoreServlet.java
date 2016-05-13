package org.leo.course.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.leo.course.service.CoreService;
import org.leo.course.util.SignUtil;

/**
 * ������ĺ�����
 * 
 * @author leo
 * @date 2015-09-29
 */
public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 4440739483644821986L;

	/**
	 * ����У�飨ȷ����������΢�ŷ�������
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ΢�ż���ǩ��
		String signature = request.getParameter("signature");
		// ʱ���
		String timestamp = request.getParameter("timestamp");
		// �����
		String nonce = request.getParameter("nonce");
		// ����ַ���
		String echostr = request.getParameter("echostr");
		


		PrintWriter out = response.getWriter();
		System.out.println(" leosu  we get timestamp is: " + timestamp);
		if(null == timestamp) {
			System.out.println(" leosu  we didn't get timestamp" + timestamp);
			out.print("test failed....");
			out.close();
			out = null;
		}
		// ����У�飬��У��ɹ���ԭ������echostr����ʾ����ɹ����������ʧ��
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}

	/**
	 * ����У���봦��
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��������Ӧ�ı��������ΪUTF-8����ֹ�������룩
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println(" leosu  receive dopost" );

		// ���ղ���΢�ż���ǩ���� ʱ����������
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
		// ����У��
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			// ���ú��ķ�������մ�������
			String respXml = CoreService.processRequest(request);
			out.print(respXml);
		}
		out.close();
		out = null;
	}
}
