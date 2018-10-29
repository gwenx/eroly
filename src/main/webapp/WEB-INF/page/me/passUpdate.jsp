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
	<title>eroly教育-修改密码</title>
	<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon"/>
	<link href="resources/css/style.css" type="text/css" rel="stylesheet" />
	<link href="resources/css/single.css" type="text/css" rel="stylesheet" />

  </head>
  
  <body>
<c:import url="../header.jsp" charEncoding="UTF-8"></c:import>

<div class="content">
   
   <div class="clear"></div>
   <div class="buyDiv">
   
   	<div class="buyHeader">
   		<h1>密码修改</h1>
   		<div class="innerDiv" onclick="history.back();">返 回</div>
        <div class="innerDiv" onclick="alert('修改成功！')">修 改</div>
   	</div>
   
   
   	<div class="msgUpdateBody">
    	<fieldset class="passField">
        	<legend style="color:#DE3237;font-size:18px;'" align="left">修改密码</legend>
               <form>
               <table class="passUptadeTable">
               <tr>
               	<td height="35" align="right" nowrap="nowrap" style="width:30%;">原密码:</td>
                <td height="35"></td>
                <td height="35" align="left"><input type="password" /></td>
               </tr>
               <tr>
               	<td height="35" align="right" nowrap="nowrap">新密码:</td>
                <td height="35"></td>
                <td height="35" align="left"><input type="password" /></td>
               </tr>
               <tr>
               	<td height="35" align="right" nowrap="nowrap">确认密码:</td>
                <td height="35">&nbsp;</td>
                <td height="35" align="left"><input type="password" /></td>
               </tr>
               </table>
               </form>
         </fieldset>
    </div>
   </div>
   <div class="clear"></div><!--清除浮动-->
</div><!--content end-->
<c:import url="../foot.jsp" charEncoding="UTF-8"></c:import>
	<script type="text/javascript" src="resources/js/banner1.js"></script>
</body>
</html>