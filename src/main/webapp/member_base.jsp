<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="org.leo.entity.Member"%>

<!DOCTYPE html>
<html>

<style>
    html,body {
        width:100%;
        min-width:1200px;
        height:auto;
        padding:0;
        margin:0;
        font-family:"微软雅黑";
        background-color:#242736
    }
    .header {
        width:100%;
        margin:0 auto;
        height:230px;
        background-color:#fff
    }
    .container {
        width:100%;
        min-width:100px;
        height:auto
    }
    .black {
        background-color:#242736
    }
    .blue {
        background-color:#0ae
    }

    .title {
        /*width:1200px;
        margin:0 auto;
        height:80px;
        line-height:80px;
        font-size:20px;*/
        
        width:600px;
        height:80px;
        margin:5 auto;
        font-size:20px
        color:#FFF
    }
    
    
    
    .content {
        width:100%;
        min-width:120px;
        height:1325px;
        background-color:#fff;      
    }
    .alipayform {
        /*width:750px;*/
        width:100%;
        margin:4 auto;
        height:1325px;
        border:3px solid #0ae
    }
    .element {
        width:800px;
        height:140px;
        margin-left:1px;
        font-size:36px
    }
    .element-less {
        width:850px;
        height:140px;
        margin-left:100px;
        font-size:36px
    }
    .etitle,.einput {
        float:left;
        height:140px
    }
    .etitle {
        width:200px;
        line-height:140px;
        text-align:right
    }
    .einput {
        width:550px;
        margin-left:40px
    }
    .einput input {
    	margin-top:10px;
        width:550px;
        height:100px;
        border:1px solid #0ae;
        font-size:36px
    }


    .legend {
        margin-left:350px;
        font-size:44px
    }
    .alisubmit {
        width:250px;
        height:80px;
        border:0;
        background-color:#0ae;
        font-size:44px;
        color:#FFF;
        cursor:pointer;
        margin-left:450px
    }
    
    
    .footer {
        width:100%;
        height:180px;
        background-color:#242735
    }
    .footer-sub a,span {
        color:#808080;
        font-size:24px;
        text-decoration:none
    }
    .footer-sub a:hover {
        color:#00aeee
    }
    .footer-sub span {
        margin:0 3px
    }
    .footer-sub {
        padding-top:40px;
        height:28px;
        width:600px;
        margin:0 auto;
        text-align:center
    }
</style>


<head>
    <meta charset="UTF-8">
	<title>成为会员</title>
</head>




<body>

<%Member tempMember =(Member)  session.getAttribute("member");%>

<!-- 	<h1> -->
<!-- 		Member.OPENID = -->
<%-- 		<%=tempMember.getOpenId()%> --%>
<!-- 	</h1> -->

<!--     <div class="header">
        <div class="container blue">
            <div class="title">支付宝即时到账(create_direct_pay_by_user)</div>
        </div>
    </div> -->
    <div class="content">
       <!--  <form action="/wechat-server-dev-pox/solar/memberbase/submit.jhtml" class="alipayform" method="POST"> -->
        <form action="./solar/memberbase/submit.jhtml" class="alipayform" method="POST">
        
            <div class="element-less" style="margin-top:60px;">
                <div class="legend">我的基本信息 
                </div>
           	</div>
  
 <%--            <div class="element-less">
                <div class="etitle">openid:</div>
                <div class="einput"><input type="text" name="openid" id="openid" value=<%=tempMember.getOpenId()%> ></div>
                <br>
            </div>
            <div class="element-less">
                <div class="etitle">unionid:</div>
                <div class="einput"><input type="text" name="unionid" id="unionid" value=<%=tempMember.getUnionId()%>></div>
                <br>
            </div> --%>
                      
            <div class="element-less">
                <div class="etitle">姓名:</div>
                <div class="einput"><input type="text" name="name" id="name" value=<%=tempMember.getName()%> ></div>
                <br>
            </div>
            <div class="element-less">
                <div class="etitle">手机:</div>
                <div class="einput"><input type="text" name="mobile" id="mobile" value=<%=tempMember.getMobile()%> ></div>
                <br>
            </div>
            <div class="element-less">
                <div class="etitle">邮箱:</div>
                <div class="einput"><input type="text" name="email" id="email" value=<%=tempMember.getEmail()%> ></div>
                <br>
            </div>      
            <div class="element-less">
                <div class="etitle">省份:</div>
                <div class="einput"><input type="text" name="province" id="province" value=<%=tempMember.getProvince()%> ></div>
                <br>
            </div>   
            
            <div class="element-less">
                <div class="etitle">城市:</div>
                <div class="einput"><input type="text" name="city" id="city" value=<%=tempMember.getCity()%> ></div>
                <br>
            </div>   
                 
            <div class="element-less">
                <div class="etitle">详细地址:</div>
                <div class="einput"><input type="text" name="address" id="address"></div>
            </div>            
            
            <div class="element">
                <input type="submit" class="alisubmit" value ="确 认 提 交">
            </div>
        </form>
    </div>
    
    
    <div class="footer">
        <p class="footer-sub">
            <a href="http://www.solargrid.com.cn/index.htm" target="_blank">关于</a><span>|</span>
            <a href="http://www.solargrid.com.cn/index.htm" target="_blank">所罗门</a>
            <br>
            <span>所罗门版权所有</span>
            <span class="footer-date">2004-2016</span>
            <span><a href="http://www.solargrid.com.cn" target="_blank">ICP证号：浙ICP备15002232号</a></span>
        </p>           
    </div>
    
</body>

</html>