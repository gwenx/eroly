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
    <!-- 5秒后跳转
    <meta http-equiv="refresh" content="5;URL=${pageContext.request.contextPath }/student/signup/message.jsp"></meta>
     -->
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<title>eroly教育-报名成功</title>
	<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon"/>
	<link href="resources/css/style.css" type="text/css" rel="stylesheet" />
	<link href="resources/css/single.css" type="text/css" rel="stylesheet" />

	<style type="text/css">
	body,div,dl,dd,dt,p,a,form,select,input,span,ul,ol,li{
	margin: 0px;
	padding: 0px;
	text-indent: 0;
	font-weight: normal;
	font-size: 14px;
	font-family: 'Microsoft YaHei',Verdana, Arial, Helvetica, sans-serif;
	color: #363636;
}
#tipdiv p{
margin-top:40px;font-size:16px;
}
    </style>
    <script type="text/javascript">
    	function toIndex(){
    		location.href="${pageContext.request.contextPath}/student/index.jsp";
    	}
    </script>
  </head>
  
  <body>
<c:import url="../header.jsp" charEncoding="UTF-8"></c:import>
  <!--  <iframe style="width:100%;height:236px" scrolling="no" frameborder="0" src="student/header.jsp"></iframe>-->

<div class="content">
   
   <div class="clear"></div>
   <div class="buyDiv">
   
   	<div class="buyHeader">
   		<h1>报名结果</h1>
   		<div class="innerDiv" onclick="toIndex()">返 回</div>
   	</div>
   	
   	<div class="successBody">
    	<fieldset class="regField">
        	<legend style="color:#DE3237;font-size:18px;" align="left">报 名 成 功 </legend>
        	<div id="tipdiv">
        		<p>恭喜${param.studentRealName}同学，您已选择eroly教育的《
java开发全套精编教程》课程，</p>
        		<p>开课时间${requestScope.subject.subjectBeginDate}，请于此前交</p>
        		<p>定课程定金或全额学费，并携带本人近期一寸照3张，到北京</p>
        		<p>市海淀区四季青桥杏石口路eroly国际IT培训中心报到。</p>
        		<p>联系电话：0010-88669678</p>
        	</div>
        </fieldset>
    </div>
   </div>
   <div class="clear"></div><!--清除浮动-->
</div><!--content end-->
	<c:import url="../foot.jsp" charEncoding="UTF-8"></c:import>
		<script type="text/javascript" src="resources/js/banner1.js"></script>
</body>
</html>
