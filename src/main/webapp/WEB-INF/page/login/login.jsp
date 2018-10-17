<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<title>eroly教育-登录</title>
<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon"/>
<link rel="stylesheet" type="text/css" href="resources/css/style.css" />
<link href="resources/css/single.css" type="text/css" rel="stylesheet" />
<link href="resources/css/login.css" type="text/css" rel="stylesheet" />

<body>
	<c:import url="../header.jsp"></c:import>
	<div class="content">
		<div class="clear"></div>
		<div class="loginDiv">
			<div class="buyHeader">
				<h1>用户登录</h1>
				<div class="innerDiv" onclick="history.back()">返 回</div>
			</div>
			<div class="loginBody">
				<fieldset class="regField">
					<legend style="color: #DE3237; font-size: 18px;" align="left">&nbsp;登&nbsp;录&nbsp;</legend>
						<form action="user/userLogin" method="post">
							<table width="80%" class="loginTable">
								<tr>
									<td align="right" style="width: 40%;height: 50px;">&nbsp;</td>
									<td align="left"><span id="tip_span" style="color: green"></span></td>
									<td align="left" nowrap="nowrap">&nbsp;</td>
								</tr>
								<tr>
									<td align="right" style="width: 40%;height:50px;">账&nbsp;&nbsp;&nbsp;&nbsp;号&nbsp;&nbsp;&nbsp;</td>
									<td width="160" align="left"><input type="text"
										name="userInfo" id="studentLoginName" placeholder="用户名/手机号/邮箱" maxlength="20"/></td>
									<td align="left" nowrap="nowrap"><span id="loginName_span"></span></td>
								</tr>
								<tr>
									<td align="right" style="width: 40%;height:50px;">密&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;&nbsp;&nbsp;</td>
									<td width="160" align="left"><input type="password"
										name="userPass" id="studentPass" placeholder="登录密码"  maxlength="16"/></td>
									<td align="left"><span id="pass_span"></span></td>
								</tr>
								<tr>
									<td align="right" style="width: 40%;height:50px;">验证码&nbsp;&nbsp;&nbsp;</td>
									<td width="160" align="left"><input type="text" id="yzm"
										onblur="checkcode()" placeholder="图形验证码" maxlength="4"/></td>
									<td align="left"><span id="tsyzm" readonly="readonly"
										onClick="code()"></span>&nbsp;&nbsp;<span id="ss"></span></td>
								</tr>
								<tr>
									<td align="right" style="width: 30%;height:50px;">&nbsp;</td>
									<td align="left">没有账号？<a href="public/gotoRegist"
										class="reg">点我注册</a></td>
									<td align="left">&nbsp;</td>
								</tr>
								<tr>
									<td height="50" align="right">&nbsp;</td>
									<td width="170" align="left">
									<input type="submit" value="登 录" onclick="javascript:return check()" style="width: 70px; height: 30px; color: #FFF; border: 0; background-color: #DE3237;">
										&nbsp;&nbsp;<input type="reset" value="重 置"
										style="width: 70px; height: 30px; color: #FFF; border: 0; background-color: #DE3237;"></td>
									<td align="left">&nbsp;</td>
								</tr>
							</table>
						</form>
				</fieldset>
			</div>
		</div>
		<div class="clear"></div>
		<!--清除浮动-->
	</div>
	<!--content end-->

	<c:import url="../foot.jsp"></c:import>
	<!--footer end-->
	
<script type="text/javascript" src="resources/js/banner1.js"></script>
<script type="text/javascript" src="resources/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="resources/js/login.js"></script>
</body>

</html>
