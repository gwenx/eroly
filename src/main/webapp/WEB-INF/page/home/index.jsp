<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <meta charset="utf-8">
      <base href="<%=basePath%>">
      <meta http-equiv="pragma" content="no-cache">
      <meta http-equiv="cache-control" content="no-cache">
      <meta http-equiv="expires" content="0">
      <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
      <meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<title>eroly教育-首页</title>
	<link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon"/>
	<link href="resources/css/style.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" href="resources/css/swiper.min.css">
	<style type="text/css">
		/* body,div,dl,dd,dt,p,a,form,select,input,span,ul,ol,li{
			margin:0px;
			padding:0px;
			text-indent:0; 
			font-weight:normal;
			font-size:14px;
			font-family:'Microsoft YaHei',Verdana, Arial, Helvetica, sans-serif;
			color:#363636;
		} */
	   html, body {
	     position: relative;
	     height: 100%;
	   }
	   body {
	     background: #eee;
	     font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
	     font-size: 14px;
	     color:#000;
	     margin: 0;
	     padding: 0;
	   }
	   .swiper-container {
	     width: 100%;
	     /* height: 86%; */
	   }
	   .swiper-slide {
	     text-align: center;
	     font-size: 18px;
	     background: #fff;
	     height: 68%;
	
	     /* Center slide text vertically */
	     display: -webkit-box;
	     display: -ms-flexbox;
	     display: -webkit-flex;
	     display: flex;
	     -webkit-box-pack: center;
	     -ms-flex-pack: center;
	     -webkit-justify-content: center;
	     justify-content: center;
	     -webkit-box-align: center;
	     -ms-flex-align: center;
	     -webkit-align-items: center;
	     align-items: center;
	   }
	   .swiper-container>img{
	   	width: 80%;
	   	height: 80%;
	   }
	   .swiper-wrapper{
	   		height:auto;
	   		padding-bottom: 24px;
	   }
	</style>
  </head>
<body>
  <c:import url="../header.jsp"></c:import>
  <!-- Swiper -->
  <div class="swiper-container">
	    <div class="swiper-wrapper">
		      <div class="swiper-slide"><img onclick="alert('正在开发中...')" src="resources/images/imgs/lunbo48.jpg"/></div>
		      <div class="swiper-slide"><img onclick="alert('正在开发中...')" src="resources/images/imgs/lunbo62.jpg"/></div>
		      <div class="swiper-slide"><img onclick="alert('正在开发中...')" src="resources/images/imgs/lunbo98.jpg"/></div>
		      <!-- <div class="swiper-slide"><img src="resources/images/imgs/1524673581948.png"/></div>
		      <div class="swiper-slide"><img src="resources/images/imgs/1524673581948.png"/></div>
		      <div class="swiper-slide"><img src="resources/images/imgs/1524673581948.png"/></div> -->
	    </div>
	    <!-- Add Pagination -->
	    <div class="swiper-button-next"></div>
	    <div class="swiper-button-prev"></div>
	    <div class="swiper-pagination" style="bottom: 0px"></div>
  </div>
  
<div class="content">
   <div class="clear"></div>
   <div class="content_left">
      <div class="left_top">
         <div class="left_top_zuo">
            <div class="hyaq">
               <h1>特色课程</h1>
              <div class="list" style="overflow:hidden;">
                  <ul class="tskc">
                  <c:forEach items="${applicationScope.subjectList }" var="subject">
                      <li><img src="resources/images/dian.png" width="5px" height="5px" style="margin-top:9px;" alt="" />&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/student/subjectInformationFindBySubjectId.servlet?subjectId=${subject.subjectId}" target="_top">${subject.subjectName}</a></li>
                 </c:forEach>
                  </ul>
              </div><!--list end-->
            </div><!--hyaq end--> 
            <div class="hyaq">
              <h1>创业资讯</h1>
               <div class="list">
                  <ul class="cyzx">
                  </ul>
               </div><!--list end-->
            </div>
            
            <div class="hyaq">
              <h1>公告信息</h1>
               <div class="list">
                  <ul class="ggxx">
                  </ul>
               </div><!--list end-->
            </div>           
        </div><!--top_left_zuo end-->
         <div class="left_top_you">
            
            
            <div class="dfdt">
               <h1>优秀学员</h1>
               <div class="list">
                  <ul class="yxxy">
                  <%-- <c:forEach items="${applicationScope.empInfoList }" var="empInfo">
                      <li><img src="resources/images/dian.png" width="5px" height="5px" style="margin-top:9px;" alt="" /><a href="${pageContext.request.contextPath }/student/empinfoFindAll.servlet" target="_top">${empInfo.employeeInformationStudent.studentRealName}</a></li>
                  </c:forEach> --%>
                  </ul>
               </div><!--list end-->
            </div><!--dfdt end-->
            
            <div class="dfdt">
               <h1>动态新闻</h1>
               <div class="list">
                  <ul class="dtxw">
                  </ul>
               </div><!--list end-->
            </div>
            <div class="dfdt">
               <h1>行业信息简介</h1>
               <div class="list">
                  <ul class="hyxxjj">
                  </ul>
               </div><!--list end-->
            </div>
        </div><!--top_left_you end-->
      </div><!--left_top end-->
      <div class="clear"></div><!--清除浮动-->
      <div class="left_under">
         
      </div><!--left_under end-->
   </div><!--content_left end-->
   <div class="content_right">
      <div class="qyfc">
            <h1>eroly概况</h1>
               <div class="list">
                  <ul style="color:#666;" class="gk">
                      
                  </ul>
               </div><!--list end-->
     </div>
     <div class="qyfc">
            <h1>版权声明</h1>
               <div class="list">
                  <ul style="color:#666;" class="bqsm">
                  </ul>
               </div><!--list end-->
     </div><!--qyfc end-->
  </div><!--content_right end-->
   <div class="clear"></div><!--清除浮动-->
</div><!--content end-->
  <c:import url="../foot.jsp"></c:import>
  	<script src="resources/js/swiper.min.js"></script>
	<script type="text/javascript" src="resources/js/banner1.js"></script>
	<script type="text/javascript" src="resources/js/jquery-3.2.0.min.js"></script>
	<script type="text/javascript" src="resources/js/home/index.js"></script>
	<script>
    var swiper = new Swiper('.swiper-container', {
      navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev'
      },
       pagination: {
        el: '.swiper-pagination',
      },
       
      autoplay:true 
    });
  </script>
</body>
</html>