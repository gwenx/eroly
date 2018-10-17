<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <link href="resources/css/foot.css"  type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="footer">
    <div class="copyright clearfix01 clear01">
        <div>
            <p>
                版权所有：北京eroly国际IT培训中心 京ICP备05****88号 <br />
                地址：北京市朝阳区朝阳路65号eroly国际IT教育中心 <br /> 咨询邮箱：105011@163.com<br />
            </p>
        </div>
    </div>
</div>
<!--footer end-->
</body>
</html>