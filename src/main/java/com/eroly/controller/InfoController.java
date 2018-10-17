package com.eroly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eroly.common.GlobalSt;
import com.eroly.exception.ErolyException;
import com.eroly.service.InfoService;
import com.eroly.util.RedisCache;
import com.eroly.util.StringUtil;

@Controller("InfoController")
@RequestMapping("/info")
public class InfoController {
	private static Logger logger = Logger.getLogger(InfoController.class);
	@Autowired(required=true)@Qualifier("InfoService")
	private InfoService infoService;
	@Autowired(required=true)
	private RedisCache redisCache;
	/**
	 * 按信息类型查找公共信息
	 * @param infoType 信息类型
	 * @return
	 */
	@RequestMapping("findInfo")
	@ResponseBody
	public Map<String, Object> selectByInfoType(String infoType){
		logger.info("------InfoController selectByInfoType start-------");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(StringUtil.isEmpty(infoType)) {
				throw new ErolyException("infoType不能为空");
			}
			Map<String, Object> infoMap = null;
			Object obj = redisCache.getRedisValue(GlobalSt.PUBLIC_INFO_ALL);
			if(null != obj) {
				infoMap = (Map<String, Object>) obj;
			}
			//缓存中不存在时，去查库，查完放缓存里
			if(null == infoMap) {
				infoMap = infoService.selectAllInfo();
				redisCache.putObject(GlobalSt.PUBLIC_INFO_ALL, infoMap);
			}
			logger.info("------呵呵哈哈哈------"+infoMap);
			Object object = infoMap.get(infoType);
			List<Map<String,Object>> infoList = new ArrayList<Map<String, Object>>();
			if(object!=null) {
				infoList=(List<Map<String, Object>>) object;
			}
			resultMap.put("infoList", infoList);
			resultMap.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ONE);
			resultMap.put(GlobalSt.SYSTEM_MSG, "查询成功");
		}catch (ErolyException e) {
			logger.error(e.getMessage(),e);
			resultMap.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			resultMap.put(GlobalSt.SYSTEM_MSG, e.getMessage());
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			resultMap.put(GlobalSt.SYSTEM_STATUS, GlobalSt.SYSTEM_STATU_ZERO);
			resultMap.put(GlobalSt.SYSTEM_MSG, "系统异常，请稍后重试");
		}
		logger.info("------InfoController selectByInfoType end-------");
		return resultMap;
		
	}
}
