package com.eroly.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eroly.common.GlobalSt;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		HttpSession session = arg0.getSession();
		String user = (String) session.getAttribute(GlobalSt.LOGIN_FLAG);
		if(GlobalSt.LOGIN_YES.equals(user)) {
			return true;
		} else {
			arg1.sendRedirect("/eroly/public/gotoLogin");
			return false;
		}
	}
}
