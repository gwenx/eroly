package com.eroly.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eroly.common.GlobalSt;
import com.eroly.exception.ErolyException;
import com.eroly.util.OperaServletUtil;
import com.eroly.util.SMSUtils;
import com.eroly.util.ValiddateUtil;

@Controller("SMSController")
@RequestMapping("/messsage")
public class SMSController {
	private static Logger logger = LoggerFactory.getLogger(SMSController.class);
	@Autowired
	OperaServletUtil OperaServlet;
	/**
	 * 发送短信验证码
	 * @param phoneNum
	 * @return
	 */
	@RequestMapping("getSMSCode")
	@ResponseBody
	public Map<String, Object> getMessage(String phoneNum){
		Map<String, Object> result = new HashMap<String, Object>();
		int code=(int) (Math.random()*(999999-100000+1))+100000;
		try {
			ValiddateUtil.validParam(phoneNum);
			boolean sendCode = SMSUtils.sendCode(phoneNum, code+"");
			if(sendCode) {
				//把短信验证码放在会话里
				OperaServlet.setSessValue(GlobalSt.MESSAGE_SESSION,code);
				OperaServlet.setSessValue(GlobalSt.MESSAGE_TIME,System.currentTimeMillis());
				result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ONE);
				result.put(GlobalSt.SYSTEM_MSG, "发送成功");
			}else {
				result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
				result.put(GlobalSt.SYSTEM_MSG, "发送失败");
			}
		} catch (ErolyException e) {
			logger.error(e.getMessage(),e);
			result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			result.put(GlobalSt.SYSTEM_MSG, e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			result.put(GlobalSt.SYSTEM_MSG, "系统异常，请稍后重试");
		}
		return result;
	}
}
