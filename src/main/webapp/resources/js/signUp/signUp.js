$(function(){
	initFun();
	Me.init();
});

var Me={
	init:function(){
		Me.initEvent();
	},
	initEvent:function(){
		$('.nextBtn').click(Me.nextBtn);
		$('.foot').click(function(){
			$('.zzc').hide();
//			$('body').css('overflow','auto');// 浮层关闭时滚动设置
		});
		$('.tosignBtn').click(function(){
			var flag = check();
			if(flag){
				Me.signUp();
			}
		});
	},
	nextBtn:function(){
		$('.nextBtn').css('background-color', '#006400');
		$('.stepOne').addClass('hidden');
		$('.stepTwo').removeClass('hidden');
	},
	signUp:function(){
		$.ajax({
			type:"POST",
			url:"signUp/signUp",
			data:{
				courseId:$('#courseId').val(),
				studentRealName:$('#studentRealName').val(),
				studentAddr:$('#studentTel').val()
			},
			dataType:"json",
			success:function(data){
				if(data.STATUS=="1"){
					//报名成功
					var courseInfo = data.currentCourse;
					window.location.href="public/goSignSuccess?course="+courseInfo;
//					alert(data.MSG);
				}else{
					$('.zzc').show();
					$('.spanTip').html(data.MSG);
//					$('body').css('overflow','hidden');//浮层出现时窗口不能滚动设置
				}
			},
			error:function(errData){
				console.log(errData);
				alert("抱歉，"+errData);
			}
		});
	}
}
function initFun(){
	//课程联动学费
	function change01(){
		var subjectId=0;
		var option_arr=document.getElementsByName("option_sub");
		for(var j=0;j<option_arr.length;j++){
			if(option_arr[j].selected){
				document.getElementById("subjectCost").innerHTML="￥"+option_arr[j].id;
			}
		}
	}
	function chkEmail(strEmail) {
		var reg_str=/^([a-zA-Z0-9_\.-]+)@([\da-zA-Z\.-]+)\.([a-zA-Z]{2,6})$/;
		var email_span=document.getElementById("email");
		if(strEmail!=""){
			if (!reg_str.test(strEmail)) { 
				email_span.innerHTML="请输入有效的邮箱！";
				email_span.style.color="red";
			} else { 
				email_span.innerHTML="邮箱格式正确！";
				email_span.style.color="green";
			} 
		}
	} 
	var tmp = /^1[3-9]\d{9}$/;    //支持11位手机号码验证  
	var p1 = /^(([0+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/; //电话号码  
	function chkTel(strTel){
		var tel_span=document.getElementById("tel_span");
		var phone = document.baoming.studentTel.value;
		if( phone =="" || phone =="输入联系方式"  || ( !tmp.test(phone) && !p1.test(phone)) ){
  			tel_span.innerHTML="请输入有效的电话号码!"; 
  			tel_span.style.color="red";
 		    document.baoming.studentTel.select();  
		}
	}
}
function check(){
	var name_val=$('#studentRealName').val();
	if(name_val==""){
		var name_span=document.getElementById("name_span");
		name_span.innerHTML="请输入您的真实姓名！";
		name_span.style.color="red";
		return false;
	}
	var reg_str=/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
	var email_span=document.getElementById("email");
	var strEmail=document.getElementById("email_inp").value;
	if(strEmail!=""){
		if (!reg_str.test(strEmail)) { 
			email_span.innerHTML="请输入有效的邮箱！";
			email_span.style.color="red";
			return false;
		} /*else { 
			email_span.innerHTML="邮箱格式正确！";
			email_span.style.color="green";
		} */
	}else{
		email_span.innerHTML="邮箱为必填项！";
		email_span.style.color="red";
		return false;
	}
	var tmp = /^1[3-9]\d{9}$/;    //支持11位手机号码验证  
	var p1 = /^(([0+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/; //电话号码  
	var tel_span=document.getElementById("tel_span");
	var phone = $('#studentTel').val();
	if( phone =="" || phone =="输入联系方式"  || ( !tmp.test(phone) && !p1.test(phone)) ){
		tel_span.innerHTML="请输入有效的电话号码!"; 
		tel_span.style.color="red";
		document.baoming.studentTel.select();  
		return false;   
	}
	var address = $('#studentAddr').val();
	if(address==''){
		$('.addTip').html('请输入您的地址');
		$('.addTip').css('color','red');
		return false;
	}
	Me.signUp();
	return true;
}


