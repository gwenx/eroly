package com.eroly.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import com.eroly.common.GlobalSt;
import com.eroly.exception.ErolyException;
import com.eroly.service.CourseService;
import com.eroly.service.InfoService;
import com.eroly.service.TeacherService;
import com.eroly.util.RedisCache;
import com.eroly.util.SpringTools;

public class CacheListener implements ServletContextListener {
	private static Logger logger = Logger.getLogger(CacheListener.class);
	public static WebApplicationContext springCtx;
    @Override
	public void contextDestroyed(ServletContextEvent arg0)  { 
    }

    @Override
	public void contextInitialized(ServletContextEvent arg0)  { 
    	logger.info("---CacheListener初始化开始---");
    	init();
    	logger.info("---CacheListener初始化结束---");
    }
	public void init() {
		try {
			RedisCache redisCache = SpringTools.getBean("RedisCache", RedisCache.class);
			CourseService courseService = SpringTools.getBean("CourseService", CourseService.class);
			InfoService infoService = SpringTools.getBean("InfoService", InfoService.class);
			TeacherService teacherService = SpringTools.getBean("TeacherService", TeacherService.class);
			if(redisCache!=null) {
				//缓存课程信息
				redisCache.putObject(GlobalSt.ALL_COURSE_INFO, courseService.findCourseInfo());
				//缓存首页信息
				redisCache.putObject(GlobalSt.PUBLIC_INFO_ALL, infoService.selectAllInfo());
				//缓存教授信息
				redisCache.putObject(GlobalSt.ALL_TEACHER_INFO, teacherService.findAll());
			}else {
				logger.error("---redisCache创建实例失败---");
			}
		} catch (ErolyException e) {
			logger.info(e.getMessage(),e);
		}catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
	}
}
