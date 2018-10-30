package com.eroly.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.eroly.common.GlobalSt;
import com.eroly.exception.ErolyException;
import com.eroly.service.CourseService;
import com.eroly.util.cacheUpdate.RedisCache;
@Repository("CourseUtil")
public class CourseUtil {
	private static Logger logger = LoggerFactory.getLogger(CourseUtil.class);
	@Autowired(required=true)
	private RedisCache redisCache;
	@Autowired
	private CourseService courseService;
	/**
	 * <p>按ID查询课程信息</p>
	 * @param courseId
	 * @throws ErolyException 
	 */
	public Map<String, Object> getCourseInfoById(String courseId) throws ErolyException{
		//查询所有课程信息
		List<Map<String, Object>> courseList = getAllCourseInfo();
		Map<String, Object> courseMap = new HashMap<String, Object>();
		boolean getCourse = false;
		//筛选出对应ID的课程信息
		if (courseList != null && courseList.size() > 0) {
			for (Map<String, Object> map : courseList) {
				int redisCourseId = Integer.parseInt(map.get("courseId")+"");
				int courseIdInt = Integer.parseInt(courseId);
				if (courseIdInt == redisCourseId) {
					courseMap.putAll(map);
					getCourse = true;
					break;
				}
			}
		}
		logger.info("----courseId对应的课程信息：" + courseMap);
		if (getCourse) {//缓存中存在
			return courseMap;
		} else {//查不到则返回空
			return null;
		}
	}
	/**
	 * <p>查询所有课程信息</p>
	 * <p>先查缓存，查不到再查库 并放到缓存里</p>
	 * @return
	 * @throws ErolyException 
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAllCourseInfo() throws ErolyException{
		List<Map<String, Object>> courseList =null;
		Object obj = redisCache.getRedisValue(GlobalSt.ALL_COURSE_INFO);
		if(null != obj) {
			courseList=(List<Map<String, Object>>) obj;
		}
		logger.info("-----CourseService getAllCuoeseInfo:redis" + courseList);
		//判断缓存中是否存在，不存在则查数据库，然后放到redis
		if (null == courseList || (null != courseList && courseList.size() == 0)) {//缓存中没有，查库
			courseList = courseService.findCourseInfo();
			if (null != courseList && courseList.size() > 0) {
				redisCache.put(GlobalSt.ALL_COURSE_INFO, courseList);//放缓存里
			} else {
				logger.error("-----课程信息为空，请核实------");
				throw new ErolyException("课程信息为空，请核实");
			}
		}
		if (null != courseList && courseList.size() > 0) {
			return courseList;
		} else {
			logger.error("------未查询到课程信息----");
			throw new ErolyException("系统异常，请稍后再试");
		} 
	}
	
}
