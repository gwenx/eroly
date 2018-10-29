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
<title>eroly教育-教师风采</title>
<link rel="shortcut icon" href="resources/img/favicon.ico"
	type="image/x-icon" />

<link href="resources/css/teacher.css" rel="stylesheet" type="text/css" />
<link href="resources/css/style.css" type="text/css" rel="stylesheet" />
<link href="resources/css/single.css" type="text/css" rel="stylesheet" />

</head>
<body>
	<c:import url="../header.jsp" charEncoding="UTF-8"></c:import>
	<div class="content">
		<div class="clear"></div>
		<div class="infoDiv">
			<div class="buyHeader">
				<h1>教师风采</h1>
				<div class="innerDiv" onclick="history.back();">返 回</div>
			</div>
			<div class="infoBody">
				<fieldset class="infoField">
					<legend style="color: #DE3237; font-size: 18px;" align="left">&nbsp;教师风采&nbsp;</legend>
					<form>
						<div>
							<br />
							<legend style="color: #DE3237; font-size: 18px;">
								最可爱的人<br/><br/>
								<span style="font-size: 10px;">（声明：以下照片禁止作为商业用途！）</span>
							</legend>
							<div class="part about05-bg" id="teacher">
								<div class="main">
									<div class="course08">
										<div class="about05-tab-box wow fadeInUp" data-wow-delay="0.7s">
											<!--Java-->
											<div class="about05-tab-box-div clearfix">
												<c:forEach items="${requestScope.teacherList }"
													var="teacher">
													<ul class="about05-ul-tab-box">
														<img src="${teacher.teacherImg}" />
														<li class="li01"><font size="+1">${teacher.teacherName}</font><br />${teacher.teacherPosition}</li>
														<li class="li02">
															<p class="p01">
																<img src="img/about-jt02.png" class="jt02" />
															</p>
															<p class="p02">${teacher.teacherIntroduce}</p>
														</li>
													</ul>
												</c:forEach>
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
	<c:import url="../foot.jsp" charEncoding="UTF-8"></c:import>
	<script type="text/javascript" src="resources/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="resources/js/teacher/teacher.js"></script>
<script type="text/javascript" src="resources/js/banner1.js"></script>
<script type="text/javascript">
	function baoming(subjectId){
		location.href="${pageContext.request.contextPath }/student/signupjudge.servlet?subjectId="+subjectId;
	}
</script>
</body>
</html>
