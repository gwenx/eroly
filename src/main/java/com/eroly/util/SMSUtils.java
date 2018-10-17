package com.eroly.util;
import org.apache.log4j.Logger;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
/**
 * 发送短信工具类
 * @author yapeng
 * @version 2018-02-09
 */
public class SMSUtils {
	private static Logger logger = Logger.getLogger(SMSUtils.class);
	public static final String appKey="24799573";//阿里提供的appkey,eroly
	public static final String appSecret="a3675d0508f1d9335557b2e02e8ac58d";//eroly密钥
	public static final String url="http://gw.api.taobao.com/router/rest";//短信发送的地址，阿里提供的
	public static final String smsTemplateCode="SMS_125770021";//短信模板ID
	public static final String smsFreeSignName="eroly教育";
	/**
	 * 发送短信验证码
	 * @param phoneNum
	 * @param code
	 * @return
	 */
	public static boolean sendCode(String phoneNum,String code){
		logger.info("--发送短信验证码--手机号{}:"+phoneNum);
		try {
			if(StringUtil.isNotEmpty(phoneNum)&&StringUtil.isNotEmpty(code)) {
				TaobaoClient client = new DefaultTaobaoClient(url, appKey, appSecret);
				AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
				//设置请求参数
				req.setExtend("123456");//公共回传参数,会在返回消息中透传会该参数，选填
				req.setSmsType("normal");//短信类型，必传
				req.setSmsFreeSignName(smsFreeSignName);//短信签名，必传
				req.setSmsParam("{\"code\":\""+code+"\"}");//短信模板中的变量，必传 如：{"code":"1234","product":"alidayu"}
				req.setRecNum(phoneNum);//短信要发送的手机号码，必传
				req.setSmsTemplateCode(smsTemplateCode);//短信模板ID，必传
				//调用接口，获得返回响应
				AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
				String body = rsp.getBody();
				logger.info("返回的响应信息{}："+body);
				return true;
			}else {
				return false;
			}
		} catch (ApiException e) {
			logger.error(e.getMessage(),e);
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
		}
		return false;
	}
	public static void main(String[] args) {
		sendCode("18301633640","123456");
	}
}
