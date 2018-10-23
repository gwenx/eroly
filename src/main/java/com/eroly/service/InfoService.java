package com.eroly.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eroly.mapper.InfoMapper;

@Service("InfoService")
public class InfoService {
private static Logger logger = LoggerFactory.getLogger(InfoService.class);
	
	@Autowired(required=true)
	@Qualifier("InfoMapper")
	private InfoMapper infoMapper;
	/**
	 * 按信息类型查找公共信息
	 * @param infoType 信息类型
	 * @return
	 */
	public List<Map<String, Object>> selectByInfoType(String infoType){
		logger.info("-------按信息类型查找公共信息开始----");
		logger.info("-------信息类型：{}"+infoType);
		List<Map<String, Object>> result = infoMapper.selectByInfoType(infoType);
		logger.info("-------查到的信息：{}"+result);
		return result;
	 }
	/**
	 * 按信息类型分类查询信息
	 * @return
	 */
	public Map<String, Object> selectAllInfo(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<String> infoTypeList = infoMapper.selectInfoType();//信息表中所有涉及的信息类型
		logger.info("-------按信息类型查找公共信息开始----"+infoTypeList);
		if(infoTypeList!=null && infoTypeList.size()>0) {
			for (String infoType : infoTypeList) {
				List<Map<String, Object>> result = infoMapper.selectByInfoType(infoType);
				resultMap.put(infoType, result);
			}
		}
		return resultMap;
	}

}
