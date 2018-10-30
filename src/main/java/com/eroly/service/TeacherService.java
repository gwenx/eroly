package com.eroly.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.eroly.common.GlobalSt;
import com.eroly.util.cacheUpdate.ICacheUpdate;
import com.eroly.util.cacheUpdate.RedisCache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eroly.mapper.TeacherMapper;
@Service
public class TeacherService implements ICacheUpdate {
	private static Logger logger = LoggerFactory.getLogger(TeacherService.class);
	@Autowired
	private RedisCache redisCache;
	@Autowired
	@Qualifier("TeacherMapper")
	private TeacherMapper teacherMapper;
	public List<Map<String,Object>> findAll(){
		logger.info("-----TeacherService findAll enter----");
		List<Map<String, Object>> teacherList=new ArrayList<Map<String,Object>>();
		teacherList=teacherMapper.findAll();
		logger.info("-----TeacherService findAll end----");
		return teacherList;
	}

	public void update() {
		logger.info("TeacherService findAll 刷新缓存");
		//缓存教授信息
		redisCache.putObject(GlobalSt.PUBLIC_INFO_ALL, this.findAll());
	}
}
