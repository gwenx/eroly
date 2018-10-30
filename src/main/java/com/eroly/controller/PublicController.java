package com.eroly.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eroly.common.GlobalSt;
import com.eroly.util.OperaServletUtil;

@Controller("PublicController")
@RequestMapping("/public")
public class PublicController {
	private static Logger logger = LoggerFactory.getLogger(PublicController.class);
	
	@Autowired
	OperaServletUtil OperaServlet;
	@RequestMapping("time")
	public String toTime() {
		return "time";
	}
	@RequestMapping("courseInfo")
	public String toGraduateInfo() {
		return "courseInfo";
	}
	@RequestMapping("gotoLogin")
	public String gotoLogin() {
		String user = (String) OperaServlet.getSessValue(GlobalSt.LOGIN_FLAG);
		if("1".equals(user)) {
			return "redirect:gotoIndex";//重定向，在当前类下直接写gotoIndex。若是其他类下则/***/***
		}
		return "login/login";
	}
	@RequestMapping("gotoRegist")
	public String gotoRegist() {
		return "regist";
	}
	@RequestMapping("goGra")
	public String goGra() {
		return "empinfo";
	}
	@RequestMapping("user/goSignUp")
	public String goSignUp() {
		return "signup/signup";
	}
	@RequestMapping("goSignSuccess")
	public String goSignSuccess() {
		logger.info("--------hhhhhh-");
		return "signup/success";
	}
	@RequestMapping("user/gotoSignUp")
	public String gotoSignUp() {
		return "signup/signup2";
	}
	@RequestMapping("gotoIndex")
	public String gotoIndex() {
		return "home/index";
	}
	@RequestMapping("gotoAboutUs")
	public String gotoAboutUs() {
		return "aboutUs/aboutUs";
	}
	@RequestMapping("gotoTeacher")
	public String gotoTeacher() {
		return "teacher/teacher";
	}
	@RequestMapping("user/gotoMessage")
	public String gotoMessage() {
		return "me/message";
	}
	@RequestMapping("user/passUpdate")
	public String gotoPassUpdate() {
		return "me/passUpdate";
	}
	@RequestMapping("user/msgUpdate")
	public String gotoMsgUpdate() {
		return "me/msgUpdate";
	}
}
