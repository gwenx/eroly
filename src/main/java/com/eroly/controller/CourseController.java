package com.eroly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eroly.common.GlobalSt;
import com.eroly.exception.ErolyException;
import com.eroly.service.ChapterService;
import com.eroly.service.CourseService;
import com.eroly.util.CourseUtil;
import com.eroly.util.RedisCache;
import com.eroly.util.ValiddateUtil;

@Controller("CourseController")
@RequestMapping("/course")
public class CourseController {
	
	private static Logger logger = LoggerFactory.getLogger(CourseController.class);
	@Autowired
	@Qualifier("CourseService")
	private CourseService courseService;
	@Autowired
	@Qualifier("ChapterService")
	private ChapterService chapterService;
	@Autowired(required=true)
	private RedisCache redisCache;
	@Autowired(required=true)
	private CourseUtil courseUtil;
	
	/**
	 * 查询所有课程
	 * @param model
	 * @return
	 */
	@RequestMapping("findCourseInfo")
//	@ResponseBody
	public String findCourseInfo(Model model) {
		logger.info("-----------------findCourseInfo start---------");
		List<Map<String, Object>> courseList = new ArrayList<Map<String, Object>>();
		try {
			courseList = courseUtil.getAllCourseInfo();//查询所有课程信息.先查缓存，查不到再查库 并放到缓存里
			if(null != courseList && courseList.size()>0) {
				model.addAttribute("courseList", courseList);
				model.addAttribute(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ONE);
				model.addAttribute(GlobalSt.SYSTEM_MSG, "成功");
			}else {
				logger.error("------未查询到课程信息----");
				throw new ErolyException("系统异常，请稍后再试");
			}
			logger.info("---findCourseInfo:{}----",courseList);
		} catch (ErolyException e) {
			logger.error(e.getMessage(),e);
			model.addAttribute(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			model.addAttribute(GlobalSt.SYSTEM_MSG, e.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			model.addAttribute(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			model.addAttribute(GlobalSt.SYSTEM_MSG, "系统异常，请稍后重试");
		}
		logger.info("-----------------findCourseInfo end---------");
		return "course/course";//返回的页面路径
	}
	@RequestMapping("findCourse")
	@ResponseBody
	public Map<String,Object> findCourse() {
		logger.info("-----------------findCourse start---------");
		Map<String,Object> result = new HashMap<String, Object>();
		List<Map<String, Object>> courseList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> courseList1 = new ArrayList<Map<String, Object>>();
		try {
			courseList = courseUtil.getAllCourseInfo();//查询所有课程信息.先查缓存，查不到再查库 并放到缓存里
			if(null != courseList && courseList.size()>0) {
				for (Map<String, Object> map : courseList) {
					Map<String,Object> map1 = new HashMap<String,Object>();
					map1.put("courseName", map.get("courseName"));
					map1.put("courseId", map.get("courseId"));
					map1.put("totalPrice", map.get("totalPrice"));
					map1.put("priceDiscount", map.get("priceDiscount"));
					courseList1.add(map1);
				}
				result.put("courseList", courseList1);
				result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ONE);
				result.put(GlobalSt.SYSTEM_MSG, "成功");
			}else {
				logger.error("------未查询到课程信息----");
				throw new ErolyException("系统异常，请稍后再试");
			}
			logger.info("-----------------findCourse----"+courseList);
		} catch (ErolyException e) {
			logger.error(e.getMessage(),e);
			result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			result.put(GlobalSt.SYSTEM_MSG, e.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			result.put(GlobalSt.SYSTEM_MSG, "系统异常，请稍后重试");
		}
		logger.info("-----------------findCourse end---------");
		return result;//返回的页面路径
	}
	/**
	 * 网站上部搜索框
	 * 根据课程名称模糊搜索课程详情,包括章节，课时
	 * @param model
	 * @param courseName
	 * @return
	 */
	@Autowired HttpServletRequest request;
	@RequestMapping("findCourseByKeyWords")
	public String findCourseByKeyWords(Model model,String courseName) {
		String characterEncoding = request.getCharacterEncoding();
		logger.info("-----------------findCourseByName start---------"+characterEncoding);
		logger.info("-----------------findCourseByName courseName:{}---------"+courseName);
		try {
			ValiddateUtil.validParam(courseName);
			List<String> courseIdList = courseService.findCourseIdByKeyWords(courseName);
			if(courseIdList!=null && courseIdList.size()>0) {
				model.addAttribute("haveCourse", "1");//是否查到相关课程 1是 0否
				if(courseIdList.size()==1) {//当只搜到一个课程时直接显示详情
					String courseId = courseIdList.get(0);
					//跳转转发到查课程详情的方法
					return "forward:/course/findCourseById?courseId="+courseId;
				}else {
					//查询课程信息
					List<Map<String, Object>> courseAllList = courseUtil.getAllCourseInfo();//查询所有课程信息.先查缓存，查不到再查库 并放到缓存里
					if(null != courseAllList && courseAllList.size()>0) {
						List<Map<String, Object>> courseList = new ArrayList<Map<String, Object>>();//满足用户查询条件的课程
						for (String courseId : courseIdList) {
							for (Map<String, Object> map : courseAllList) {
								String courseIdInMap = ""+map.get("courseId");
								if(courseId.equals(courseIdInMap)) {
									courseList.add(map);
									break;
								}
							}
						}
						model.addAttribute("haveCourse", "1");
						model.addAttribute("courseName", courseName);
						model.addAttribute("courseList", courseList);
						model.addAttribute(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ONE);
						model.addAttribute(GlobalSt.SYSTEM_MSG, "成功");
						return "course/course";
					}else {
						logger.error("------未查询到课程信息----");
						model.addAttribute("courseName", courseName);
						model.addAttribute("haveCourse", "0");
						model.addAttribute(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ONE);
						model.addAttribute(GlobalSt.SYSTEM_MSG, "未查询到关于“"+courseName+"”的课程");
						return "course/course";
					}
				}
			}else {
				logger.error("------未查询到课程信息----");
				model.addAttribute("courseName", courseName);
				model.addAttribute("haveCourse", "0");
				model.addAttribute(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ONE);
				model.addAttribute(GlobalSt.SYSTEM_MSG, "未查询到关于“"+courseName+"”的课程");
				return "course/course";
			}
		}catch (ErolyException e) {
			logger.error(e.getMessage(),e);
			model.addAttribute(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			model.addAttribute(GlobalSt.SYSTEM_MSG, e.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			model.addAttribute(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			model.addAttribute(GlobalSt.SYSTEM_MSG, "系统异常，请稍后重试");
		}
		return "course/courseInfo";
	}
	/**
	 * 根据课程ID点详情时调用
	 * 查询课程详情,包括章节，课时
	 * @param model
	 * @param courseId
	 * @param courseName
	 * @return
	 */
	@RequestMapping("findCourseById")
	public String findCourseById(Model model,String courseId,String courseName) {
		logger.info("-----------------findCourseById start---------");
		logger.info("-----------------findCourseById courseId:{}---------"+courseName);
		Map<String, Object> courseMap = new HashMap<String, Object>();
		try {
			ValiddateUtil.validParam(courseId);
			courseMap = courseUtil.getCourseInfoById(courseId);
			logger.info("----courseId对应的课程信息："+courseMap);
			if(courseMap != null && courseMap.size()>0) {//查到了课程信息
				//查询课程章节详情
				List<Map<String, Object>> courseDetailList = getChapterInfoByCourseId(courseId);
				if(courseDetailList!=null&&courseDetailList.size()>0) {
					model.addAttribute("courseMap",courseMap);//课程信息
					model.addAttribute("chapterList",courseDetailList);//章节列表
					model.addAttribute("courseId",courseId);
					model.addAttribute("courseName",courseName);
					model.addAttribute(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ONE);
					model.addAttribute(GlobalSt.SYSTEM_MSG, "成功");
				}else {
					throw new ErolyException("系统异常，请稍后再试");
				}
			}else {//没查到
				logger.error("------未查询到课程信息----");
				throw new ErolyException("系统异常，请稍后再试");
			}
		}catch (ErolyException e) {
			logger.error(e.getMessage(),e);
			model.addAttribute(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			model.addAttribute(GlobalSt.SYSTEM_MSG, e.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			model.addAttribute(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			model.addAttribute(GlobalSt.SYSTEM_MSG, "系统异常，请稍后重试");
		}
		return "course/courseInfo";
	}
	/**
	 * 根据课程ID查询对应的章节信息和课时信息
	 */
	public List<Map<String, Object>> getChapterInfoByCourseId(String courseId) {
		Object obj = redisCache.getRedisValue(GlobalSt.CHAPTER_LIST+courseId);
		List<Map<String, Object>> courseDetailList = null;
		if(null != obj) {
			courseDetailList=(List<Map<String, Object>>) obj;
		}
		//缓存中存在 则直接返回
		if(courseDetailList!=null&&courseDetailList.size()>0) {
			return courseDetailList;
		}
		//查询课程章节详情
		Map<String, Object> resultMap = chapterService.findByCourseId(courseId);
		//所有章节的信息列表
		courseDetailList = (List<Map<String, Object>>)resultMap.get("courseDetailList"); 
		//根据课程ID查到的对应的所有课时信息。key是章节ID，value是该章节对应的所有课时列表，列表里放的是map
		Map<String, Object> nodeMap = (Map<String, Object>) resultMap.get("nodeMap");
		
		Set<String> keySet = nodeMap.keySet();//所有章节的ID
		//从所有课时里找到对应章节的课时信息.这个循环就是要把课时信息和对应的章节进行匹配放在一起，组织数据格式
		for (Map<String, Object> map : courseDetailList) {
			for (String key : keySet) {
				int mapKey = Integer.parseInt(map.get("chapterId")+"");//章节ID
				int parseInt = Integer.parseInt(key);
				if(parseInt == mapKey) {
					map.put("NODE",nodeMap.get(key));//这个NODE对应的是一个listMap
					break;
				}
			}
		}
		if(courseDetailList!=null&&courseDetailList.size()>0) {
			redisCache.put(GlobalSt.CHAPTER_LIST+courseId, courseDetailList);//放缓存里
		}
		return courseDetailList;
	}
	
}
