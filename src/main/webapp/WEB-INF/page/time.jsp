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

</head>

<body>
	<p>哈哈哈实打实</p>
	<span id="day_show"></span>
	<span id="hour_show"></span>
	<span id="minute_show"></span>
	<span id="second_show"></span>
	<p style="display:none;" id="over">已结束</p>
	<script type="text/javascript" src="resources/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="resources/js/timeOut.js"></script>
</body>
</html>
