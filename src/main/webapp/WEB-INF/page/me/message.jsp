<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<title>eroly教育-个人中心</title>
	<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon"/>
    <link href="resources/css/style.css" type="text/css" rel="stylesheet" />
    <link href="resources/css/single.css" type="text/css" rel="stylesheet" />
    <style type="text/css">
        #tipdiv p{margin-top:7px;font-size:16px;}
    </style>
  </head>
  <body>
<c:import url="../header.jsp" charEncoding="UTF-8"></c:import>
<div class="content">
   <div class="clear"></div>
   <div class="buyDiv">
   	<div class="buyHeader">
   		<h1>个人中心</h1>
   		<div class="innerDiv" onclick="history.back();">返 回</div>
        <div class="innerDiv" onclick="location.href='public/user/msgUpdate'">修改个人资料</div>
        <div class="innerDiv" onclick="location.href='public/user/passUpdate'">修改密码</div>
   	</div>
   	<div class="buyBody">
    	<fieldset class="registField">
        	<legend style="color:#DE3237;font-size:18px;" align="left">个人中心</legend>
               <form>
               <table class="registTable" style="margin-top:25px;">
               <tr>
               	<td width="80" height="38" align="right" nowrap="nowrap" style="width:30%;">用&nbsp;&nbsp;户&nbsp;名:</td>
               	<td width="10" height="38" align="left" nowrap="nowrap">&nbsp;</td>
                <td width="150" height="38" align="left" nowrap="nowrap">${sessionScope.USER_INFO.userName}</td>
                <td width="80" height="38" align="right" nowrap="nowrap">已报课程<span style="width:30%;">:</span></td>
                <td width="10" height="38" align="center" nowrap="nowrap">&nbsp;</td>
                <td height="38" align="left" nowrap="nowrap">${empty sessionScope.student.studentSubject.subjectName?"尚未报名":sessionScope.student.studentSubject.subjectName}</td>
               </tr>
               <tr>
               	<td width="80" height="38" align="right" nowrap="nowrap">真实姓名<span style="width:30%;">:</span></td>
               	<td width="10" height="38" align="left" nowrap="nowrap">&nbsp;</td>
                <td width="150" height="38" align="left" nowrap="nowrap">${empty sessionScope.student.studentRealName?"尚未完善":sessionScope.student.studentRealName}</td>
                <td width="80" height="38" align="right" nowrap="nowrap">报名时间:</td>
                <td width="10" height="38" align="center" nowrap="nowrap">&nbsp;</td>
                <td height="38" align="left" nowrap="nowrap">${empty sessionScope.student.studentApplyDate?"尚未报名":sessionScope.student.studentApplyDate}</td>
               </tr>
               <tr>
                 <td height="38" align="right" nowrap="nowrap">您的性别<span style="width:30%;">:</span></td>
                 <td height="38" align="left" nowrap="nowrap">&nbsp;</td>
                 <td height="38" align="left" nowrap="nowrap"><c:choose><c:when test="${sessionScope.student.studentSex eq 0}">尚未完善</c:when>
                   <c:otherwise><c:choose>
                       <c:when test="${sessionScope.USER_INFO.userSex eq '1'}">男</c:when>
                       <c:otherwise>女</c:otherwise>
                     </c:choose>
                 </c:otherwise></c:choose></td>
                 <td height="38" align="right" nowrap="nowrap">开课时间:</td>
                 <td height="38" align="center" nowrap="nowrap">&nbsp;</td>
                 <td height="38" align="left" nowrap="nowrap">${empty sessionScope.student.studentSubject.subjectBeginDate?"尚未报名":sessionScope.student.studentSubject.subjectBeginDate}</td>
               </tr>
               <tr>
                 <td width="80" height="38" align="right" nowrap="nowrap">联系电话<span style="width:30%;">:</span></td>
                 <td width="10" height="38" align="left" nowrap="nowrap">&nbsp;</td>
                 <td width="150" height="38" align="left" nowrap="nowrap">${empty sessionScope.student.studentTel?"尚未完善":sessionScope.student.studentTel}</td>
                 <td width="80" height="38" align="right" nowrap="nowrap">课程状态:</td>
                 <td width="10" height="38" align="center" nowrap="nowrap">&nbsp;</td>
                 <td height="38" align="left" nowrap="nowrap">${empty sessionScope.student.studentSubjectState.subjectStateName?"尚未报名":sessionScope.student.studentSubjectState.subjectStateName}</td>
               </tr>
               <tr>
               	<td width="80" height="38" align="right" nowrap="nowrap">电子邮箱<span style="width:30%;">:</span></td>
               	<td width="10" height="38" align="left" nowrap="nowrap">&nbsp;</td>
                <td width="150" height="38" align="left" nowrap="nowrap">${empty sessionScope.student.studentEmail?"尚未完善":sessionScope.student.studentEmail}</td>
                <td width="80" height="38" align="right" nowrap="nowrap">缴费状态:</td>
                <td width="10" height="38" align="center" nowrap="nowrap">&nbsp;</td>
                <td height="38" align="left" nowrap="nowrap">${empty sessionScope.student.studentState.studentStateName ? '尚未报名':sessionScope.student.studentState.studentStateName}<c:if test="${sessionScope.student.studentState.studentStateId eq '2'}"><a 
                	href="student/signup/pay.jsp" id="paymoney" style="font-size:14px;color:green;">去缴费</a></c:if></td>
               </tr>
               <tr>
               	<td width="80" height="38" align="right" nowrap="nowrap">您的学历<span style="width:30%;">:</span></td>
               	<td width="10" height="38" align="left" nowrap="nowrap">&nbsp;</td>
                <td width="150" height="38" align="left" nowrap="nowrap">${empty sessionScope.student.studentEducation.educationName?"尚未完善":sessionScope.student.studentEducation.educationName}</td>
                <td width="80" height="38" align="right" nowrap="nowrap">您的专业<span style="width:30%;">:</span></td>
                <td width="10" height="38" align="center" nowrap="nowrap">&nbsp;</td>
                <td height="38" align="left" nowrap="nowrap">${empty sessionScope.student.studentMajor?"尚未完善":sessionScope.student.studentMajor}</td>
               </tr>
               <tr>
               	<td width="80" height="38" align="right" nowrap="nowrap">详细地址<span style="width:30%;">:</span></td>
                <td width="10" height="38" align="left" nowrap="nowrap">&nbsp;</td>
                <td height="38" colspan="4" align="left" nowrap="nowrap">${empty sessionScope.student.studentAddr?"尚未完善":sessionScope.student.studentAddr}</td>
                </tr>
               </table>
               
               </form>
         <div style="height:170px;padding-top:20px;">
         <hr>
           <legend style="color:#DE3237;font-size:18px;margin-top:10px;" align="left">温馨提示</legend>
           <div id="tipdiv">
           	<c:choose>
           		<c:when test="${not empty sessionScope.student.studentRealName}">
           			<p>请您在开课日期之前缴课程押金或课程全款，报到当天持</p>
           			<p>三张一寸证件照和有效证件到北京市海淀区四季青桥杏石</p>
           			<p>口路eroly国际IT培训中心报到。如有急事不能在开课当天</p>
           			<p>报到，请务必提前与我们取得联系！</p>
           			<p>联系电话：0010-88669678</p>
           		</c:when>
           		<c:otherwise>
           		<p style="margin-top:20px;">您还未报名哟~~<a href="${pageContext.request.contextPath }/course/findCourseInfo" style="font-size:14px;">点我了解课程</a></p>
           		</c:otherwise>
           	</c:choose>
           </div>
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