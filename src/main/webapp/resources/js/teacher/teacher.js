var Eroly={
		getContent:function (){
			$.ajax({
				type:"POST",
				url:"teacher/findAll",
				data:{},
				dataType:"json",
				success:function(data){
					if(data.STATUS=="1"){
						var teacherList = data.teacherList;
						var html="";
						for (var i = 0; i < teacherList.length; i++) {
							var teacher = teacherList[i];
							html += "<ul class='about05-ul-tab-box' style='margin-top:10px;'>"+
										"<img src=resources/img/"+teacher.teacherImg+" />"+
										"<li class='li01'><font size='+1'>"+teacher.teacherName+"</font><br />"+teacher.teacherEmail+"</li>"+
										"<li class='li02'><p class='p01'><img src='resources/img/about-jt02.png' class='jt02' /></p>"+
										"<p class='p02'>"+teacher.teacherInfo+"</p></li></ul>";
						} 
						$(".about05-tab-box-div").html(html);
					}else{
						alert(data.MSG);
					}
				},
				error:function(errData){
					alert("抱歉，"+errData.MSG);
				}
			});
		},
}

var flag = 0;
function showNode(){
	if(flag==0){
		$("#hhh").hide("slow");
		$("#moreA").html("更多");
		flag=1;
	}else{
		$("#hhh").show("slow");
		$("#moreA").html("收起");
		flag=0;
	}
}
$(function(){
	$(".big").click(function(){
		var node = $(this).find("div[class=node]");
		if(node.attr("isshow")!=1){
			node.attr("isshow",1);
			node.show("slow");
		}else{
			node.attr("isshow",0);
			node.hide("normal");
		}
	});
	Eroly.getContent();
});
















