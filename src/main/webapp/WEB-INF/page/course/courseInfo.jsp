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
<title>eroly教育-课程详情</title>
<link rel="shortcut icon" href="resources/img/favicon.ico"type="image/x-icon" />
<link href="resources/css/teacher.css" rel="stylesheet" type="text/css" />
<link href="resources/css/style.css" type="text/css" rel="stylesheet" />
<link href="resources/css/single.css" type="text/css" rel="stylesheet" />
<link href="resources/css/course.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<c:import url="../header.jsp"></c:import>
	<div class="content">
		<div class="clear"></div>
		<div class="infoDiv">
			<div class="buyHeader">
				<h1>课程信息-${requestScope.courseMap.courseName}</h1>
				<div class="innerDiv" onclick="history.back();">返 回</div>
				<div class="innerDiv" onclick="javascript:baoming(${requestScope.courseMap.courseId})">报名</div>
			</div>
			<div class="infoBody">
				<fieldset class="infoField">
					<legend style="color: #DE3237; font-size: 18px;" align="left">${requestScope.courseMap.courseName}</legend>
					<form style="margin-top: 20px;">
						<legend style="color: #DE3237; font-size: 18px;">课程信息</legend>
						<div class="tableDiv">
							<div class="courseDetailDiv">
								<div class="floatDiv1">
									<div title="课程名称">课程名称: ${requestScope.courseMap.courseName}</div>
									<div title="总课时数">课时总数: ${requestScope.courseMap.totalNode}课时</div>
									<div title="报名截止日期">截止日期: ${requestScope.courseMap.priceDiedline}</div>
									<div title="开课日期">开课日期: ${requestScope.courseMap.teachingStart}</div>
								</div>
								<div class="floatDiv2">
									<div title="报名本课程需要${requestScope.courseMap.totalPrice}元">课程价格: ￥${requestScope.courseMap.totalPrice}</div>
									<div title="资金不足可以选择先付定金${requestScope.courseMap.priceDeposit}元">报名定金: ￥${requestScope.courseMap.priceDeposit}</div>
									<div title="一次性缴费成功则减免${requestScope.courseMap.priceDiscount}元">全款优惠: ￥${requestScope.courseMap.priceDiscount}</div>
									<div title="结课日期">结课日期: ${requestScope.courseMap.teachingEnd}</div>
								</div>
							</div>
						</div>
						<!-- 课程简介 -->
						<div>
							<legend style="color: #DE3237; font-size: 18px; padding-top: 20px;">课程简介</legend>
							<div class="courseIntroduce">
								${requestScope.courseMap.courseInfo}
								</tr>
							</div>
						</div>
						<hr>
						<br />
						<legend
							style="color: #DE3237; font-size: 18px; padding-top: 20px;">课程详情</legend>
						<br />
						<c:forEach items="${requestScope.chapterList }" var="subjectInfo" varStatus="status">
						<div class="big">
							<div class="couese-div" style="text-align: left;" title=" ${subjectInfo.chapterInfo}">
								<span>第${subjectInfo.chapterIndex}章</span> ${subjectInfo.chapterName}&nbsp;&nbsp;共${subjectInfo.nodeCount}课时&nbsp;&nbsp;${subjectInfo.chapterInfo}<br>
							</div>
							<div class="node">
							<c:forEach items="${subjectInfo.NODE }" var="node">
								<div class="nodeDiv ${status.count}" style="text-align: left;" title=" ${node.nodeInfo}">
									<span>第${node.nodeIndex}节 </span>${node.nodeName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;简介:${node.nodeInfo}<br>
								</div>
							</c:forEach>
							</div>
						</div>
						</c:forEach>
						<div>
							<br />
							<br />
						<hr>
							<br />
							<br />
							<legend style="color: #DE3237; font-size: 18px;">
								授课老师<br/>
								<span style="font-size: 10px;">（声明：以下照片禁止作为商业用途！）</span>
							</legend>
							<div class="part about05-bg" id="teacher">
								<div class="main">
									<div class="course08">
										<div class="about05-tab-box wow fadeInUp"
											data-wow-delay="0.7s">
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
	<c:import url="../foot.jsp"></c:import>
	<script type="text/javascript" src="resources/js/jquery-3.2.0.min.js"></script>
	<script type="text/javascript" src="resources/js/teacher/teacher.js"></script>
	<script type="text/javascript" src="resources/js/banner1.js"></script>
	<script type="text/javascript">
		function baoming(courseId){
			location.href="${pageContext.request.contextPath }/signUp/toSignUp?courseId="+courseId;
		}
</script>
</body>
</html>
