// JavaScript Document

var fader = new Hongru.fader.init('fader',{
 id:'fader'
});
/**
 * 检查参数是否包含特殊字符
 * @param str
 * @returns 包含则返回0，不包含返回1
 */
function checkParam(str){
	var fibdn = new Array ("'" ,"’","‘","”","“","\\","/","-");
	var ii=fibdn.length;
	var jj=str.length;
	for (var i=0; i<ii; i++){
		var temp2=fibdn[i];
		for (var j=0; j<jj; j++){
			var temp1=str.charAt(j);
			if (temp1==temp2){
				return 0;
			}
		}
	}
	return 1;
}
            