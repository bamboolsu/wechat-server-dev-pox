<%@ page language="java" pageEncoding="gb2312"%>
<%@ page import="org.leo.course.pojo.SNSUserInfo"%>
<%@ page import="java.io.File"%>

<html>
<head>
	<title>OAuth2.0网页授权</title>
	<meta name="viewport" content="width=device-width,user-scalable=0">
	<style type="text/css">
		*{margin:0; padding:0}
		table{border:1px dashed #B9B9DD;font-size:12pt}
		td{border:1px dashed #B9B9DD;word-break:break-all; word-wrap:break-word;}
	</style>
</head>
<body>
	<% 
		// 获取由OAuthServlet中传入的参数
		SNSUserInfo user = (SNSUserInfo)request.getAttribute("snsUserInfo"); 
		ServletContext s1=this.getServletContext();
		String temp=s1.getRealPath("/");
		
		String path = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath(); 
		String rootPath = path.substring(0, path.indexOf("/classes/")) + File.separatorChar +  "cert"; 

		
	
		if(null != user) {
	%>
	<table width="100%" cellspacing="0" cellpadding="0">
		<tr><td width="20%">属性</td><td width="80%">值</td></tr>
		<tr><td>OpenID</td><td><%=user.getOpenId()%></td></tr>
		<tr><td>昵称</td><td><%=user.getNickname()%></td></tr>
		<tr><td>性别</td><td><%=user.getSex()%></td></tr>
		<tr><td>国家</td><td><%=user.getCountry()%></td></tr>
		<tr><td>省份</td><td><%=user.getProvince()%></td></tr>
		<tr><td>城市</td><td><%=user.getCity()%></td></tr>
		<tr><td>头像</td><td><%=user.getHeadImgUrl()%></td></tr>
		<tr><td>特权</td><td><%=user.getPrivilegeList()%></td></tr>
	</table>
	<%
		}
		else {
			
			out.print("用户不同意授权,未获取到用户信息！ selvet path is: "  + temp + "\n  thread path is : " + path + "\n rootPath is: " + rootPath);
			}
	%>
</body>
</html>