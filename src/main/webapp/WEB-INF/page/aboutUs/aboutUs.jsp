<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
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
<title>eroly教育-关于我们</title>
<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon"/>

<link href="resources/css/teacher.css" rel="stylesheet" type="text/css" />
<link href="resources/css/style.css" type="text/css" rel="stylesheet" />
<link href="resources/css/single.css" type="text/css" rel="stylesheet" />

<script type="text/javascript">
	/* function baoming(subjectId){
		location.href="${pageContext.request.contextPath }/student/signupjudge.servlet?subjectId="+subjectId;
	} */
</script>
</head>
<body>
<c:import url="../header.jsp"></c:import>
  <!--  <iframe style="width:100%;height:236px" scrolling="no" frameborder="0" src="student/header.jsp"></iframe>-->
	<div class="content">
		<div class="clear"></div>
		<div class="infoDiv">
			<div class="buyHeader">
				<h1>关于我们</h1>
				<div class="innerDiv" onclick="history.back();">返 回</div>
			</div>
			<div class="infoBody">
				<fieldset class="infoField">
					<legend style="color:#DE3237;font-size:18px;" align="left">&nbsp;关于我们&nbsp;</legend>
					<form>
					<legend style="color:#DE3237;font-size:18px;margin-top:20px;margin-bottom:10px;">eroly简介</legend>
					<div style="width:84%;margin-top:20px;" >
						<div id="aboutUs" style="border-radius:10px;margin-left:130px;margin-bottom:20px;margin-top:20px;padding:10px;background-color:#b5d6e6; border:1px red solid;"></div>
					</div>
					<div>
					<br />
					<hr>
					<br />
					<legend style="color:#DE3237;font-size:18px;margin-top:20px;">联系我们</legend>
					<div class="part about05-bg" id="teacher">
						<div class="main">
							<div class="course08">
								<div class="about05-tab-box wow fadeInUp" data-wow-delay="0.7s">
									<div class="about05-tab-box-div clearfix" style="margin-top:20px;">
										<span style="font-size:14px;margin-top:10px;margin-bottom:20px;">联系电话：010-8920010</span><br/>
										<span style="font-size:14px;margin-top:10px;margin-bottom:20px;">新浪微博：eroly教育中心</span><br/>
										<span style="font-size:14px;margin-top:10px;margin-bottom:20px;">咨询邮箱：105011@163.com</span><br/>
										<span style="font-size:14px;margin-top:10px;margin-bottom:20px;">地址：北京市朝阳区朝阳路65号eroly国际IT教育中心 </span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<br />
					</div>
				</form>
			</fieldset>
		</div>
	</div>
	<div class="clear"></div>
	<!--清除浮动-->
</div>
<!--content end-->
<c:import url="../foot.jsp"></c:import>
<script type="text/javascript" src="resources/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="resources/js/aboutUs/aboutUs.js"></script>
<script type="text/javascript" src="resources/js/banner1.js"></script>
</body>
</html>
