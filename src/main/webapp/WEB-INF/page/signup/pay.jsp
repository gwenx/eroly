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
	<title>eroly教育-支付页面</title>
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
   		<h1>课程结算</h1>
   		<div class="innerDiv" onclick="history.back();">返 回</div>
   	</div>
   	<div class="loginBody">
    	<fieldset class="regField">
        	<legend style="color:#DE3237;font-size:18px;" align="left">  结 算 </legend>
        	<form name="baoming">
              <table class="registTable">
               <tr>
               	<td align="right" nowrap="nowrap" style="width:30%;">报 名 人：</td>
                <td><img src=""/></td>
                <td align="left" nowrap="nowrap">${sessionScope.student.studentRealName}</td>
               </tr>
               <tr>
               	<td align="right" nowrap="nowrap">已报课程：</td>
                <td></td>
                <td align="left" nowrap="nowrap">${sessionScope.student.studentSubject.subjectName}</td>
               </tr>
               <tr>
               	<td align="right" nowrap="nowrap">课程定金：</td>
                <td>&nbsp;</td>
                <td align="left" nowrap="nowrap">￥${sessionScope.student.studentSubject.subjectDeposit}</td>
               </tr>
               <tr>
                 <td align="right" nowrap="nowrap">全额费用：</td>
                 <td>&nbsp;</td>
                 <td align="left" nowrap="nowrap">￥${sessionScope.student.studentSubject.subjectCost}</td>
               </tr>
               <tr>
                 <td align="right" nowrap="nowrap">&nbsp;</td>
                 <td>&nbsp;</td>
                 <td align="left" nowrap="nowrap">&nbsp;</td>
               </tr>
               <tr>
                 <td align="right" nowrap="nowrap"><div id="payDingjin" onclick="payDingjin()" style="width:80px;height:30px;background-color:#DE3237;cursor:pointer;text-align:center;color:#FFF;margin-right:2px;line-height:30px;">付定金</div></td>
                 <td>&nbsp;</td>
                 <td align="left" nowrap="nowrap"><div id="payQuankuan" onclick="payQuankuan()" style="width:80px;height:30px;background-color:#DE3237;cursor:pointer;text-align:center;color:#FFF;margin-right:2px;line-height:30px;">付全款</div></td>
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
<script type="text/javascript" src="resources/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#payDingjin").click(function(){
			$.ajax({
				type:"POST",
				url:"student/paydingjin.servlet",
				data:{
					"studentId":${sessionScope.student.studentId},
					"stustateId":3
				},
				dateType:"text",
				success:function(msg){
					if(msg=="true"){
						alert("定金交付成功！");
						location.href="student/signup/message.jsp"
					}else{
						alert("定金交付失败！")
					}
				}
			});
		});
		$("#payQuankuan").click(function(){
			$.ajax({
				type:"POST",
				url:"student/pay.servlet",
				data:{
					"studentId":${sessionScope.student.studentId},
					"stustateId":4
				},
				dateType:"text",
				success:function(msg){
					if(msg=="true"){
						alert("全款交付成功！");
						location.href="student/signup/message.jsp"
					}else{
						alert("全款交付失败！")
					}
				}
			});
		});
	});
	
</script>
</body>
</html>
