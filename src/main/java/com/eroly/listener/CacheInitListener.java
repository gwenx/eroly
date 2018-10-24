package com.eroly.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.eroly.util.cacheUpdate.PubSubManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

import com.eroly.service.CourseService;
import com.eroly.service.InfoService;
import com.eroly.service.TeacherService;
import com.eroly.util.SpringTools;

/**
 * 加载系统参数
 */
public class CacheInitListener implements ServletContextListener {
	private static Logger logger = LoggerFactory.getLogger(CacheInitListener.class);
	public static WebApplicationContext springCtx;
//	@Override
	public void contextDestroyed(ServletContextEvent arg0)  { 
    }
//    @Override
    public void contextInitialized(ServletContextEvent arg0)  { 
    	logger.info("---CacheListener初始化开始---");
    	init();
    	logger.info("---CacheListener初始化结束---");
    }
	public void init() {
		try {
			PubSubManager pubSubManager = SpringTools.getBean("pubSubManager", PubSubManager.class);

			CourseService courseService = SpringTools.getBean("courseService", CourseService.class);
			pubSubManager.addListener("courseService",courseService);

			InfoService infoService = SpringTools.getBean("infoService", InfoService.class);
			pubSubManager.addListener("infoService",infoService);

			TeacherService teacherService = SpringTools.getBean("teacherService", TeacherService.class);
			pubSubManager.addListener("teacherService",teacherService);

			//启动线程执行订阅操作
			pubSubManager.start();
			loadParamToRedis();
		} catch (Exception e) {
			logger.info(e.getMessage(),e);
		}
	}
	private void loadParamToRedis(){
		CourseService courseService = SpringTools.getBean("courseService", CourseService.class);
		courseService.update();
		InfoService infoService = SpringTools.getBean("infoService", InfoService.class);
		infoService.update();
		TeacherService teacherService = SpringTools.getBean("teacherService", TeacherService.class);
		teacherService.update();
	}
}
