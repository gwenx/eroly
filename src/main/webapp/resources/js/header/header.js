$('#subjectId').on('keypress',function(event){ 
     if(event.keyCode == 13){  
    	 checkname();  
     }  
});
function checkname() {
	var subjectName = $('#subjectId').val();
	if (subjectName == "") {
		alert("请输入课程名！");
		return false;
	}else{
		return true;
		/*searchCourse();*/
	}
}
function logout(){
	var r= confirm("您确定要退出吗？");
	if (r==true){
		return true;
    }else{
    	return false;
    }
}
function searchCourse(){
	window.location.href="course/findCourseByKeyWords?courseName="+$('#subjectId').val();
	/*$.ajax({
		type:"POST",
		url:"course/findCourseByKeyWords",
		data:{
			courseName:$('#subjectId').val()
		},
		dataType:"json",
		success:function(data){
			if(data.STATUS=="1"){
				alert(data);
			}else{
				alert(data.MSG);
			}
		},
		error:function(errData){
			alert("抱歉，"+errData.MSG);
		}
	});*/
}