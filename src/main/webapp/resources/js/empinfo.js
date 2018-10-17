var startNum=0,pageSize=20;
var Me={
		init:function(){
			
			//首页
			$('.first').click(function(){
				startNum=$('.first').attr('data-page');
				var nowPage = $('.nowPage').html();//当前页
				if(startNum!="0"&&nowPage!='1'){
					Me.getGraInfo();
				}else{
					$('.first').attr('disabled', true);
				}
			});
			//上一页
			$('.pre').click(function(){
				startNum=$('.pre').attr('data-page');
				var nowPage = $('.nowPage').html();//当前页
				if(startNum!='0'&&nowPage!='1'){
					$('.next').attr('data-page',startNum+1);
					Me.getGraInfo();
				}else{
					$('.pre').attr('disabled', true);
				}
			});
				
			//下一页
			$('.next').click(function(){
				var nowPage = $('.nowPage').html();//当前页
				var totalPage = $('.totalPage').html();//总页数
				startNum=$('.next').attr('data-page');
				if(nowPage!=totalPage){
					Me.getGraInfo();
				}else{
					$('.next').attr('disabled', true);
				}
			});
			//尾页
			
			$('.end').click(function(){
				startNum=$('.end').attr('data-page');
				var nowPage = $('.nowPage').html();//当前页
				var totalPage = $('.totalPage').html();//总页数
				if(nowPage!=totalPage){
					Me.getGraInfo();
				}else{
					$('.end').attr('disabled', true);
				}
			});
		},
		getGraInfo:function (){
			$.ajax({
				type:"POST",
				url:"graduation/findGraPage",
				data:{
					startNum:startNum,
					pageSize:pageSize
				},
				dataType:"json",
				success:function(data){
					if(data.STATUS=="1"){
						var list = data.list;
						var html="";
						for(var i = 0;i<list.length;i++){
							var stu = list[i];
							html+="<tr>"+
							"<td width='120' height='20' align='center' nowrap='nowrap' bgcolor='#FFFFFF'>"+stu.userName+"</td>"
							+"<td width='100' height='20' align='center' nowrap='nowrap' bgcolor='#FFFFFF'>"+stu.graPlace+"</td>"
							+"<td width='160' height='20' align='center' nowrap='nowrap' bgcolor='#FFFFFF'>"+stu.graJob+"</td>"
							+"<td width='160' height='20' align='center' nowrap='nowrap' bgcolor='#FFFFFF'>"+stu.graDatetime+"</td>"
							+"<td width='160' height='20' align='center' nowrap='nowrap' bgcolor='#FFFFFF'>"+stu.graJobtime+"</td>"
							+"<td width='100' height='20' align='center' nowrap='nowrap' bgcolor='#FFFFFF'><p>￥"+stu.graSalary+"</p></td>"
							+"</tr>"
						}
						$('#graInfo').html(html);
						$('.countSize').html(data.total);
						$('.nowPage').html(data.pageNum);
						$('.totalPage').html(data.pages);
						if(data.pageNum!='1'){
							//上一页
							$('.pre').attr('data-page',data.prePage);
						}
						if(data.pageNum!=data.pages){
							//下一页
							$('.next').attr('data-page',data.nextPage);
							//尾页
							$('.end').attr('data-page',data.pages);
						}
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

$(function(){
	Me.getGraInfo();
	Me.init();
});
