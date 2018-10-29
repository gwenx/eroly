<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
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
	<script type="text/javascript">
		function goPage(page){
			location="${pageContext.request.contextPath }/student/subjectfindall.servlet?page="+page;
		}
	</script>
<title>eroly教育-开设课程</title>
<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon"/>

<link href="resources/css/style.css" type="text/css" rel="stylesheet" />
<link href="resources/css/single.css" type="text/css" rel="stylesheet" />


<style type="text/css">
body,div,dl,dd,dt,p,a,form,select,input,span,ul,ol,li{
	margin:0px; padding:0px; text-indent:0; 
	font-weight:normal;
	font-size:14px;
	font-family:'Microsoft YaHei',Verdana, Arial, Helvetica, sans-serif;
	color:#363636;
}
.classA{
	font-size:16px;
}
</style>
</head>
<body>
	<c:import url="../header.jsp" charEncoding="UTF-8"></c:import>
	<div class="content">
		<div class="clear"></div>
		<div class="buyDiv">
			<div class="buyHeader">
				<c:choose>
					<c:when test="${empty requestScope.haveCourse }"><h1>开设课程</h1></c:when>
					<c:otherwise><h1>搜索结果</h1></c:otherwise>
				</c:choose>
				<div class="innerDiv" onclick="back()">返 回</div>
			</div>
			<div class="buyBody">
            	<div style="height: 780px;">
				<table  class="buyTable" border="1" bordercolor="#b5d6e6" style="border: 1px solid #5bbdec;width:100%"
				 cellpadding="0" cellspacing="0" bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()">	
					<c:choose>
						<c:when test="${requestScope.haveCourse eq '0'}"></c:when>
						<c:otherwise>
							<thead>
								<tr>
									<th height="60px;" height="50" nowrap="nowrap" bgcolor="#b5d6e6">序号</th>
									<th height="50" bgcolor="#b5d6e6">课程名称</th>
									<th height="50" bgcolor="#b5d6e6">课时</th>
									<th height="50" bgcolor="#b5d6e6">课程简介</th>
									<th height="50" bgcolor="#b5d6e6">费用</th>
									<th height="50" bgcolor="#b5d6e6">操作</th>
								</tr>
							</thead>
						</c:otherwise>
					</c:choose>
					<tbody id="body_data" style="font-size:18px;">
    				<c:choose>
    					<c:when test="${empty requestScope.courseList}">
    					<c:choose>
    						<c:when test="${empty requestScope.haveCourse }">
	    						<tr>
	    							<td colspan="6" style="height:750px;color:red;text-align: center;">抱歉，未查询到相关课程</td>
	    						</tr>
    						</c:when>
    						<c:otherwise>
	    						<tr>
	    							<td colspan="6" style="height:750px;color:red;text-align: center;">抱歉，未查询到与“${requestScope.courseName}”相关的课程</td>
	    						</tr>
    						</c:otherwise>
    					</c:choose>
    					</c:when>
    				<c:otherwise>
					<c:forEach items="${requestScope.courseList }" var="subject" varStatus="status">
						<c:choose>
							<c:when test="${status.count%2==0}">
								<tr>
									<td height="120px;" width="50px;" align="center"  bgcolor="#E5E5E5">${status.count}</td>
									<td width="150px;" align="center"  bgcolor="#E5E5E5">${subject.courseName}</td>
									<td width="80px;" align="center"  bgcolor="#E5E5E5">${subject.totalNode }学时</td>
									<td align="center" bgcolor="#E5E5E5"><p style="padding:10px;">${subject.courseInfo }</p></td>
									<td width="100px;" align="center" bgcolor="#E5E5E5">
										<span style="color:green;font-size:18px;">￥${subject.totalPrice }</span>
									</td>
									<td width="100px;" align="center"  bgcolor="#E5E5E5">
									<a class="classA"  style="font-size:16px;" href="${pageContext.request.contextPath }/course/findCourseById?courseId=${subject.courseId}" target="_blank">详情
										</a> | <a class="classA"  style="font-size:16px;" href="${pageContext.request.contextPath }/signUp/toSignUp?courseId=${subject.courseId}">报名</a></td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td height="120px;" width="50px;" align="center"  bgcolor="#F4F4F4">${status.count}</td>
									<td width="150px;" align="center"  bgcolor="#F4F4F4">${subject.courseName}</td>
									<td  width="80px;" align="center"  bgcolor="#F4F4F4">${subject.totalNode }学时</td>
									<td  align="center"  bgcolor="#F4F4F4"><p style="padding:10px;">${subject.courseInfo }</p></td>
									<td width="100px;" align="center"  bgcolor="#F4F4F4">
										<span style="color:green;font-size:18px;">￥${subject.totalPrice }</span>
									</td>
									<td width="100px;" align="center"  bgcolor="#F4F4F4">
									<a class="classA" style="font-size:16px;" href="${pageContext.request.contextPath }/course/findCourseById?courseId=${subject.courseId}" target="_blank">详情
										</a> | <a class="classA"  style="font-size:16px;" href="${pageContext.request.contextPath }/signUp/toSignUp?courseId=${subject.courseId}">报名</a></td>
								</tr>
							</c:otherwise>
						</c:choose>
					</c:forEach>
    				</c:otherwise>
					</c:choose>
				</table>
                </div>
                <c:choose>
					<c:when test="${requestScope.haveCourse eq '0'}"></c:when>
					<c:otherwise>
						<div class="graInfoPage clearfix">
							<span>共${requestScope.pageVO.recordCount}项课程
							第${requestScope.pageVO.page}/${requestScope.pageVO.pageCount} 页</span>
							<c:choose><c:when test="${requestScope.pageVO.page eq 1}">
							<a>上一页</a>
							</c:when><c:otherwise>
							<a href="javascript:goPage(${requestScope.pageVO.page-1 })">上一页</a>
							</c:otherwise>
						  </c:choose>
							<c:choose><c:when test="${requestScope.pageVO.page eq requestScope.pageVO.pageCount}">
							<a>下一页</a>
							</c:when><c:otherwise>
							<a href="javascript:goPage(${requestScope.pageVO.page+1 })">下一页</a>
							</c:otherwise>
							</c:choose>
							<c:choose><c:when test="${requestScope.pageVO.page eq requestScope.pageVO.pageCount}">
							<a>末页</a></c:when><c:otherwise>
								<a href="javascript:goPage(${requestScope.pageVO.pageCount })">末页</a>
								</c:otherwise>
							</c:choose>
					  </div>
					</c:otherwise>
				</c:choose>
		  </div>
		</div>
		<div class="clear"></div>
		<!--清除浮动-->
	</div>
	<c:import url="../foot.jsp" charEncoding="UTF-8"></c:import>
	<script type="text/javascript" src="resources/js/banner1.js"></script>
	<script type="text/javascript">
		function back(){
			/* $('#subjectId').val(""); */
			history.back();
			<%-- <% request.removeAttribute("regErr");%> --%>
		}
	</script>
</body>
</html>