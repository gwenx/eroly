package com.eroly.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eroly.domain.User;
import com.eroly.mapper.UserMapper;

@Service("UserService")
public class UserService {
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired(required=true)
	@Qualifier("UserMapper")
	private UserMapper userMapper;
	/**
	 * 根据用户输入信息查找用户信息
	 * @param userId
	 * @return
	 */
	public Map<String , Object> findByUserInfo(String userInfo){
		logger.info("--UserService 用户输入信息--："+userInfo);
		return userMapper.findByUserInfo(userInfo);
	}
	/**
	 * 根据用户ID查找用户信息
	 * @param userId
	 * @return
	 */
	public Map<String , Object> selectByUserId(Integer userId){
		logger.info("--UserService 根据ID查询用户信息--ID："+userId);
		return userMapper.selectByUserId(userId);
	}
	/**
	 * 根据用户名查找用户信息
	 * @param userName
	 * @return
	 */
	public Map<String,Object> findByUserName(String userName){
		logger.info("--UserService 根据用户名查询用户信息--用户名："+userName);
		return userMapper.findByUserName(userName);
	}
	public boolean insertSelective(User record) {
		logger.info("--UserService 新建用户user："+record);
		int insertSelective = userMapper.insertSelective(record);
		if(insertSelective==1) {
			return true;
		}
		return false;
	}
}
