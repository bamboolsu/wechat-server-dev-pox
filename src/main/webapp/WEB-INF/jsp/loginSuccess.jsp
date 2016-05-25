<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.leo.entity.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信登陆成功</title>
</head>
<body>
<h1>微信登陆成功！</h1>

<h1>code is:   ${code}</h1>

<h1>state is: ${state}</h1>

	<h1>
		WechatInfo.nickname =
		<%=WechatInfo.nickname%>
	</h1>
	<h1>
		WechatInfo.OPENID =
		<%=WechatInfo.OPENID%>
	</h1>
		<h1>
		WechatInfo.UNIONID =
		<%=WechatInfo.UNIONID%>
	</h1>
		<h1>
		WechatInfo.province =
		<%=WechatInfo.province%>
	</h1>
	<h1>
		WechatInfo.headimgurl =
		<%=WechatInfo.headimgurl%>
		<img src=<%=WechatInfo.headimgurl%>>
	</h1>
	<h1>
		WechatInfo.city =
		<%=WechatInfo.city%>
	</h1>
	<h1>
		WechatInfo.sex =
		<%=WechatInfo.sex%>
	</h1>



</body>
</html>