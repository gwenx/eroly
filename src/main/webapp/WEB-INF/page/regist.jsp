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
<title>eroly教育-注册</title>
<link rel="shortcut icon" href="resources/img/favicon.ico"
	type="image/x-icon" />
<link href="resources/css/style.css" type="text/css" rel="stylesheet" />
<link href="resources/css/single.css" type="text/css" rel="stylesheet" />

<style type="text/css">
.errorTip {
	color: red;
}

.okTip {
	color: green;
}

body, div, dl, dd, dt, p, a, form, select, input, span, ul, ol, li,button {
	margin: 0px;
	padding: 0px;
	text-indent: 0;
	font-weight: normal;
	font-size: 14px;
	font-family: 'Microsoft YaHei', Verdana, Arial, Helvetica, sans-serif;
	color: #363636;
}
#getSMsCode{
	width: 100px;
    line-height: 30px;
    font-size: 16px;
    background-color: #2E8B57;
    color: white;
    border: 0;
    border-radius: 5px;
    height: 30px;
}
#getSMsCode:hover{
	background-color: #006400;
}
td>input{
	height:30px;;
	width:100%;
}
</style>
</head>

<body>
	<c:import url="header.jsp" charEncoding="UTF-8"></c:import>
	<div class="content">
		<div class="clear"></div>
		<div class="loginDiv">
			<div class="buyHeader">
				<h1>用户注册</h1>
				<div class="innerDiv" onclick="history.back();">返 回</div>
			</div>
			<div class="loginBody">
				<fieldset class="regField">
					<legend style="color: #DE3237; font-size: 18px;" align="left">&nbsp;注册&nbsp;</legend>
					<form action="student/regist.servlet" method="post" >
						<table width="80%" class="logTable">
							<tr>
								<td align="right" style="width: 30%;">&nbsp;</td>
								<td align="left"><span id="tip_span" style="color: red">${requestScope.error}</span></td>
								<td align="left" nowrap="nowrap">&nbsp;</td>
							</tr>
							<tr>
								<td align="right" style="width: 40%;height:50px;">姓名&nbsp;&nbsp;&nbsp;</td>
								<td width="190" align="left"><input type="text"
									id="loginName" placeholder="请使用真实姓名" name="studentLoginName" /></td>
								<td align="left" nowrap="nowrap"><span id="loginName_span">&nbsp;&nbsp;&nbsp;用户名6-12位数字字母下划线</span></td>
							</tr>
							<tr>
								<td align="right" style="width: 40%;height:50px;">电子邮箱&nbsp;&nbsp;&nbsp;</td>
								<td width="160" align="left"><input type="text"
									id="email" placeholder="请填写电子邮箱" name="studentLoginName" /></td>
								<td align="left" nowrap="nowrap"><span id="loginName_span">&nbsp;&nbsp;&nbsp;常用邮箱</span></td>
							</tr>
							<tr>
								<td align="right" style="width: 40%;height:50px;">手机号码&nbsp;&nbsp;&nbsp;</td>
								<td width="190" align="left"><input type="text"
									id="phoneNum" value="" placeholder="请填写手机号码" name="studentLoginName" /></td>
								<td align="left" nowrap="nowrap"><span id="loginName_span">&nbsp;&nbsp;&nbsp;11位手机号码</span></td>
							</tr>
							<tr>
								<td align="right" style="width: 40%;height:50px;">短信验证码&nbsp;&nbsp;&nbsp;</td>
								<td width="190" align="left"><input type="text"
									id="smsCode" placeholder="请输入6位短信验证码" name="studentLoginName" /></td>
								<td align="left" nowrap="nowrap">&nbsp;&nbsp;&nbsp;<button type="button" id="getSMsCode">免费获取</button></td>
							</tr>
							<tr>
								<td align="right">&nbsp;</td>
								<td align="left">&nbsp;</td>
								<td align="left">&nbsp;</td>
							</tr>
							<tr>
								<td align="right">&nbsp;</td>
								<td width="190" align="left"><input type="submit"
									id="next" value="下一步" onclick="javascript:return check()"
									style="width: 70px; height: 30px; color: #FFF; border: 0; background-color: #DE3237;">
									&nbsp; <input type="reset" value="重 置"
									style="width: 70px; height: 30px; color: #FFF; border: 0; background-color: #DE3237;"></td>
								<td align="left">&nbsp;</td>
							</tr>
						</table>
					</form>
					<form action="student/regist.servlet" method="post" style="display:none">
						<table width="80%" class="logTable">
							<tr>
								<td align="right" style="width: 30%;height: 50px;">&nbsp;</td>
								<td align="left"><span id="tip_span" style="color: red">${requestScope.error}</span></td>
								<td align="left" nowrap="nowrap">&nbsp;</td>
							</tr>
							
							<tr>
								<td align="right" style="width: 40%;height: 50px;">登录密码&nbsp;&nbsp;&nbsp;<span style="width:30%;"></span></td>
								<td width="190" align="left"><input type="password" placeholder="设置登录密码"
									id="password" name="studentPass" /></td>
								<td align="left" nowrap="nowrap"><span id="pass_span">&nbsp;&nbsp;&nbsp;密码6-18位数字字母下划线</span></td>
							</tr>
							<tr>
								<td align="right" style="width: 40%;height: 50px;">确认密码&nbsp;&nbsp;&nbsp;<span style="width:30%;"></span></td>
								<td width="190" align="left"><input type="password" placeholder="再次确认"
									id="repassword" /></td>
								<td align="left" nowrap="nowrap"><span id="repass_span">&nbsp;&nbsp;&nbsp;再次输入</span></td>
							</tr>
							<tr>
								<td align="right">&nbsp;</td>
								<td align="left">&nbsp;</td>
								<td align="left">&nbsp;</td>
							</tr>
							<tr>
								<td align="right">&nbsp;</td>
								<td width="160" align="left"><input type="submit"
									id="regist_btn" value="注 册" onclick="javascript:return check2()"
									style="width: 70px; height: 30px; color: #FFF; border: 0; background-color: #DE3237;">
									&nbsp; <input type="reset" value="重 置"
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
	<c:import url="foot.jsp" charEncoding="UTF-8"></c:import>
	<script type="text/javascript" src="resources/js/banner1.js"></script>
<script type="text/javascript" src="resources/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="resources/js/regist/regist.js"></script>
	<!--footer end-->
</body>
</html>
