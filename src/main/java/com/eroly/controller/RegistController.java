package com.eroly.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eroly.common.GlobalSt;
import com.eroly.domain.User;
import com.eroly.exception.ErolyException;
import com.eroly.service.UserService;
import com.eroly.util.DigestUtil;
import com.eroly.util.StringUtil;

@Controller("RegistController")
@RequestMapping("/regist")
public class RegistController {
	private static Logger logger = LoggerFactory.getLogger(RegistController.class);
	@Autowired
	@Qualifier("UserService")
	private UserService userService;
	/**
	 * 注册
	 * @return
	 */
	@RequestMapping("toRegist")
	@ResponseBody
	public Map<String, Object> regist(String userName,String password){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)) {
				throw new ErolyException("userName或password不能为空");
			}
			if (isRegist(userName)) {//用户名被占用
				result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
				result.put(GlobalSt.SYSTEM_MSG, "用户名已被占用，请更换");
				return result;
			} 
			if("123456".equals(password)||"112233".equals(password)||"111111".equals(password)||"111222".equals(password)) {
				throw new ErolyException("密码过于简单，请更换");
			}
			User record = new User();
			record.setUserName(userName);
			record.setUserPassword(DigestUtil.hmacSign(password, ""));
			boolean insertSelective = userService.insertSelective(record);
			if(insertSelective) {
				result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ONE);
				result.put(GlobalSt.SYSTEM_MSG, "注册成功");
			}else {
				throw new ErolyException("注册失败，请重试");
			}
		} catch (ErolyException e) {
			logger.error(e.getMessage(),e);
			result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			result.put(GlobalSt.SYSTEM_MSG, e.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			result.put(GlobalSt.SYSTEM_MSG, "系统异常，请稍后重试");
		}
		return result;
	}
	/**
	 * 是否注册
	 * @return
	 */
	@RequestMapping("judgeRegist")
	@ResponseBody
	public Map<String, Object> judgeRegist(String userName){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtil.isEmpty(userName)) {
				throw new ErolyException("userName不能为空");
			}
			if (isRegist(userName)) {//用户名被占用
				result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
				result.put(GlobalSt.SYSTEM_MSG, "用户名已被占用，请更换");
				return result;
			} 
			result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ONE);
			result.put(GlobalSt.SYSTEM_MSG, "用户名可用");
		} catch (ErolyException e) {
			logger.error(e.getMessage(),e);
			result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			result.put(GlobalSt.SYSTEM_MSG, e.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			result.put(GlobalSt.SYSTEM_MSG, "系统异常，请稍后重试");
		}
		return result;
	}
	/**
	 * 根据用户名查找用户信息
	 * @param userName
	 * @return
	 */
	public Map<String,Object> findUser(String userName) {
		Map<String,Object> user = userService.findByUserName(userName);
		return user;
	}
	/**
	 * 判断是否注册
	 * @param userName 用户名
	 * @return true已注册 false未注册
	 */
	public boolean isRegist(String userName) {
		Map<String,Object> user = findUser(userName);
		if(null != user && userName.equals(user.get("userName"))) {
			return true;
		}
		return false;
	}
	

}
