package com.eroly.controller;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eroly.common.GlobalSt;
import com.eroly.exception.ErolyException;
import com.eroly.service.UserService;
import com.eroly.util.OperaServletUtil;
import com.eroly.util.ValiddateUtil;
@Controller("UserController")
@RequestMapping("/user")
public class UserController {
	private static Logger logger = Logger.getLogger(UserController.class);
	@Autowired@Qualifier("UserService")
	private UserService userService;
	@Autowired
	OperaServletUtil OperaServlet;
	@RequestMapping("userLogin")
	public String userLogin(String userInfo,String userPass,Model model) {
		try {
			ValiddateUtil.validParam(userInfo);
			ValiddateUtil.validParam(userPass);
			String user = (String) OperaServlet.getSessValue(GlobalSt.LOGIN_FLAG);
			if("1".equals(user)) {
				throw new ErolyException("您已登录，请勿重复登录");
			}
			Map<String, Object> userInfoMap = userService.findByUserInfo(userInfo);
			if (userInfoMap == null) {
				model.addAttribute("userInput", userPass);
				model.addAttribute("error", "1");
				model.addAttribute(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
				model.addAttribute(GlobalSt.SYSTEM_MSG, "账号错误,请重新输入");
				return "login/login";
			}
			String password = (String) userInfoMap.get("userPassword");
			if(!userPass.equals(password)) {
				model.addAttribute("userInput", userInfo);
				model.addAttribute("error", "2");
				model.addAttribute(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
				model.addAttribute(GlobalSt.SYSTEM_MSG, "密码错误,请重输入");
				return "login/login";
			}
			OperaServlet.setSessValue(GlobalSt.USER_INFO, userInfoMap);
			OperaServlet.setSessValue(GlobalSt.LOGIN_FLAG, GlobalSt.LOGIN_YES);
			model.addAttribute(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ONE);
			model.addAttribute(GlobalSt.SYSTEM_MSG, "登录成功");
		}catch (ErolyException e) {
			logger.error(e.getMessage(),e);
			model.addAttribute(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			model.addAttribute(GlobalSt.SYSTEM_MSG, e.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			model.addAttribute(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			model.addAttribute(GlobalSt.SYSTEM_MSG, e.getMessage());
		}
		return "redirect:/public/gotoIndex";
	}
	/**
	 * 注销登录
	 */
	@RequestMapping("userLogout")
	public String userLogout() {
		//清空登录信息
		OperaServlet.clearSession();
		return "redirect:/public/gotoIndex";
	}
}