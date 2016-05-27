<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.leo.entity.WechatInfo"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信支付</title>
</head>





<!-- 
<body onload="javascript: document.forms[0].submit();">
 -->
<body >

<li>
	
<span>
<%
String rediecturi = java.net.URLEncoder.encode(WechatInfo.REDIRECTURIOLD_CJ, "utf-8");
System.out.println(" leosu  appid " +  WechatInfo.APPID);

String url = WechatInfo.WECHATQRCONNECT + "?appid=" + WechatInfo.APPID
             + "&redirect_uri=" + rediecturi
             + "&response_type=code&scope=snsapi_base"
             + "&state=" + WechatInfo.STATE
             + "#wechat_redirect";
%>
<a href=<%=url%> > 微信登陆</a>



</span>
</li>

<li>
	<form action=<%=url%>  method="post"  >
	test 
	test 
	<input type="submit" class="alisubmit" value ="确认提交">
	</form>
</li>


<li>
<span>
leo test
REDIECTURI IS: <%=rediecturi%>
</span>
</li>

<li>
<span>
<%=url%>

<!--  a href="https://open.weixin.qq.com/connect/qrconnect?appid=wxd5766a6d882dab7e&redirect_uri=https%3A%2F%2Fmaidehao.com%2Fwechatcallback&response_type=code&scope=snsapi_login&state=3d6be0a4035d839573b04816624a415e#wechat_redirect"> 微信登陆</a -->

</span>
</li>

</body>
</html>