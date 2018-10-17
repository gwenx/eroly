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
	<title>eroly教育-资料修改</title>
	<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon"/>
	<link href="resources/css/style.css" type="text/css" rel="stylesheet" />
	<link href="resources/css/single.css" type="text/css" rel="stylesheet" />

</head>
<body>
<c:import url="../header.jsp"></c:import>
<div class="content">
   
   <div class="clear"></div>
   <div class="buyDiv">
   
   	<div class="buyHeader">
   		<h1>资料修改</h1>
   		<div class="innerDiv" onclick="history.back();">返 回</div>
        <div class="innerDiv" onclick="alert('修改成功！');">修 改</div>
   	</div>
   	<div class="msgUpdateBody">
    	<fieldset class="msgUpdateField">
        	<legend style="color:#DE3237;font-size:18px;" align="left">资料修改</legend>
               <form>
                 <table class="registTable" style="margin-top:25px;">
                   <tr>
                     <td width="80" height="38" align="right" nowrap="nowrap" style="width:30%;">用&nbsp;&nbsp;户&nbsp;名:</td>
                     <td width="5" height="38" align="left" nowrap="nowrap">&nbsp;</td>
                     <td width="150" height="38" align="left" nowrap="nowrap">${sessionScope.USER_INFO.userName}</td>
                   </tr>
                   <tr>
                     <td width="80" height="38" align="right" nowrap="nowrap">真实姓名<span style="width:30%;">:</span></td>
                     <td width="5" height="38" align="left" nowrap="nowrap">&nbsp;</td>
                     <td width="150" height="38" align="left" nowrap="nowrap"><input type="text" name="studentName" value="${empty sessionScope.student.studentRealName?'尚未完善':sessionScope.student.studentRealName}"></td>
                   </tr>
                   <tr>
                     <td height="38" align="right" nowrap="nowrap">您的性别<span style="width:30%;">:</span></td>
                     <td width="5" height="38" align="left" nowrap="nowrap">&nbsp;</td>
                     <td height="38" align="left" nowrap="nowrap"><input 
                      name="studentSex" type="radio" value="1" checked/>男 <input
                      name="studentSex" type="radio" value="0"
                       <c:if test="${sessionScope.student.studentSex eq '2'}">checked</c:if>>女
                     </td>
                   </tr>
                   <tr>
                     <td width="80" height="38" align="right" nowrap="nowrap">联系电话<span style="width:30%;">:</span></td>
                     <td width="5" height="38" align="left" nowrap="nowrap">&nbsp;</td>
                     <td width="150" height="38" align="left" nowrap="nowrap"><input type="text" name="studentTel"value="${empty sessionScope.student.studentTel?'尚未完善':sessionScope.student.studentTel}"/></td>
                   </tr>
                   <tr>
                     <td width="80" height="38" align="right" nowrap="nowrap">电子邮箱<span style="width:30%;">:</span></td>
                     <td width="5" height="38" align="left" nowrap="nowrap">&nbsp;</td>
                     <td width="150" height="38" align="left" nowrap="nowrap"><input type="text" name="studentEmail" value="${empty sessionScope.student.studentEmail?'尚未完善':sessionScope.student.studentEmail}"/></td>
                   </tr>
                   <tr>
                     <td width="80" height="38" align="right" nowrap="nowrap">您的学历<span style="width:30%;">:</span></td>
                     <td width="5" height="38" align="left" nowrap="nowrap">&nbsp;</td>
                     <td width="150" height="38" align="left" nowrap="nowrap"><select name="educationId" style="width:155px;">
                   	<%-- <c:forEach items="${applicationScope.educationList}" var="education">
					<option value="${education.educationId}" <c:if test="${education.educationId eq sessionScope.student.studentEducation.educationId}">selected</c:if>>${education.educationName}</option>
					</c:forEach> --%>
					<option>硕士</option>
					<option>本科</option>
					<option>高中</option>
					<option>小学</option>
					<option>不详</option>
                 </select>
                   </tr>
                   <tr>
                     <td height="38" align="right" nowrap="nowrap">您的专业<span style="width:30%;">:</span></td>
                     <td width="5" height="38" align="left" nowrap="nowrap">&nbsp;</td>
                     <td height="38" align="left" nowrap="nowrap"><input type="text" name="studentEdu" value="${empty sessionScope.student.studentMajor?'尚未完善':sessionScope.student.studentMajor}"/></td>
                   </tr>
                   <tr>
                     <td width="80" height="38" align="right" nowrap="nowrap">详细地址<span style="width:30%;">:</span></td>
                     <td width="5" height="38" align="left" nowrap="nowrap">&nbsp;</td>
                     <td height="38" colspan="2" align="left" nowrap="nowrap"><input type="text" name="studentAddr" value="${empty sessionScope.student.studentAddr?'尚未完善':sessionScope.student.studentAddr}"/></td>
                   </tr>
                 </table>
               </form>
         </fieldset>
    </div>
   </div>
   <div class="clear"></div><!--清除浮动-->
</div><!--content end-->
<c:import url="../foot.jsp"></c:import>
	<script type="text/javascript" src="resources/js/banner1.js"></script>
</body>
</html>