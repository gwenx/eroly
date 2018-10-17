$(function(){
	initFun();
	Me.init();
});

var Me={
	init:function(){
		Me.initEvent();
		Me.getContent();
	},
	initEvent:function(){
		$('.nextBtn').click(Me.nextBtn);
		$('#courseChose').change(Me.selectChange);
	},
	selectChange:function(){
		var total = $('#courseChose option:selected').attr('data-total');
		var discount = $('#courseChose option:selected').attr('data-discount');
		$('#subjectCost').html("￥"+total);
		$('#subjectDeposit').html("￥"+discount);
	},
	nextBtn:function(){
		$('.stepOne').addClass('hidden');
		$('.stepTwo').removeClass('hidden');
	},
	getContent:function (){
		$.ajax({
			type:"POST",
			url:"course/findCourse",
			data:{},
			dataType:"json",
			success:function(data){
				if(data.STATUS=="1"){
					var courseList = data.courseList;
					var html="";
					for(var i=0;i<courseList.length;i++){
						html+="<option value="+courseList[i].courseId+" data-total="+courseList[i].totalPrice+" data-discount="+courseList[i].priceDiscount+">"+courseList[i].courseName+"</option>";
					}
					$('#courseChose').html(html);
					$('#subjectCost').html("￥"+courseList[0].totalPrice);
					$('#subjectDeposit').html("￥"+courseList[0].priceDiscount);
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
	function check(){
		var name_val=document.baoming.studentRealName.value;
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
			} else { 
				email_span.innerHTML="邮箱格式正确！";
				email_span.style.color="green";
			} 
		}else{
			email_span.innerHTML="邮箱为必填项！";
			email_span.style.color="red";
			return false;
		}
		var tmp = /^1[3-9]\d{9}$/;    //支持11位手机号码验证  
		var p1 = /^(([0+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/; //电话号码  
		var tel_span=document.getElementById("tel_span");
		var phone = document.baoming.studentTel.value;
		if( phone =="" || phone =="输入联系方式"  || ( !tmp.test(phone) && !p1.test(phone)) ){
  			tel_span.innerHTML="请输入有效的电话号码!"; 
  			tel_span.style.color="red";
 		    document.baoming.studentTel.select();  
 			return false;   
		}
		return true;
	}
}


