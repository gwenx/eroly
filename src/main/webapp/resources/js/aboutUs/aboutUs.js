var Me={
		getContent:function (){
			$.ajax({
				type:"POST",
				url:"info/findInfo",
				data:{
					"infoType":"1"
				},
				dataType:"json",
				success:function(data){
					if(data.STATUS=="1"){
						var info = data.infoList;
						$("#aboutUs").html(info[0].infoContrnt);
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

$(function(){
	Me.getContent();
});
