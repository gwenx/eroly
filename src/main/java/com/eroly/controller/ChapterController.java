package com.eroly.controller;
import java.util.HashMap;
import java.util.Map;

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
@Controller("ChapterController")
@RequestMapping("/chapter")
public class ChapterController {
	private static Logger logger = LoggerFactory.getLogger(ChapterController.class);
	@Autowired
	@Qualifier("ChapterService")
	private ChapterService chapterService;
	@RequestMapping("findChapter")
	@ResponseBody
	public String findChapter(String courseId,Model model) {
		logger.info("-----------------findChapter start---------");
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> chapterList = new HashMap<String, Object>();
		chapterList = chapterService.findByCourseId(courseId);
		try {
			if(null != chapterList && chapterList.size()>0) {
				model.addAttribute("chapterList", chapterList);
				model.addAttribute(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ONE);
				model.addAttribute(GlobalSt.SYSTEM_MSG, "成功");
			}else {
				logger.error("------未查询到课程章节信息----");
				throw new ErolyException("系统异常，请稍后再试");
			}
			logger.info("-----------------findChapter----"+chapterList);
		} catch (ErolyException e) {
			logger.error(e.getMessage(),e);
			model.addAttribute(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			model.addAttribute(GlobalSt.SYSTEM_MSG, e.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			model.addAttribute(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			model.addAttribute(GlobalSt.SYSTEM_MSG, e.getMessage());
		}
		logger.info("-----------------findChapter end---------");
		return "course/course";//返回的页面路径
	}
}
