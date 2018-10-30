package com.eroly.controller;

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
import com.eroly.service.GraduationService;
import com.eroly.util.ValiddateUtil;
import com.eroly.util.cacheUpdate.RedisCache;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller("GraduationController")
@RequestMapping("/graduation")
public class GraduationController {
	private static Logger logger = LoggerFactory.getLogger(GraduationController.class);
	@Autowired
	@Qualifier("GraduationService")
	private GraduationService graduationService;
	@Autowired
	private RedisCache redisCache;
	/**
	 * 首页优秀学员模块，查询前六个学员信息
	 * @return
	 */
	@RequestMapping("findTopSix")
	@ResponseBody
	public Map<String, Object> findTopSix(){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Object obj = redisCache.getRedisValue(GlobalSt.TOP_SIX_GRA);
			List<Map<String, Object>> findTopSixGra = null;
			if (null != obj) {
				findTopSixGra = (List<Map<String, Object>>) obj;
			}
			//判断缓存中是否存在，不存在则查数据库，然后放到redis
			if (null == findTopSixGra || (null != findTopSixGra && findTopSixGra.size() == 0)) {//缓存中没有，查库
				findTopSixGra = graduationService.findTopSixGra();
				if (null != findTopSixGra && findTopSixGra.size() > 0) {
					redisCache.put(GlobalSt.TOP_SIX_GRA, findTopSixGra);//放缓存里
				} else {
					logger.error("-----课程信息为空，请核实------");
					throw new ErolyException("课程信息为空，请核实");
				}
			}
			result.put("topSixGra", findTopSixGra);
			result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ONE);
			result.put(GlobalSt.SYSTEM_MSG, "成功");
		}  catch (ErolyException e) {
			logger.error(e.getMessage(),e);
			result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			result.put(GlobalSt.SYSTEM_MSG, e.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			result.put(GlobalSt.SYSTEM_MSG, "系统异常，请稍后重试");
		}
		return result;
	}
	/**
	 * 分页查询就业信息，就业信息页用
	 * @param startNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("findGraPage")
	@ResponseBody
	public Map<String, Object> findGraPage(String startNum,String pageSize) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			ValiddateUtil.validParam(startNum);
			ValiddateUtil.validParam(pageSize);
			int num = Integer.parseInt(startNum);
			int size = Integer.parseInt(pageSize);
			PageHelper.startPage(num, size);
			List<Map<String, Object>> list = graduationService.findGraOfPage();
			PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
			List<Map<String, Object>> list2 = pageInfo.getList();
			int pages = pageInfo.getPages();//总页数
			int pageNum = pageInfo.getPageNum();//当前页码
			int nextPage = pageInfo.getNextPage();//下页页码
			int prePage = pageInfo.getPrePage();//上页页码
			long total = pageInfo.getTotal();//总记录数
			long firstPage = pageInfo.getFirstPage();//第一页
			logger.info(""+pageInfo);
			result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ONE);
			result.put("list", list2);
			result.put("pages", pages);
			result.put("pageNum", pageNum);
			result.put("nextPage", nextPage);
			result.put("prePage", prePage);
			result.put("total", total);
			result.put("firstPage", firstPage);
		}catch (ErolyException e) {
			logger.error(e.getMessage(),e);
			result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			result.put(GlobalSt.SYSTEM_MSG, e.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			result.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			result.put(GlobalSt.SYSTEM_MSG, "系统异常，请稍后重试");
		}
		return result;
	}

}
