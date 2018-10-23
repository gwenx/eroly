package com.eroly.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eroly.mapper.TeacherMapper;
@Service("TeacherService")
public class TeacherService {
	private static Logger logger = LoggerFactory.getLogger(TeacherService.class);
	@Autowired(required=true)
	@Qualifier("TeacherMapper")
	private TeacherMapper teacherMapper;
	public List<Map<String,Object>> findAll(){
		logger.info("-----TeacherService findAll enter----");
		List<Map<String, Object>> teacherList=new ArrayList<Map<String,Object>>();
		teacherList=teacherMapper.findAll();
		logger.info("-----TeacherService findAll end----");
		return teacherList;
	}
}
