<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生成带参数的二维码</title>
</head>
<body>
<form action="qrcodeServlet" method="post">
<table>
	<tr>
		<td>action_name：</td>
		<td><input type="text" name="appid" value="wxe71e2c3a1dbe7740"/>APPID = "wxe71e2c3a1dbe7740"</td>
	</tr>
		<tr>
		<td>action_name：</td>
		<td><input type="text" name="appsecret" value="0e967a0dcc14d651ce4bf9979e1d8594"/>APPSECRET = "0e967a0dcc14d651ce4bf9979e1d8594"</td>
	</tr>
	<tr>
		<td>action_name：</td>
		<td><input type="text" name="action_name" value="QR_LIMIT_STR_SCENE"/>QR_LIMIT_STR_SCENE 为永久的字符串参数值</td>
	</tr>
	<tr>
		<td>action_info：</td>
		<td><input type="text" name="scene_str" value="welcome"/>　 welcome　为　scene_str　的值</td>
	</tr>

	<tr>
		<td></td>
		<td><input type="submit" value="提交"/></td>
	</tr>
</table>
</form>
</body>
</html>