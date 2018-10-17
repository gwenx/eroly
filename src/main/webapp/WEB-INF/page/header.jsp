<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
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
<meta charset="UTF-8">
<title>eroly教育</title>
<link rel="stylesheet" type="text/css" href="resources/css/global.css" />
<link rel="stylesheet" type="text/css" href="resources/css/header.css" />

<style type="text/css">
body, div, dl, dd, dt, p, a, form, select, input, span, ul, ol, li {
	margin: 0px;
	padding: 0px;
	text-indent: 0;
	font-weight: normal;
	font-size: 14px;
	font-family: 'Microsoft YaHei', Verdana, Arial, Helvetica, sans-serif;
	color: #363636;
}
</style>
</head>

<body>
	<!--top begin-->
<div class="headWraper">
	<div class="top">
		<div class="wrap">
			<span class="fl">欢迎您来到<a href="public/gotoIndex" title="eroly教育"
				target="_top">eroly教育网</a></span> <span class="fr"><c:choose>
					<c:when test="${empty sessionScope.USER_INFO }">
						<a href="public/gotoLogin" title="登录" target="_top">你好，请登录</a>&nbsp;|&nbsp;<a
							href="public/gotoRegist" title="立即注册" target="_top">立即注册</a>&nbsp;&nbsp;</c:when>
					<c:otherwise>欢迎&nbsp;&nbsp;<span style="color:red">${sessionScope.USER_INFO.userName}</span>&nbsp;&nbsp;<a
							href="public/user/gotoMessage" title="个人中心" target="_top">个人中心</a>&nbsp;|&nbsp;<a
							href="user/userLogout" id="logout" onclick="javascript:return logout()" title="安全退出" target="_top">安全退出</a>
					</c:otherwise>
				</c:choose></span>
		</div>
	</div>
	<!--top end-->
	<!--header begin-->
	<div class="header">
		<div class="nav">
			<div class="wrap">
				<ul class="nav_left">
					<li><a
						href="${pageContext.request.contextPath }/public/gotoIndex"
						title="" target="_top">首 页</a></li>
					<li><a
						href="${pageContext.request.contextPath }/course/findCourseInfo"
						title="" target="_top">开设课程</a></li>
					<li><a
						href="${pageContext.request.contextPath }/public/user/gotoSignUp"
						title="" target="_top">我要报名</a></li>
					<li><a
						href="${pageContext.request.contextPath }/public/goGra"
						title="" target="_top">就业情况</a></li>
					<li><a
						href="${pageContext.request.contextPath }/public/gotoTeacher"
						title="" target="_top">教师风采</a></li>
					<li><a
						href="${pageContext.request.contextPath }/public/gotoAboutUs"
						title="" target="_top">关于我们</a></li>
				</ul>
				
				<ul class="nav_right">
					<li>
						<label class="serachLab">
						<form action="course/findCourseByKeyWords" method="post">
							<input type="text" id="subjectId" name="courseName" 
								value="${requestScope.courseName}" placeholder="输入你想学的课程" class="sertxt" />
							<button type="submit" value="搜 索" onclick="javascript:return checkname()" class="serbtn" ><em class="icon18">&nbsp;</em></button>
						</form>
						</label>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="resources/js/settab.js"></script>
<script type="text/javascript" src="resources/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="resources/js/header/header.js"></script>
</body>
</html>
