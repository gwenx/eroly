var reg_loginname = /^[a-zA-Z0-9_-]{6,12}$/;
var reg_pass = /^[a-zA-Z0-9_-]{6,18}$/;
$(function() {
	
	var countdown=60;
	function settime() { 
		var phoneNum = $("#phoneNum").val();
		if(phoneNum=="" || phoneNum==null || phoneNum.length!=11){
			alert('请输入正确的手机号');
			return;
		}else{
			if (countdown == 0) { 
				$("#getSMsCode").attr("disabled",false);    
				$("#getSMsCode").html("免费获取"); 
				countdown=60;
				return;
			} else { 
				$("#getSMsCode").attr("disabled",true);    
				$("#getSMsCode").html("重新发送(" + countdown + ")"); 
				countdown--; 
			} 
			setTimeout(function() { 
				settime() 
			},1000) 
		}
	}
	
	$("#loginName").val("");
	$("#password").val("");
	$("#repassword").val("");
	$('#getSMsCode').click(function(){
		var phoneNum = $("#phoneNum").val();
		if(phoneNum=="" || phoneNum==null || phoneNum.length!=11){
			alert('请输入正确的手机号');
			return;
		}
		settime();
		$.ajax({
			type : "POST",
			url : "messsage/getSMSCode",
			data : {
				"phoneNum" : phoneNum,
			},
			dateType : "text",
			success : function(result) {
				if (result.STATUS == "1") {
					$("#loginName_span").html("用户名可用~");
					$("#loginName_span").addClass("okTip");
				} else {
					$("#loginName_span").html(result.MSG);
					$("#loginName_span").removeClass("okTip");
					$("#loginName_span").addClass("errorTip");
				}
			}
		});
	});
	// 验证二次密码
	$("#repassword").blur(function() {
		if ($("#repassword").val() != "" && $("#password").val() != "") {
			if ($("#password").val() != $("#repassword").val()) {
				$("#repass_span").html("两次密码不一致！");
				$("#repass_span").removeClass("okTip");
				$("#repass_span").addClass("errorTip");
			} else {
				$("#repass_span").html("两次密码一致！");
				$("#repass_span").removeClass("errorTip");
				$("#repass_span").addClass("okTip");
			}
		}
	});
	// 验证密码
	$("#password").blur(function() {
		if ($("#password").val() != "") {
			if (!reg_pass.test($("#password").val())) {
				$("#pass_span").html("密码格式为6-18位数字字母下划线");
				$("#pass_span").removeClass("okTip");
				$("#pass_span").addClass("errorTip");
			} else {
				$("#pass_span").html("密码格式正确");
				$("#pass_span").removeClass("errorTip");
				$("#pass_span").addClass("okTip");
			}
		}
	});
	// 验证用户名
	$("#loginName").blur(function() {
		if ($("#loginName").val() != "") {
			if (!reg_loginname.test($("#loginName").val())) {
				$("#loginName_span").html("用户名格式为6-12位数字字母下划线");
				$("#loginName_span").removeClass("okTip");
				$("#loginName_span").addClass("errorTip");
			} else {
				$.ajax({
					type : "POST",
					url : "regist/judgeRegist",
					data : {
						"userName" : $("#loginName").val(),
					},
					dateType : "text",
					success : function(result) {
						if (result.STATUS == "1") {
							$("#loginName_span").html("用户名可用~");
							$("#loginName_span").addClass("okTip");
						} else {
							$("#loginName_span").html(result.MSG);
							$("#loginName_span").removeClass("okTip");
							$("#loginName_span").addClass("errorTip");
						}
					}
				});
			}
		}
	});
});
function check() {
	if ($("#loginName").val() == "") {
		$("#loginName_span").html("请输入用户名！");
		$("#loginName_span").removeClass("okTip");
		$("#loginName_span").addClass("errorTip");
		return false;
	} else {
		if (!reg_loginname.test($("#loginName").val())) {
			$("#loginName_span").html("用户名格式为6-12位数字字母下划线");
			$("#loginName_span").removeClass("okTip");
			$("#loginName_span").addClass("errorTip");
			return false;
		} else {
			$.ajax({
				type : "POST",
				url : "regist/judgeRegist",
				data : {
					"userName" : $("#loginName").val(),
				},
				dateType : "text",
				success : function(msg) {
					if (msg == "true") {
						$("#loginName_span").html("用户名已存在!");
						$("#loginName_span").removeClass("okTip");
						$("#loginName_span").addClass("errorTip");
						return false;
					} else {
						$("#loginName_span").html("用户名可用~");
						$("#loginName_span").addClass("okTip");
					}
				}
			});
		}
	}
}
function check2() {
	if ($("#loginName").val() == "") {
		$("#loginName_span").html("请输入用户名！");
		$("#loginName_span").removeClass("okTip");
		$("#loginName_span").addClass("errorTip");
		return false;
	} else {
		if (!reg_loginname.test($("#loginName").val())) {
			$("#loginName_span").html("用户名格式为6-12位数字字母下划线");
			$("#loginName_span").removeClass("okTip");
			$("#loginName_span").addClass("errorTip");
			return false;
		} else {
			$.ajax({
				type : "POST",
				url : "regist/judgeRegist",
				data : {
					"userName" : $("#loginName").val(),
				},
				dateType : "text",
				success : function(msg) {
					if (msg == "true") {
						$("#loginName_span").html("用户名已存在!");
						$("#loginName_span").removeClass("okTip");
						$("#loginName_span").addClass("errorTip");
						return false;
					} else {
						$("#loginName_span").html("用户名可用~");
						$("#loginName_span").addClass("okTip");
					}
				}
			});
		}
	}
	if ($("#password").val() == "") {
		$("#pass_span").html("请输入密码！");
		$("#pass_span").removeClass("okTip");
		$("#pass_span").addClass("errorTip");
		return false;
	} else {
		if (!reg_pass.test($("#password").val())) {
			$("#pass_span").html("密码格式为6-18位数字字母下划线");
			$("#pass_span").removeClass("okTip");
			$("#pass_span").addClass("errorTip");
			return false;
		} else {
			$("#pass_span").html("密码格式正确");
			$("#pass_span").removeClass("errorTip");
			$("#pass_span").addClass("okTip");
		}
	}
	if ($("#repassword").val() == "") {
		$("#repass_span").html("请再次输入密码！");
		$("#repass_span").removeClass("okTip");
		$("#repass_span").addClass("errorTip");
		return false;
	} else {
		if ($("#password").val() != $("#repassword").val()) {
			$("#repass_span").html("两次密码不一致！");
			$("#repass_span").removeClass("okTip");
			$("#repass_span").addClass("errorTip");
			return false;
		} else {
			$("#repass_span").html("两次密码一致！");
			$("#repass_span").removeClass("errorTip");
			$("#repass_span").addClass("okTip");
		}
	}
	return true;
}