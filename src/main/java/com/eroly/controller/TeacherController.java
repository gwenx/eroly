package com.eroly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eroly.common.GlobalSt;
import com.eroly.exception.ErolyException;
import com.eroly.service.TeacherService;
import com.eroly.util.RedisCache;

@Controller("TeacherController")
@RequestMapping("/teacher")
public class TeacherController {
	private static Logger logger = LoggerFactory.getLogger(TeacherController.class);
	@Autowired
	@Qualifier("TeacherService")
	private TeacherService teacherService;
	@Autowired(required=true)
	private RedisCache redisCache;
	
	@RequestMapping("findAll")
	@ResponseBody
	public Map<String, Object> findAll(){
		logger.info("---------查询所有教师信息- findAll -------");
		List<Map<String, Object>> teacherList = new ArrayList<Map<String, Object>>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			//从缓存去取教师信息
			Object obj = redisCache.getRedisValue(GlobalSt.ALL_TEACHER_INFO);
			if (null != obj) {
				teacherList = (List<Map<String, Object>>) obj;
			}
			//取不到就去查库，然后放缓存里
			if (null == teacherList || (null != teacherList && teacherList.size() == 0)) {
				teacherList = teacherService.findAll();
				if (null != teacherList && teacherList.size() > 0) {
					redisCache.put(GlobalSt.ALL_TEACHER_INFO, teacherList);
				} else {
					logger.error("-----教师信息为空，请核实------");
					throw new ErolyException("教师信息为空，请核实");
				}
			}
			resultMap.put("teacherList", teacherList);
			resultMap.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ONE);
			resultMap.put(GlobalSt.SYSTEM_MSG, "查询成功");
		} catch (ErolyException e) {
			logger.error(e.getMessage(),e);
			resultMap.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			resultMap.put(GlobalSt.SYSTEM_MSG, e.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			resultMap.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			resultMap.put(GlobalSt.SYSTEM_MSG,"系统异常，请稍后重试");
		}
		return resultMap;
	}
}
