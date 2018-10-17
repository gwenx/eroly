/*$(function(){
	code();
	$("#login").click(function(){
		$.ajax({
				type:"POST",
				url:"user/userLogin",
				data:{
					userInfo:$("#studentLoginName").val(),
					userPass:$("#studentPass").val()
				},
				dataType:"json",
				success:function(data){
					if(data.STATUS=="1"){
						alert(data.MSG);
					}else{
						var input = data.userInput;
						if(data.error!=null&&data.error=="1"){
							$("#studentPass").val(input);
							$("#studentLoginName").val('');
						}else{
							$("#studentPass").val('');
							$("#studentLoginName").val(input);
						}
						$("#tip_span").html(data.MSG);
						setTimeout(function() {
							$("#tip_span").hide('fast');	
							$("#tip_span").html("");
							$("#tip_span").show('fast');	
						}, 2000);
//						alert("提示："+data.MSG);
					}
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					$("#tip_span").html("there is something wrong!");
					alert(XMLHttpRequest.status);
				}
		});
	});
});*/

var reg_loginname = /^[a-zA-Z0-9_-]{6,12}$/;
	var reg_pass = /^[a-zA-Z0-9_-]{6,18}$/;
	$(function() {
		code();
		$("#studentLoginName").blur(function() {
			var username = $("#studentLoginName").val();
			if (username != ""&&username.length<2) {
					$("#loginName_span").html("&nbsp;&nbsp;输入姓名长度不符合要求");
			}
		});
	});
	function code() {
		var array = [ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
				'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
				'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7',
				'8', '9', '0' ];
		var str = "";
		for (var i = 0; i < 4; i++) {
			var index = Math.floor(Math.random() * 62);
			str += array[index];
		}
		document.getElementById("tsyzm").innerHTML = str;
	}

	function checkcode() {
		var codecc = (document.getElementById("tsyzm").innerHTML).toLowerCase();
		var code = (document.getElementById("yzm").value).toLowerCase();
		if (code != "") {
			if (codecc == code) {
				document.getElementById("ss").innerHTML = "验证码输入正确";
				document.getElementById("ss").style.color = "green";
			} else {
				document.getElementById("ss").innerHTML = "验证码输入错误";
				document.getElementById("ss").style.color = "red";
			}
		}
	}
	//点击登录验证
	function check() {
		if ($("#studentLoginName").val() == "") {
			/*if (!reg_loginname.test($("#studentLoginName").val())) {
				$("#loginName_span").html("用户名为6-12位数字字母下划线");
				$("#loginName_span").removeClass("okTip");
				$("#loginName_span").addClass("errorTip");
				return false;
			}
		} else {*/
			$("#loginName_span").html("请输入用户名!");
			$("#loginName_span").removeClass("okTip");
			$("#loginName_span").addClass("errorTip");
			return false;
		}

		if ($("#studentPass").val() == "") {
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
			}
		}
		var codecc = (document.getElementById("tsyzm").innerHTML).toLowerCase();
		var code = (document.getElementById("yzm").value).toLowerCase();
		if (code != "") {
			if (codecc != code) {
				document.getElementById("ss").innerHTML = "验证码输入错误"
				document.getElementById("ss").style.color = "red";
				return false;
			}
		} else {
			document.getElementById("ss").innerHTML = "请输入验证码！"
			document.getElementById("ss").style.color = "red";
			return false;
		}
		return true;
	}