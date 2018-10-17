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
	<title>eroly教育-就业情况</title>
	<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon"/>
<style type="text/css">
body,div,dl,dd,dt,p,a,form,select,input,span,ul,ol,li{margin:0px; padding:0px; text-indent:0; 
font-weight:normal;font-size:14px;
font-family:'Microsoft YaHei',Verdana, Arial, Helvetica, sans-serif;color:#363636;
}
.nowPage,.totalPage{
	width: 18px;
    display: inline-block;
}
</style>
<link href="resources/css/style.css" type="text/css" rel="stylesheet" />
<link href="resources/css/single.css" type="text/css" rel="stylesheet" />

  </head>
  
  <body>
<c:import url="header.jsp"></c:import>
  <!--  <iframe style="width:100%;height:236px" scrolling="no" frameborder="0" src="student/header.jsp"></iframe>-->
<div class="content">
   
   	<div class="clear"></div>
   	<div class="infoDiv">
   
   	<div class="buyHeader">
   		<h1>就业情况</h1>
   		<div class="innerDiv" onclick="history.back()">返 回</div>
   	</div>
   	<div class="buyBody">
    <div>
    	<table class="buyTable"  border="1" style="margin: 30 auto;border: 1px solid #b5d6e6;" cellpadding="0" cellspacing="0" bgcolor="b5d6e6" onmouseover="changeto()" onmouseout="changeback()"> 
            <tr>
            	<th height="50" bgcolor="#FFFFFF">学员姓名</th>
                <th height="50" bgcolor="#FFFFFF">工作地</th>
                <th height="50" bgcolor="#FFFFFF">职位</th>
                <th height="50" bgcolor="#FFFFFF">毕业时间</th>
                <th height="50" bgcolor="#FFFFFF">入职时间</th>
                <th height="50" bgcolor="#FFFFFF">薪资</th>
            </tr>
            <%-- <c:forEach items="${requestScope.pageVO.list }" var="empinfo">
            <tr>
            	<td width="120" height="20" align="center" nowrap="nowrap" bgcolor="#FFFFFF">${empinfo.employeeInformationStudent.studentRealName}</td>
                <td width="370" height="20" align="center" nowrap="nowrap" bgcolor="#FFFFFF">${empinfo.employeeInformationUnit}</td>
                <td width="160" height="20" align="center" nowrap="nowrap" bgcolor="#FFFFFF">${empinfo.employeeInformationPosition}</td>
                <td width="100" height="20" align="center" nowrap="nowrap" bgcolor="#FFFFFF"><p>￥${empinfo.employeeInformationSalary}</p></td>
            </tr>
            </c:forEach> --%>
            <tbody id="graInfo"></tbody>
        </table>
    </div>

   	<div class="graInfoPage clearfix">
		<span>共<label class="countSize"></label>条数据</span>
		<span>第<label class="nowPage"></label>/<label class="totalPage"></label>页</span>
		<a class="first" data-page="1">首页</a>
		<a class="pre" data-page="0">上一页</a>
		<a class="next" data-page="0">下一页</a>
		<a class="end" data-page="0">末页</a>
	</div>
    </div>
   </div>
   <div class="clear"></div><!--清除浮动-->
</div><!--content end-->
<c:import url="foot.jsp"></c:import>
<script type="text/javascript" src="resources/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="resources/js/empinfo.js"></script>
<script type="text/javascript" src="resources/js/banner1.js"></script>
<script type="text/javascript">
		function goPage(page){
			location.href="${pageContext.request.contextPath }/student/empinfoFindAll.servlet?page="+page;
		}
	</script>
</body>
</html>
