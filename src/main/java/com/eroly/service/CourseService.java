package com.eroly.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eroly.exception.ErolyException;
import com.eroly.mapper.CourseMapper;
import com.eroly.util.cache.CacheBase;

@Service("CourseService")
public class CourseService extends CacheBase<String, Object>{
	private static Logger logger = LoggerFactory.getLogger(CourseService.class);
	
	@Autowired(required=true)
	@Qualifier("CourseMapper")
	private CourseMapper courseMapper;

	/**
	 * 查询所有课程信息
	 * @return
	 * @throws ErolyException 
	 */
	public List<Map<String, Object>> findCourseInfo() throws ErolyException {
		logger.info("-----CourseService findCourseInfo start");
		List<Map<String, Object>> courseList=new ArrayList<Map<String,Object>>();
		courseList = courseMapper.findCourseInfo();
		logger.info("-----CourseService findCourseInfo end");
		return courseList;
	}
	/**
     * 根据关键词查找课程ID
     * @param keyWords
     * @return
     */
    public List<String> findCourseIdByKeyWords(String keyWords){
    	logger.info("-----CourseService findCourseIdByKeyWords start");
    	logger.info("-----CourseService findCourseIdByKeyWords keyWords："+keyWords);
    	List<String> courseIdList = courseMapper.findCourseIdByKeyWords(keyWords);
    	logger.info("-----CourseService findCourseIdByKeyWords end");
    	return courseIdList;
    }
}
