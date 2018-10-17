$(function(){
	/*function goPage(page){
		location="${pageContext.request.contextPath }/student/subjectfindall.servlet?page="+page;
	}*/
	Eroly.getCyzc();
	Eroly.getDtxw();
	Eroly.getGgxx();
	Eroly.getHyxxjj();
	Eroly.getGk();
	Eroly.getBqsm();
	Eroly.getYxxy();
	Eroly.getTskc();
});
var Eroly={
		//获取创业资讯
		getYxxy:function (){
			$.ajax({
				type:"POST",
				url:"graduation/findTopSix",
				data:{
				},
				dataType:"json",
				success:function(data){
					if(data.STATUS=="1"){
						var info = data.topSixGra;
						var html="";
						for(var i=0;i<info.length;i++){
							html += "<li><img src='resources/images/dian.png' " +
									"width='5px' height='5px' style='margin-top:9px;'alt=''" +
									" />&nbsp;&nbsp;<a href='public/goGra'>"
									+info[i].userName+"&nbsp;&nbsp;"+info[i].graJob+"&nbsp;&nbsp;月薪:"+info[i].graSalary+"元</a></li>";
						}
						$(".yxxy").html(html);
					}else{
						alert(data.MSG);
					}
				},
				error:function(errData){
					alert("抱歉，"+errData.MSG);
				}
			});
		},
		//获取创业资讯
		getCyzc:function (){
			$.ajax({
				type:"POST",
				url:"info/findInfo",
				data:{
					"infoType":"3"
				},
				dataType:"json",
				success:function(data){
					if(data.STATUS=="1"){
						var info = data.infoList;
						var html="";
						for(var i=0;i<info.length;i++){
							html += "<li><img src='resources/images/dian.png' width='5px' height='5px' style='margin-top:9px;'alt='' />&nbsp;&nbsp;<a href='#'>"+info[i].infoContrnt+"</a></li>";
						}
						$(".cyzx").html(html);
					}else{
						alert(data.MSG);
					}
				},
				error:function(errData){
					alert("抱歉，"+errData.MSG);
				}
			});
		},
		//获取动态新闻
	getDtxw:function (){
		$.ajax({
			type:"POST",
			url:"info/findInfo",
			data:{
				"infoType":"4"
			},
			dataType:"json",
			success:function(data){
				if(data.STATUS=="1"){
					var info = data.infoList;
					var html="";
					for(var i=0;i<info.length;i++){
						html += "<li><img src='resources/images/dian.png' width='5px' height='5px' style='margin-top:9px;'alt='' />&nbsp;&nbsp;<a href='#'>"+info[i].infoContrnt+"</a></li>";
					}
					$(".dtxw").html(html);
				}else{
					alert(data.MSG);
				}
			},
			error:function(errData){
				alert("抱歉，"+errData.MSG);
			}
		});
	},
		//获取公告信息
		getGgxx:function (){
			$.ajax({
				type:"POST",
				url:"info/findInfo",
				data:{
					"infoType":"5"
				},
				dataType:"json",
				success:function(data){
					if(data.STATUS=="1"){
						var info = data.infoList;
						var html="";
						for(var i=0;i<info.length;i++){
							html += "<li><img src='resources/images/dian.png' width='5px' height='5px' style='margin-top:9px;'alt='' />&nbsp;&nbsp;<a href='#'>"+info[i].infoContrnt+"</a></li>";
						}
						$(".ggxx").html(html);
					}else{
						alert(data.MSG);
					}
				},
				error:function(errData){
					alert("抱歉，"+errData.MSG);
				}
			});
		},
		//获取行业信息简介
		getHyxxjj:function (){
			$.ajax({
				type:"POST",
				url:"info/findInfo",
				data:{
					"infoType":"6"
				},
				dataType:"json",
				success:function(data){
					if(data.STATUS=="1"){
						var info = data.infoList;
						var html="";
						for(var i=0;i<info.length;i++){
							html += "<li><img src='resources/images/dian.png' width='5px' height='5px' style='margin-top:9px;'alt='' />&nbsp;&nbsp;<a href='#'>"+info[i].infoContrnt+"</a></li>";
						}
						$(".hyxxjj").html(html);
					}else{
						alert(data.MSG);
					}
				},
				error:function(errData){
					alert("抱歉，"+errData.MSG);
				}
			});
		},
		//获取概况
		getGk:function (){
			$.ajax({
				type:"POST",
				url:"info/findInfo",
				data:{
					"infoType":"2"
				},
				dataType:"json",
				success:function(data){
					if(data.STATUS=="1"){
						var info = data.infoList;
						var html="";
						for(var i=0;i<info.length;i++){
							html += "<li>"+info[i].infoContrnt+"</li>";
						}
						$(".gk").html(html);
					}else{
						alert(data.MSG);
					}
				},
				error:function(errData){
					alert("抱歉，"+errData.MSG);
				}
			});
		},
		//获取版权声明
		getBqsm:function (){
			$.ajax({
				type:"POST",
				url:"info/findInfo",
				data:{
					"infoType":"7"
				},
				dataType:"json",
				success:function(data){
					if(data.STATUS=="1"){
						var info = data.infoList;
						var html="";
						for(var i=0;i<info.length;i++){
							html += "<li>"+info[i].infoContrnt+"</li>";
						}
						$(".bqsm").html(html);
					}else{
						alert(data.MSG);
					}
				},
				error:function(errData){
					alert("抱歉，"+errData.MSG);
				}
			});
		},
		//获取特色课程
		getTskc:function (){
			$.ajax({
				type:"POST",
				url:"course/findCourse",
				data:{
				},
				dataType:"json",
				success:function(data){
					if(data.STATUS=="1"){
						var course = data.courseList;
						var html="";
						for(var i=0;i<course.length;i++){
							html += "<li><img src='resources/images/dian.png' width='5px'" +
									" height='5px' style='margin-top:9px;'alt='' />&nbsp;&nbsp;<a href='course/findCourseById?courseId="+course[i].courseId+"'>"
							+course[i].courseName+"</a></li>";
						}
						$(".tskc").html(html);
					}else{
						alert(data.MSG);
					}
				},
				error:function(errData){
					alert("抱歉，"+errData.MSG);
				}
			});
		}
}

