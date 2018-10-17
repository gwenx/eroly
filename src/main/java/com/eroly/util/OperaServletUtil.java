package com.eroly.util;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("OperaServletUtil")
public class OperaServletUtil {
	@Autowired
	HttpSession session;
	/**
	 * 保存在session中
	 * @param key
	 * @param value
	 */
	public void setSessValue(String key,Object value) {
		session.setAttribute(key, value);
	}
	/**
	 * 从session中获得key的value
	 * @param key
	 * @return
	 */
	public Object getSessValue(String key) {
		Object attribute = session.getAttribute(key);
		return attribute;
	}
	/**
	 * 清空session中key对应的value
	 * @param key
	 */
	public void removeSessValue(String key) {
		session.removeAttribute(key);
	}
	/**
	 * 清空当前session
	 */
	public void clearSession() {
		session.invalidate();
	}
}
