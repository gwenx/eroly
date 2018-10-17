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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<title>eroly教育-课程报名</title>
    <link href="resources/css/bootstrap.css" type="text/css" rel="stylesheet" />
	<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon"/>
	<link href="resources/css/style.css" type="text/css" rel="stylesheet" />
	<link href="resources/css/single.css" type="text/css" rel="stylesheet" />
	<style type="text/css">
		.registTable{color:black;}
		body,div,dl,dd,dt,p,a,form,select,input,span,ul,ol,li{
			margin: 0px;padding: 0px;
			text-indent: 0;font-weight: normal;font-size: 14px;
			font-family: 'Microsoft YaHei',Verdana, Arial, Helvetica, sans-serif;color: #363636;
		}
		td.span{font-size:14px;}
		
  	</style>
</head>
<body>
<div class="zzc">
 <div class="tk">
 	  <div class="title">提示：</div>
 	  <div class="tipContent"><span class="spanTip"></span></div>
 	  <div class="foot">确定</div>
 </div>
</div>
<c:import url="../header.jsp"></c:import>
<div class="content">
   <div class="clear"></div>
   <div class="signDiv">
   	<div class="buyHeader">
   		<h1>我要报名</h1>
   		<div class="innerDiv" onclick="history.back();">返 回</div>
   	</div>
   	<div class="signupBody">
    	<fieldset class="signupField">
        	<legend style="color:#DE3237;font-size:18px;width:100px;border: 0;" align="left">报 名 信 息</legend>
              <div class="stepOne">
	              	<table class="registTable">
		               <tr class="text-danger bg-info">
		               	<td align="right" style="width: 250px;height: 50px;"><b>报名课程：&nbsp;&nbsp;</b></td>
		                <td align="left"  style="width: 350px;"><span>${requestScope.courseMap.courseName}</span></td>
		               </tr>
		               <tr class="text-danger bg-info">
		                 <td align="right"  style="height: 50px;"><b>课程费用：&nbsp;&nbsp;</b></td>
		                 <td align="left">
		                 	<span id="subjectCost"><c:choose>
		                 		<c:when test="${empty requestScope.courseMap.totalPrice}">￥15000.0</c:when>
		                 		<c:otherwise>￥${requestScope.courseMap.totalPrice}</c:otherwise>
		                 	</c:choose></span>
		                 	</td>
		               </tr>
		               <tr class="text-danger bg-info"  style=" border-radius: 3px;">
		                 <td align="right"  style="height: 50px;"><b>课程定金：&nbsp;&nbsp;</b></td>
		                 <td align="left"><span id="subjectDeposit"><c:choose>
		                 		<c:when test="${empty requestScope.courseMap.priceDeposit}">￥2000.0</c:when>
		                 		<c:otherwise>￥${requestScope.courseMap.priceDeposit}</c:otherwise>
		                 	</c:choose></span></td>
		               </tr>
	               </table>
                   <span class="nextBtn next">下一步</span>
              </div>
               
               <!-- 第一步结束 -->
               <div class="stepTwo hidden">
	              <!--  <form action="signUp/signUp" method="post" name="baoming"> -->
	               <input type="hidden" id="courseId" size="20" value="${requestScope.courseId}" />
	               	<table style="margin-left: 140px;">
	               		<tr>
		               	<td align="right"  style="width: 40%;height: 50px;">你的名字&nbsp;&nbsp;</td>
		                <td width="190" align="left"><input type="text" id="studentRealName" size="20" value="${sessionScope.USER_INFO.userName}"/></td>
		                <td align="left" nowrap="nowrap"><span id="name_span">请填写您的真实姓名</span></td>
		               </tr>
		               <tr>
		                 <td align="right"  style="width: 40%;height: 50px;">您的性别&nbsp;&nbsp;</td>
		                 <td width="190" align="left"><input name="studentSex" type="radio" value="1" checked />
		                 男 <input name="studentSex" type="radio" value="2" <c:if test="${sessionScope.student.studentSex eq '2'}">checked</c:if>>
		                 女</td>
		                 <td align="left">&nbsp;</td>
		               </tr>
		               <tr>
		                 <td align="right"  style="width: 40%;height: 50px;">你的学历&nbsp;&nbsp;</td>
		                 <td width="190" align="left"><select name="educationId" style="width:161px;">
		                 	<option value="1">&nbsp;-------请选择-------</option>
		                 	<option value="2">小学</option>
		                 	<option value="3">初中</option>
		                 	<option value="4">高中</option>
		                 	<option value="5">大专</option>
		                 	<option value="6">本科</option>
		                 	<option value="7">硕士</option>
		                 	<option value="8">博士</option>
		                   <%-- <c:forEach items="${applicationScope.educationList}" var="education">
							<option value="${education.educationId}">${education.educationName}</option>
							</c:forEach> --%>
		                 </select></td>
		                 <td align="left" nowrap="nowrap"><span>请选择学历便于分情况教学</span></td>
		               </tr>
		               <tr>
		                 <td align="right"  style="width: 40%;height: 50px;">你的专业&nbsp;&nbsp;</td>
		                 <td width="190" align="left"><input type="text" name="studentMajor" value="${sessionScope.student.studentMajor}" size="20"/></td>
		                 <td align="left" nowrap="nowrap"><span id="major_span">请填写您的专业（选填）</span></td>
		               </tr>
		               
		               <tr>
		                 <td align="right"  style="width: 40%;height: 50px;">电子邮箱&nbsp;&nbsp;</td>
		                 <td width="190" align="left"><input type="text" id="email_inp" value="${sessionScope.USER_INFO.userEmail}"name="studentEmail" size="20" onfocus="if(this.value=='输入电子邮箱') this.value='';" onblur="if(this.value=='') this.value='输入电子邮箱'; else chkEmail(this.value)" value="输入电子邮箱"/></td>
		                 <td align="left" nowrap="nowrap"><span id="email">请填写您的邮箱</span></td>
		               </tr>
		               
		               <tr>
		               	<td align="right"  style="width: 40%;height: 50px;">联系电话&nbsp;&nbsp;</td>
		                <td width="170" align="left"><input type="text" id="studentTel" size="20" value="${sessionScope.USER_INFO.userPhone}" onfocus="if(this.value=='输入联系方式') this.value='';" onblur="if(this.value=='') this.value='输入联系方式';else chkTel(this.value)" value="输入联系方式"  /></td>
		                <td align="left" nowrap="nowrap"><span id="tel_span">请填写常用手机号</span></td>
		               </tr>
		               <tr>
		                 <td align="right"  style="width: 40%;height: 50px;">联系地址&nbsp;&nbsp;</td>
		                 <td width="190" align="left"><input type="text" id="studentAddr" value="${sessionScope.student.studentAddr}" size="20" onfocus="if(this.value=='输入详细地址') this.value='';" onblur="if(this.value=='') this.value='输入详细地址'; " value="输入详细地址" /></td>
		                 <td align="left" nowrap="nowrap"><span class="addTip">请填写您的详细地址</span></td>
		               </tr>
		               <tr>
		                 <td height="50" align="right"  style="width: 40%;height: 50px;">
		                   <input type="submit" value="报 名" class="tosignBtn" style="margin-right:5px;;width:80px;height:30px;color:#FFF;border:0;background-color:#DE3237;"></td>
		                 <td width="190" align="left">
		                   <input type="reset" value="重 置" style="width:80px;margin-left:5px;height:30px;color:#FFF;border:0;background-color:#DE3237;">
		                 </td>
		                 <td align="left">&nbsp;</td>
	               	  </tr>
	               	</table>
	               <!-- </form> -->
               </div>
         </fieldset>
    </div>
   </div>
   <div class="clear"></div><!--清除浮动-->
</div><!--content end-->
	<c:import url="../foot.jsp"></c:import>
	<!--footer end-->
	<script type="text/javascript" src="resources/js/banner1.js"></script>
	<script type="text/javascript" src="resources/js/jquery-3.2.0.min.js"></script>
	<script type="text/javascript" src="resources/js/signUp/signUp.js"></script>
</body>
</html>
