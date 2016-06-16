<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.leo.entity.WechatInfo"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成为会员</title>
</head>






<body onload="javascript: document.forms[0].submit();">
 
<!-- <body >
 -->

	

<%
String rediecturi = java.net.URLEncoder.encode(WechatInfo.REDIRECTURIOLD_C_ONLINE + "?url=/member_base.jsp", "utf-8");
System.out.println(" leosu  appid " +  WechatInfo.APPID);

String url = WechatInfo.WECHATQRCONNECT + "?appid=" + WechatInfo.APPIDSOLORMAN
             + "&redirect_uri=" + rediecturi
             + "&response_type=code&scope=snsapi_base"
             + "&state=" + WechatInfo.STATE
             + "#wechat_redirect";
%>
<%-- <a href=<%=url%> > 微信登陆</a>



</span>
</li>

<li> --%>
<li>
	<form action=<%=url%>  method="post"  >
	test 
	test 
	<input type="submit" class="alisubmit" value ="确认提交">
	</form>
</li>


</body>
</html>