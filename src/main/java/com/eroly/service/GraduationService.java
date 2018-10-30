package com.eroly.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.eroly.mapper.GraduationMapper;
import com.github.pagehelper.PageHelper;

@Service("GraduationService")
public class GraduationService {
	private static Logger logger = LoggerFactory.getLogger(GraduationService.class);
	@Autowired
	@Qualifier("GraduationMapper")
	private GraduationMapper graduationMapper;
	/**
	 * 查询前六个学员就业信息
	 * @return
	 */
	public List<Map<String,Object>> findTopSixGra(){
		logger.info("查询前六个就业的学员");
		return graduationMapper.findTopSixGra();
	}
	/**
	 * 分页查找就业信息
	 * @return
	 */
	public List<Map<String,Object>> findGraOfPage(){
		logger.info("分页查询就业的学员信息");
		return graduationMapper.findGraOfPage();
	}
}