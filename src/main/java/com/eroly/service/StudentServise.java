package com.eroly.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eroly.mapper.StudentMapper;
@Service("StudentServise")
public class StudentServise {
	private static Logger logger = Logger.getLogger(StudentServise.class);
	@Autowired(required=true)
	@Qualifier("StudentMapper")
	private StudentMapper studentMapper;
	/**
     * 插入报名信息
     * @param param (userId用户ID,courseId课程ID,hasPay是否缴费,payMoney缴费金额,studentStatus学员状态)
     * @return
     */
	public void insertSignInfo(Map<String,String> param) {
		studentMapper.insertSignInfo(param);
	}
	/**
	 * 根据用户ID和课程ID，查询用户是否报过名过该课程
	 * @param userId
	 * @param courseId
	 * @return
	 */
	public Map<String, Object> findSignUp(String userId,String courseId){
		Map<String, String> param = new HashMap<String, String>();
		param.put("userId", userId);
		param.put("courseId", courseId);
		return studentMapper.findSignUp(param);
	}
	/**
	 * 根据用户ID查询用户报过名的课程
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> findSignByUser(String userId){
		return studentMapper.findSignByUser(userId);
	}
}
